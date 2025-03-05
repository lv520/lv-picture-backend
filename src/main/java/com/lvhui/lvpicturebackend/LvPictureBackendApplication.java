package com.lvhui.lvpicturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.lvhui.lvpicturebackend.mapper")
//暴露代理  AOP
@EnableAsync
@EnableAspectJAutoProxy(exposeProxy = true)
public class LvPictureBackendApplication {

    public static void main(String[] args) {
        // 运行Spring Boot应用程序
        SpringApplication.run(LvPictureBackendApplication.class, args);
    }
}



