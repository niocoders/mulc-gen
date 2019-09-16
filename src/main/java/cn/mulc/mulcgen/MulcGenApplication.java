package cn.mulc.mulcgen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.mulc.mulcgen.mapper")
public class MulcGenApplication {

    public static void main(String[] args) {
        SpringApplication.run(MulcGenApplication.class, args);
    }

}
