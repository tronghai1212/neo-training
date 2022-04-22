package vn.plusplus.lms.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.factory.PageResponse;
import vn.plusplus.lms.factory.PageResponseUtil;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.services.BaseSearchService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class BaseSearchController<T> {
    @Autowired
    BaseSearchService<T> service;

    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    public ResponseEntity findAllByRsql(
            @RequestParam(value = "query") String query,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "direction", required = false) String direction) throws UnsupportedEncodingException {
        query = URLDecoder.decode(query, StandardCharsets.UTF_8.name());
        if (query.trim().isEmpty()) {
            query += "status!=DELETED";
        } else query += ";status!=DELETED";
        PageRequest pageRequest = service.getPageRequestParam(size, page, true, sort, direction);
        Page pageResult = service.findAllByRsql(query, pageRequest);
        List<Object> collect = pageResult.getContent();
        PageResponse pageResponse = PageResponseUtil.buildPageResponse(pageResult);
        ResponseEntity responseEntity = ResponseFactory.success(collect, pageResponse);
        return responseEntity;
    }
}
