package com.app.config;

import com.app.domain.model.token.gateway.EncryptService;
import com.app.domain.usecases.secrets.secretDeleteAllUseCase.SecretDeleteAllUseCase;
import com.app.domain.usecases.secrets.secretNoteUseCase.SecretCreateUseCase;
import com.app.domain.usecases.secrets.secretNoteUseCase.SecretDeleteNoteUseCase;
import com.app.domain.usecases.secrets.secretNoteUseCase.SecretFindUseCase;
import com.app.domain.usecases.secrets.secretNoteUseCase.SecretUpdateNoteUseCase;
import com.app.domain.usecases.secrets.secretPassUseCase.SecretDeleteUseCase;
import com.app.domain.usecases.secrets.secretPassUseCase.SecretCreatePassUseCase;
import com.app.domain.model.token.gateway.TokenService;
import com.app.domain.model.user.gateway.UserSearchRepository;
import com.app.domain.usecases.auth.loginUserUseCase.LoginUseCase;
import com.app.domain.usecases.auth.signUpUseCase.SignUpUseCase;
import com.app.domain.usecases.secrets.secretPassUseCase.SecretSearchUseCase;
import com.app.domain.usecases.secrets.secretPassUseCase.SecretUpdateUseCase;
import com.app.domain.usecases.secrets.verifyPassUseCase.VerifyPasswordUseCase;
import com.app.infraestructure.portsadapters.rds.adapter.secretAll.SecretDeleteAllRepositoryAdapter;
import com.app.infraestructure.portsadapters.rds.adapter.secretNote.SecretCreateRepositoryAdapter;
import com.app.infraestructure.portsadapters.rds.adapter.secretNote.SecretDeleteNoteAdapter;
import com.app.infraestructure.portsadapters.rds.adapter.secretNote.SecretFindRepositoryAdapter;
import com.app.infraestructure.portsadapters.rds.adapter.secretNote.SecretUpdateNoteRepositoryAdapter;
import com.app.infraestructure.portsadapters.rds.adapter.secretPassword.SecretDeleteRepositoryAdapter;
import com.app.infraestructure.portsadapters.rds.adapter.secretPassword.SecretPassRepositoryAdapter;
import com.app.infraestructure.portsadapters.rds.adapter.secretPassword.SecretSearchRepositoryAdapter;
import com.app.infraestructure.portsadapters.rds.adapter.UserRepositoryAdapter;
import com.app.infraestructure.portsadapters.rds.adapter.secretPassword.SecretUpdateRepositoryAdapter;
import com.app.infraestructure.portsadapters.rds.adapter.verifyPass.VerifyPassAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {


    @Bean
    public SignUpUseCase signUpUseCase(UserRepositoryAdapter userRepository){

         return new SignUpUseCase(userRepository);
    }

    @Bean
    public SecretCreatePassUseCase secretPassUseCase(SecretPassRepositoryAdapter secretPasswordRepository, EncryptService encryptService) {
        return new SecretCreatePassUseCase(secretPasswordRepository, encryptService);
    }
    @Bean
    public LoginUseCase loginUseCase(UserSearchRepository userSearchRepository, TokenService tokenService){

        return new LoginUseCase(userSearchRepository, tokenService);
    }

    @Bean
    public SecretSearchUseCase searchUseCase(SecretSearchRepositoryAdapter repositoryAdapter){
        return new SecretSearchUseCase(repositoryAdapter);
    }

    @Bean
    public SecretDeleteUseCase deleteUseCase(SecretDeleteRepositoryAdapter deleteRepositoryAdapter){
        return new SecretDeleteUseCase(deleteRepositoryAdapter);
    }

    @Bean
    public SecretUpdateUseCase updateUseCase(SecretUpdateRepositoryAdapter updateRepositoryAdapter, EncryptService encryptService){
        return new SecretUpdateUseCase(updateRepositoryAdapter, encryptService);
    }

    @Bean
    public SecretCreateUseCase createUseCase(SecretCreateRepositoryAdapter createRepositoryAdapter, EncryptService encryptService){
        return new SecretCreateUseCase(createRepositoryAdapter, encryptService);
    }

    @Bean
    public SecretFindUseCase searchNoteUseCase(SecretFindRepositoryAdapter findRepositoryAdapter){
        return new SecretFindUseCase(findRepositoryAdapter);
    }

    @Bean
    public SecretUpdateNoteUseCase updateNoteUseCase(SecretUpdateNoteRepositoryAdapter updateNoteRepositoryAdapter, EncryptService encryptService){
        return new SecretUpdateNoteUseCase (updateNoteRepositoryAdapter, encryptService);
    }

    @Bean
    public SecretDeleteNoteUseCase deleteNoteUseCase(SecretDeleteNoteAdapter deleteNoteAdapter){
        return new SecretDeleteNoteUseCase(deleteNoteAdapter);
    }

    @Bean
    public VerifyPasswordUseCase verifyPasswordUseCase(VerifyPassAdapter passAdapter){
        return new VerifyPasswordUseCase(passAdapter);
    }

    @Bean
    public SecretDeleteAllUseCase deleteAllUseCase(SecretDeleteAllRepositoryAdapter deleteAllAdapter){
        return new SecretDeleteAllUseCase(deleteAllAdapter);
    }

}
