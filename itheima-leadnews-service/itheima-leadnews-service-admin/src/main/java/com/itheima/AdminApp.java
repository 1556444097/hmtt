package com.itheima;

/**
 * @version 1.0
 * @description 说明
 * @package com.itheima
 */
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//mybatis的mapper扫包
@MapperScan(basePackages = "com.itheima.admin.mapper")
public class AdminApp {

    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class, args);
    }

    // Mybatis Plus 分页插件, 版本不一样而已
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
