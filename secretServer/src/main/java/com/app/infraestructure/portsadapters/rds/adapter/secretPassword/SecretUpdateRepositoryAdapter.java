package com.app.infraestructure.portsadapters.rds.adapter.secretPassword;

import com.app.config.BusinessException;
import com.app.config.ConnectionManager;
import com.app.config.DataBaseConfig;
import com.app.domain.model.secretPassword.gateway.SecretUpdatePassRepository;
import com.app.domain.model.secretPassword.secretPassword;
import com.app.domain.model.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class SecretUpdateRepositoryAdapter implements SecretUpdatePassRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataBaseConfig dbConfig;

    @Override
    public Mono<String> update(secretPassword password) {
        StringBuilder sql = new StringBuilder();
        MapSqlParameterSource source = new MapSqlParameterSource();

        sql.append(dbConfig.getUpdateSecretPass());
        source.addValue("in_secret_password_id", password.getId());
        source.addValue("in_secret_password_name", password.getName());
        source.addValue("in_secret_password_username", password.getUsername());
        source.addValue("in_secret_password_password", password.getPassword());
        source.addValue("in_secret_password_URI", password.getURI());

        try{
            int update = jdbcTemplate.update(sql.toString(), source);
            if(update < 1){
                return Mono.just(Constant.SUCCESSFUL_UPDATE_ZERO_PASSWORD_CODE);
            }
        }catch(Exception e){
            throw new BusinessException(Constant.ERROR_SECRET_PASS_CODE);
        }finally {
            ConnectionManager.closeJdbc(jdbcTemplate);
        }

        return Mono.just(Constant.SUCCESSFUL_UPDATE_PASSWORD_CODE);
    }
}
