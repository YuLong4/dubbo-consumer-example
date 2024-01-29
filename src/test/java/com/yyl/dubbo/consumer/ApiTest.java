package com.yyl.dubbo.consumer;

import com.alibaba.fastjson.JSON;
import com.yyl.dubbo.api.IUserService;
import com.yyl.dubbo.api.dto.UserReqDTO;
import com.yyl.dubbo.api.dto.UserResDTO;
import com.yyl.dubbo.api.types.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiTest {

    //dubbo直连模式 @DubboReference(interfaceClass = IUserService.class, url = "dubbo://127.0.0.1:20881", version = "1.0.0")
    //注册中心模式
    @DubboReference(interfaceClass = IUserService.class, version = "1.0.0")
    private IUserService userService;

    @Test
    public void test_userService(){
        UserReqDTO userReqDTO = UserReqDTO.builder().userId("10001").build();
        Response<UserResDTO> userResDTOResponse = userService.queryUserInfo(userReqDTO);
        log.info("测试结果: req:{}  res:{}", JSON.toJSONString(userReqDTO), JSON.toJSONString(userResDTOResponse));
    }

}
