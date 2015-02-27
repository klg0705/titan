package study.gordon.titan.sandbox.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import study.gordon.titan.common.web.MediaTypes;
import study.gordon.titan.showcase.simple.entity.SimpleEntity;

@RestController
@RequestMapping(value = "/api/v1/sandbox")
public class SandboxRestController {

    private static final Logger logger = LoggerFactory.getLogger(SandboxRestController.class);

    @RequestMapping(value = "/case1", method = RequestMethod.POST, consumes = MediaTypes.TEXT_PLAIN)
    public String textToText(@RequestBody String body) {
        logger.info("Test slf4j output in \"{}\" method. Request body is \"{}\".", "textToText", body);
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

    @RequestMapping(value = "/case11", method = RequestMethod.GET)
    public String cacheExpires0(@RequestParam(value = "name") String name, HttpServletResponse response) {
        response.setDateHeader("Expires", 0);
        return "Cache_Expires_0 " + name;
    }

    @RequestMapping(value = "/case12", method = RequestMethod.GET)
    public String cacheExpires5(@RequestParam(value = "name") String name, HttpServletResponse response) {
        response.setDateHeader("Expires", System.currentTimeMillis() + 60 * 1000);
        return "Cache_Expires_1min " + name;
    }

    @RequestMapping(value = "/case13", method = RequestMethod.GET)
    public String cacheControlNoCache(@RequestParam(value = "name") String name, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache"); // winner
        response.setDateHeader("Expires", System.currentTimeMillis() + 60 * 1000);
        return "Cache_Control_no-cache " + name;
    }

    @RequestMapping(value = "/case14", method = RequestMethod.GET)
    public String cacheControlMaxAge(@RequestParam(value = "name") String name, HttpServletResponse response) {
        response.setHeader("Cache-Control", "max-age=60"); // winner
        response.setDateHeader("Expires", 0);
        return "Cache_Control_max_age_60sec " + name;
    }

    @RequestMapping(value = "/case15", method = RequestMethod.GET)
    public String cacheLastModified(HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File("d:\\fl\\aFile.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        response.setDateHeader("Last-Modified", file.lastModified());

        String s = request.getHeader("If-Modified-Since");
        if (s != null && s.length() > 10) {
            long last = Date.parse(s) / 1000;
            if (last >= file.lastModified() / 1000) {
                response.setStatus(304);
                return null;
            }
        }
        return "Cache_Last_Modified " + request.getParameter("name");
    }

    @RequestMapping(value = "/case16", method = RequestMethod.GET)
    public String cacheEtag(HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File("d:\\fl\\aFile.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        String etag = file.lastModified() + "";
        response.setHeader("Etag", etag);

        String s = request.getHeader("If-None-Match");
        if (etag.equals(s)) {
            response.setStatus(304);
            return null;
        }
        return "Cache_Etag " + etag;
    }

}
