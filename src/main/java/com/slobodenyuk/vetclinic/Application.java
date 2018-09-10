package com.slobodenyuk.vetclinic;

import com.slobodenyuk.vetclinic.controller.ServletExample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

// or java -jar target/VetClinicRest-1.0.jar
@SpringBootApplication
public class Application {
    @Bean
    public ServletRegistrationBean servletBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new ServletExample(), "/servlet");
        bean.setLoadOnStartup(1);
        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
