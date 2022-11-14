package com.app.config;

import com.app.domain.model.password.PasswordEncryptService;
import com.app.domain.model.token.gateway.TokenService;
import com.app.domain.model.user.gateway.UserRemoveRepository;
import com.app.domain.model.user.gateway.UserSearchRepository;
import com.app.domain.usecases.auth.loginUserUseCase.LoginUseCase;
import com.app.domain.usecases.auth.removeUserUseCase.RemoveUserUseCase;
import com.app.domain.usecases.auth.signUpUseCase.SignUpUseCase;
import com.app.domain.usecases.auth.validateByToken.ValidateByTokenUseCase;
import com.app.infraestructure.portsadapters.rds.adapter.SearchRepositoryAdapter;
import com.app.infraestructure.portsadapters.rds.adapter.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {


    @Bean
    public SignUpUseCase signUpUseCase(UserRepositoryAdapter userRepository,  PasswordEncryptService pwdEncy){

         return new SignUpUseCase(userRepository,pwdEncy );
    }

    @Bean
    public LoginUseCase loginUseCase(UserSearchRepository userSearchRepository,
                                                          TokenService tokenService,
                                                          PasswordEncryptService encryptService){

        return new LoginUseCase(userSearchRepository, tokenService, encryptService);
    }

    @Bean
    public ValidateByTokenUseCase validateByTokenUseCase(SearchRepositoryAdapter userSearchRepository,
                                                         TokenService tokenService){
        return new ValidateByTokenUseCase(userSearchRepository, tokenService);
    }

    @Bean
    public RemoveUserUseCase removeUserUseCase(SearchRepositoryAdapter userSearchRepository,
                                               UserRemoveRepository removeRepository,
                                               TokenService tokenService){
        return new RemoveUserUseCase(userSearchRepository, removeRepository, tokenService);
    }

}
