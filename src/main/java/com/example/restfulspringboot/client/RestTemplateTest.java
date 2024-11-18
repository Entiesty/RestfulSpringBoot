package com.example.restfulspringboot.client;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.restfulspringboot.vo.ResultVo;
import com.example.restfulspringboot.vo.UserVo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestTemplateTest {
    public static void main(String[] args) {
        findUser2("user", 1, 1L, 1L);
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

    public static ResultVo<UserVo> postUser(UserVo userVo) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/user";

        // 使用 HttpEntity 来传递请求体和请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserVo> entity = new HttpEntity<>(userVo, headers);

        // 执行POST请求，返回ResponseEntity
        try {
            ResponseEntity<ResultVo<UserVo>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    new ParameterizedTypeReference<>() {
                    }
            );

            // 获取响应体
            return response.getBody();
        } catch (Exception e) {
            // 处理异常，例如网络错误，服务端错误等
            e.printStackTrace();
            return new ResultVo<>(false, "请求失败", userVo);
        }
    }

    public static void deleteUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/{id}";

        restTemplate.delete(url, id);
    }

    public static void putUser(Long id, UserVo userVo) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/header";

        HttpHeaders headers = new HttpHeaders();
        headers.add("id", id.toString());
        HttpEntity<UserVo> userVoHttpEntity = new HttpEntity<>(userVo, headers);
        restTemplate.put(url, userVoHttpEntity);
    }

    public static UserVo postUser2(UserVo userVo) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user2/annotation";
        ResponseEntity<UserVo> userVoResponseEntity = restTemplate.postForEntity(url, userVo, UserVo.class);
        HttpStatusCode statusCode = userVoResponseEntity.getStatusCode();
        System.out.println(statusCode);
        HttpHeaders headers = userVoResponseEntity.getHeaders();
        System.out.println(headers);

        return userVoResponseEntity.getBody();
    }

    public static List<UserVo> findUser2(String userName, Integer sex, Long start, Long limit) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("sex", sex);
        params.put("start", start);
        params.put("limit", limit);

        String url = "http://localhost:8080/user2/{userName}/{sex}/{start}/{limit}";

        ResponseExtractor<List<UserVo>> responseExtractor = response -> {
            String bodyJson = new String(response.getBody().readAllBytes());
            System.out.println(bodyJson);
            JSONArray jsonArray = JSONArray.parseArray(bodyJson);
            System.out.println(jsonArray);
            System.out.println(jsonArray.toJavaList(UserVo.class));

            return jsonArray.toJavaList(UserVo.class);
        };

        return restTemplate.execute(url, HttpMethod.GET, null, responseExtractor, params);
    }

    public static ResultVo<UserVo> postUser3(UserVo user) {
        var restTmpl = new RestTemplate();
        var url = "http://localhost:8080/user";
        // 请求对象回调
        RequestCallback reqCallback = request -> { // ①
            // 设置请求体类型为JSON
            request.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            // 请求体
            var body = JSON.toJSON(user).toString();
            // 写入请求体
            request.getBody().write(body.getBytes());
        };
        // 创建响应提取器
        ResponseExtractor<ResultVo<UserVo>> respExtractor = response -> {  // ②
            // 获取响应体（JSON类型）
            var bodyJson = new String(response.getBody().readAllBytes());
            // 绑定为fastjson2的JSONObject对象
            var jsonObj = JSONObject.parseObject(bodyJson);
            // 将JSON中的“data”转换为UserVo对象
            var userVo = jsonObj.getJSONObject("data").to(UserVo.class);
            // 整体转换为JSON
            var result = JSONObject.parseObject(bodyJson, ResultVo.class);
            // 设计数据为UserVo对象
            result.setData(userVo);
            return result;
        };
        var result = restTmpl.execute(
                url, HttpMethod.POST, reqCallback, respExtractor, ResultVo.class); // ③
        return result;
    }
}
