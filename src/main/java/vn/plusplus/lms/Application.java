package vn.plusplus.lms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import vn.plusplus.lms.interceptor.GatewayInterceptor;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAsync
public class Application {
    @Autowired
    GatewayInterceptor gatewayInterceptor;

    public static void main(String[] args) {
        System.out.println("Starting Projiect Server");
        SpringApplication.run(Application.class, args);
        System.out.println("Started Projiect Server");
    }

    @PostConstruct
    public void initData(){
        gatewayInterceptor.initApiCache();
    }
}
