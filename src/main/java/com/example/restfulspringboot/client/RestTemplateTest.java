package com.example.restfulspringboot.client;

import com.example.restfulspringboot.vo.UserVo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestTemplateTest {
    public static void main(String[] args) {
        List<Map<String, Object>> mapList = findUser("user", 1, 0L, 10L);
        System.out.println(mapList);
    }

    public static UserVo getUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user2/{id}";

        return restTemplate.getForObject(url, UserVo.class, id);
    }

    public static List<Map<String, Object>> findUser(String userName, Integer sex, Long start, Long limit) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("sex", sex);
        params.put("start", start);
        params.put("limit", limit);

        String url = "http://localhost:8080/user2/{userName}/{sex}/{start}/{limit}";

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                params
        );

        return response.getBody();
    }
}
