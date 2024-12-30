package com.example.demo.controller;

import com.example.demo.utils.ApiResult;
import com.example.demo.utils.RustDll;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {

    @GetMapping("/")
    public ApiResult index() {
        int name = RustDll.INSTANCE.addNumbers(41,3);
        return ApiResult.success(name+"");
    }

    @GetMapping("/hello")
    public ApiResult hello() {
        String xiu = RustDll.INSTANCE.processString("中文");
        return ApiResult.success(xiu);
    }

    @GetMapping("/g")
    public ApiResult greet() {
        String xiu = RustDll.INSTANCE.greet("测试demo");
        return ApiResult.success(xiu);
    }

}
