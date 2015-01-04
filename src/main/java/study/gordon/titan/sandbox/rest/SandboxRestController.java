package study.gordon.titan.sandbox.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import study.gordon.titan.common.web.MediaTypes;
import study.gordon.titan.showcase.simple.entity.SimpleEntity;

@RestController
@RequestMapping(value = "/api/v1/sandbox")
public class SandboxRestController {

    @RequestMapping(value = "/case1", method = RequestMethod.POST, consumes = MediaTypes.TEXT_PLAIN)
    public String textToText(@RequestBody String body) {
        return "Hello " + body;
    }

    @RequestMapping(value = "/case2", method = RequestMethod.POST, consumes = MediaTypes.TEXT_PLAIN)
    public SimpleEntity textToJson(@RequestBody String body) {
        SimpleEntity simple = new SimpleEntity();
        simple.setName(body);
        return simple;
    }

    @RequestMapping(value = "/case3", method = RequestMethod.POST, consumes = MediaTypes.JSON)
    public SimpleEntity jsonToJson(@RequestBody SimpleEntity simple) {
        simple.setName("updated-" + simple.getName());
        return simple;
    }

    @RequestMapping(value = "/case4", method = RequestMethod.POST, consumes = MediaTypes.JSON)
    public ResponseEntity<String> jsonToText(@RequestBody SimpleEntity simple) {
        return new ResponseEntity<String>("cool", HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/case5", method = RequestMethod.POST, consumes = MediaTypes.JSON)
    public List<SimpleEntity> jsonToList(@RequestBody SimpleEntity simple) {
        List<SimpleEntity> result = new ArrayList<SimpleEntity>();
        SimpleEntity newSimple = new SimpleEntity();
        newSimple.setName(simple.getName() + "_1");
        result.add(newSimple);
        newSimple = new SimpleEntity();
        newSimple.setName(simple.getName() + "_2");
        result.add(newSimple);
        return result;
    }

    @RequestMapping(value = "/case6", method = RequestMethod.POST, consumes = MediaTypes.JSON)
    public Map<String, String> jsonToMap(@RequestBody SimpleEntity simple) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", simple.getName());
        map.put("age", String.valueOf(simple.getAge()));
        map.put("gender", simple.getGender().name());
        return map;
    }

}
