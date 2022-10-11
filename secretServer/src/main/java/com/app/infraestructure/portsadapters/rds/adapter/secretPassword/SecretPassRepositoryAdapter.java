package com.app.infraestructure.portsadapters.rds.adapter.secretPassword;

import com.app.config.BusinessException;
import com.app.config.ConnectionManager;
import com.app.config.DataBaseConfig;
import com.app.domain.model.secretPassword.gateway.SecretPasswordRepository;
import com.app.domain.model.secretPassword.secretPassword;
import com.app.domain.model.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public class SecretPassRepositoryAdapter implements SecretPasswordRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataBaseConfig dbConfig;

    @Override
    public Mono<Void> save(secretPassword secretPassword) {
        StringBuilder sql = new StringBuilder();
        MapSqlParameterSource source = new MapSqlParameterSource();

        sql.append(dbConfig.getCreateSecretPass());
        source.addValue("in_secret_password_id", secretPassword.getId());
        source.addValue("in_secret_password_name", secretPassword.getName());
        source.addValue("in_secret_password_username", secretPassword.getUsername());
        source.addValue("in_secret_password_password", secretPassword.getPassword());
        source.addValue("in_secret_password_URI", secretPassword.getURI());
        source.addValue("in_user_uid_fk", secretPassword.getUser_uid());

        try{
            int insert = jdbcTemplate.update(sql.toString(), source);
            if (insert > 0){
                System.out.println("Se realiza insert");
            } else {
                System.out.println("No se realiza insert");
            }
        }catch(Exception e){
            throw new BusinessException(Constant.ERROR_SECRET_PASS_CODE);
        }finally {
            ConnectionManager.closeJdbc(jdbcTemplate);
        }

        return Mono.empty();
    }

}
