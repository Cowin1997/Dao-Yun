package cn.edu.fzu.daoyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages="cn.edu.fzu.*")
@MapperScan("cn.edu.fzu.dao")
//@CrossOrigin(origins = {"http://localhost:8083"},allowCredentials = "true",
//        allowedHeaders = "*"
//)
public class DaoyunApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaoyunApplication.class, args);
    }

}
