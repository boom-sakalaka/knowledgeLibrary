package com.jiawa.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${test.hello:TEST}")
    private String testHell;
    /**
     * GET POST PUT DELETE
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!" + testHell;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Hello World! Post" + "  " +name;
    }
}
