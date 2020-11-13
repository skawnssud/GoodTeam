package com.example.app01.home;
 
import javax.servlet.http.HttpServletRequest;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class HomeController {
 
    final static Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @RequestMapping(value="/", method={ RequestMethod.GET, RequestMethod.POST })
    public String home( HttpServletRequest request ) throws Exception {
        JSONObject json = new JSONObject();
        
        json.put("success", true);
        json.put("data", 10);
                
        return json.toString(4);
    }
}