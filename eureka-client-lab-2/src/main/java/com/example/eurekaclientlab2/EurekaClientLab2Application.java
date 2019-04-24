package com.example.eurekaclientlab2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class EurekaClientLab2Application {
    private static Logger log = LoggerFactory.getLogger(EurekaClientLab2Application.class);

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient client;

    @RequestMapping(value = "/instances")
    public String getInstancesRun(){
        ServiceInstance instance = client.choose("a-bootiful-client");
        return instance.getUri().toString();
    }

    @RequestMapping(value = "/builders/{id}", method = RequestMethod.GET)
    public String getEmployee(@PathVariable Long id) {
        String url = getInstancesRun();
        log.info("Getting all details for builders " + id + " from " + url);
        String response = this.restTemplate.exchange(String.format("%s/builders/%s", url, Long.toString(id)),
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, id).getBody();

        log.info("Info about builder: " + response);

        return "Id -  " + id + " \n Builder Details " + response;
    }

    @RequestMapping(value = "/builders", method = RequestMethod.GET)
    public String getEmployees() {
        String url = getInstancesRun();
        log.info("Getting all builders" + " from " + url);
        String response = this.restTemplate.exchange(String.format("%s/builders", url),
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }).getBody();

        return "All builders: \n" + response;
    }

    @RequestMapping(value = "/builders", method = RequestMethod.POST)
    public String createEmployee(@RequestBody String object) {
        String url = getInstancesRun();
        log.info("Posting builder from json from " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(object, headers);

        String response = this.restTemplate.exchange(String.format("%s/builders", url),
                HttpMethod.POST, entity, new ParameterizedTypeReference<String>() {
                }).getBody();

        return "Posted builder: \n" + response;
    }

    @RequestMapping(value = "/builders/{id}", method = RequestMethod.PUT)
    public String updateEmployee(@RequestBody String object, @PathVariable Long id) {
        String url = getInstancesRun();
        log.info("Updating builder from json from " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(object, headers);

        String response = this.restTemplate.exchange(String.format("%s/builders/%s", url, id),
                HttpMethod.PUT, entity, new ParameterizedTypeReference<String>() {
                }, id).getBody();

        return "Updated builder: \n" + response;
    }

    @RequestMapping(value = "/builders/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable Long id) {
        String url = getInstancesRun();
        log.info("Deleting builder from " + url);
        String response = this.restTemplate.exchange(String.format("%s/builders/%s", url, id),
                HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {
                }, id).getBody();

        return "Deleted builder: \n" + response;
    }

    @RequestMapping(value = "/constructions/{id}", method = RequestMethod.GET)
    public String getOrder(@PathVariable Long id) {
        String url = getInstancesRun();
        log.info("Getting all details for construction " + id + " from " + url);
        String response = this.restTemplate.exchange(String.format("%s/orders/%s", url, id),
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, id).getBody();

        log.info("Info about construction: " + response);

        return "Id -  " + id + " \n construction Details " + response;
    }

    @RequestMapping(value = "/constructions", method = RequestMethod.GET)
    public String getOrders() {
        String url = getInstancesRun();
        log.info("Getting all constructions from " + url);
        String response = this.restTemplate.exchange(String.format("%s/constructions", url),
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }).getBody();

        return "All constructions: \n" + response;
    }

    @RequestMapping(value = "/constructions", method = RequestMethod.POST)
    public String createOrder(@RequestBody String object) {
        String url = getInstancesRun();
        log.info("Posting construction from json from " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(object, headers);

        String response = this.restTemplate.exchange(String.format("%s/constructions", url),
                HttpMethod.POST, entity, new ParameterizedTypeReference<String>() {
                }).getBody();

        return "All constructions: \n" + response;
    }

    @RequestMapping(value = "/constructions/{id}", method = RequestMethod.PUT)
    public String updateOrder(@RequestBody String object, @PathVariable Long id) {
        String url = getInstancesRun();
        log.info("Updating construction from json from " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(object, headers);

        String response = this.restTemplate.exchange(String.format("%s/constructions/%s", url, id),
                HttpMethod.PUT, entity, new ParameterizedTypeReference<String>() {
                }, id).getBody();

        return "Updated construction: \n" + response;
    }

    @RequestMapping(value = "/constructions/{id}", method = RequestMethod.DELETE)
    public String deleteOrder(@PathVariable Long id) {
        String url = getInstancesRun();
        log.info("Deleting constructions from " + url);
        String response = this.restTemplate.exchange(String.format("%s/constructions/%s", url, id),
                HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {
                }, id).getBody();

        return "Deleted constructions: \n" + response;
    }

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientLab2Application.class, args);
	}
}
