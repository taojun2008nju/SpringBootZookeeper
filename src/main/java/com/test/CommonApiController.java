package com.test;

import com.test.bean.CacheTestBean;
import com.test.cache.GuavaCacheManager;
import com.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CommonApiController {

    @Autowired
    private TestService testService;

    @Autowired
    private GuavaCacheManager guavaCacheManager;

    @RequestMapping(value = "/testApi")
    @ResponseBody
    public String testApi() {
        CacheTestBean bean1 = new CacheTestBean();
        bean1.setId("1");
        bean1.setName("testa");
        guavaCacheManager.putCache("1", bean1);
        CacheTestBean object = (CacheTestBean)guavaCacheManager.getCache("1");
        Object object2 = guavaCacheManager.getCache("2");

        String result = testService.test("a");

        log.info("Method:testApi:");
        return "Hello World";
    }

    @RequestMapping(value = "/testRedisQuery")
    @ResponseBody
    public String testRedisQuery() {
        String result = testService.testRedisQuery("a");

        log.info("Method:testRedis:");
        return "Hello World Redis";
    }

    @RequestMapping(value = "/testRedisFlush")
    @ResponseBody
    public String testRedisFlush() {
        String result = testService.testRedisFlush("a");

        log.info("Method:testRedis:");
        return "Hello World Redis";
    }
}