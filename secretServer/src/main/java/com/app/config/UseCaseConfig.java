package com.app.config;

import com.app.domain.usecases.auth.signUpUseCase.SignUpUseCase;
import com.app.infraestructure.portsadapters.rds.adapter.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {


    @Bean
    public SignUpUseCase signUpUseCase(UserRepositoryAdapter userRepository){

         return new SignUpUseCase(userRepository);
    }

}