package vn.plusplus.lms.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewApiRequest;
import vn.plusplus.lms.controller.request.UpdateApiRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.repository.entities.ApiEntity;
import vn.plusplus.lms.services.ApiService;
import vn.plusplus.lms.interceptor.Payload;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/admin/api")
public class ApiController extends BaseSearchController<ApiEntity> {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    ApiService service;
    @Autowired
    ResponseFactory factory;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity findAllByRsql(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "direction", required = false) String direction) throws UnsupportedEncodingException {

        logger.info("query= " + query);
        if (StringUtils.isBlank(sort)) {
            sort = "updatedTime";
        }
        if (StringUtils.isBlank(direction)) {
            direction = Sort.Direction.DESC.name();
        }
        return super.findAllByRsql(query, size, page, sort, direction);
    }

    @PostMapping
    public ResponseEntity addApi(@RequestBody NewApiRequest request,
                                 @RequestAttribute Payload payload) {
        logger.info("Add new Api :", request.getName());
        ApiEntity data = service.addApi(request, payload.getRoles());
        return factory.success(data, ApiEntity.class);
    }

    @PutMapping(value = "/{apiId}")
    public ResponseEntity updateApi(@PathVariable("apiId") Integer apiId,
                                    @RequestBody UpdateApiRequest request,
                                    @RequestAttribute Payload payload) {
        logger.info("Update api Id [{}]", apiId);
        ApiEntity data = service.updateApi(apiId, request, payload.getRoles());
        return factory.success(data, ApiEntity.class);
    }

    @DeleteMapping(value = "/{apiId}")
    public ResponseEntity deleteApi(@PathVariable("apiId") Integer apiId,
                                    @RequestAttribute Payload payload){
        logger.info("Delete API ID [{}]",apiId);
        String data = service.deleteApi(apiId, payload.getRoles());
        return factory.success(data,String.class);
    }
}
