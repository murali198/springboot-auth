package com.murali.jwt;

import com.murali.jwt.core.Properties;
import com.murali.jwt.entity.AppUser;
import com.murali.jwt.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories
public class JWTAuthApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JWTAuthApplication.class, args);
    }

    @Bean(name = "jwtProperty")
    public Properties jwtConfig() throws IOException {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("jwt-config.properties"));
        bean.afterPropertiesSet();
        return new Properties(bean.getObject());
    }

    @Autowired
    private AppUserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... strings) throws Exception {
        Iterable<AppUser> custList =  Arrays.asList(
                AppUser.builder().user("admin").password(encoder.encode("admin")).build(),
                AppUser.builder().user("root").password(encoder.encode("root")).build()
        );
        repository.saveAll(custList);
    }
}
