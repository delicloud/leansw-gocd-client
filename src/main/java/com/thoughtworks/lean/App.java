package com.thoughtworks.lean;

import com.thoughtworks.lean.gocd.dto.pipeline.Template;
import com.thoughtworks.lean.gocd.impl.GoClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}


@RestController
@RequestMapping("/api")
class ApiGatewayRestController {

    private final RestTemplate restTemplate;

    @Autowired
    private GoClientImpl goClient;

    @Autowired
    public ApiGatewayRestController(
            RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/templates")
    public Collection<Template> templates(){
        return goClient.getAllTemplates();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/templates/{name:.*}")
    public Template getTemplate(@PathVariable("name") String name){
        return goClient.getTemplate(name);
    }
}

