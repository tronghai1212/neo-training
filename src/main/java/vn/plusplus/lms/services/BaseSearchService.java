package vn.plusplus.lms.services;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;
import vn.plusplus.lms.config.RSQLSearchConfiguration;
import vn.plusplus.lms.repository.rsql.GenericRSQLVisitor;
import vn.plusplus.lms.repository.rsql.RSQLOperators;

import java.util.List;
import java.util.function.Consumer;

public abstract class BaseSearchService<T> {
    private JpaRepository<T, Integer> repository;

    @Autowired
    RSQLSearchConfiguration searchConfiguration;

    @Autowired
    public void setRepository(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    public Page findAllByRsql(String search, PageRequest pageRequest) {
        return findAllByRsql(search, pageRequest, (page) -> {
        });
    }

    public Page findAllByRsql(String search, PageRequest pageRequest, Consumer<List<T>> getAdditionalInfo) {
        if (repository instanceof JpaSpecificationExecutor) {
            Page result;
            if (StringUtils.isEmpty(search)) {
                result = repository.findAll(pageRequest);
            } else {
                Node rootNode = new RSQLParser(RSQLOperators.supportedOperators()).parse(search);
                Specification<T> spec = rootNode.accept(new GenericRSQLVisitor<>());
                result = ((JpaSpecificationExecutor) repository).findAll(spec, pageRequest);
            }
            getAdditionalInfo.accept(result.getContent());
            return result;
        }

        return null;
    }

    public PageRequest getPageRequestParam(Integer size, Integer page, boolean sortResult, String sort, String direction) {
        int searchPage = page == null ? 0 : page;
        int searchSize = size == null ? searchConfiguration.size() : size;
        if (!sortResult) {
            return PageRequest.of(searchPage, searchSize);
        } else {
            String searchSort = StringUtils.isEmpty(sort) ? searchConfiguration.sort() : sort;
            Sort.Direction searchDirection = StringUtils.isEmpty(direction) ? searchConfiguration.direction() : RSQLSearchConfiguration.DIRECTION_ASC.equals(direction) ? Sort.Direction.ASC : Sort.Direction.DESC;
            return PageRequest.of(searchPage, searchSize, new Sort(searchDirection, searchSort));
        }
    }
}
