package study.gordon.titan.showcase.simple.rest;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Validator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import study.gordon.titan.common.rest.RestException;
import study.gordon.titan.common.util.BeanValidators;
import study.gordon.titan.common.web.MediaTypes;
import study.gordon.titan.showcase.simple.entity.SimpleEntity;
import study.gordon.titan.showcase.simple.service.SimpleEntityService;

@RestController
@RequestMapping(value = "/api/v1/simple")
public class SimpleEntityRestController {

    @Resource
    private SimpleEntityService simpleEntityService;

    @Resource
    private Validator validator;

    @RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    public List<SimpleEntity> list() {
        return simpleEntityService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    public SimpleEntity get(@PathVariable("id") Long id) {
        SimpleEntity simple = simpleEntityService.findById(id);
        if (simple == null) {
            String message = "Simple Entity 不存在(id:" + id + ")";
            throw new RestException(HttpStatus.NOT_FOUND, message);
        }
        return simple;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaTypes.JSON)
    public ResponseEntity<String> create(@RequestBody SimpleEntity simple, UriComponentsBuilder uriBuilder) {
        // 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
        BeanValidators.validateWithException(validator, simple);

        simpleEntityService.create(simple);

        // 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
        Long id = simple.getId();
        URI uri = uriBuilder.path("/api/v1/simple/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaTypes.JSON)
    public ResponseEntity<String> update(@RequestBody SimpleEntity simple) {
        // 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
        BeanValidators.validateWithException(validator, simple);

        simpleEntityService.update(simple);

        // 按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        simpleEntityService.deleteById(id);
    }
}
