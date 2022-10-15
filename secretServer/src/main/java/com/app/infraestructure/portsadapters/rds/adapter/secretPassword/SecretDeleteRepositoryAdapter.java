package com.app.infraestructure.portsadapters.rds.adapter.secretPassword;

import com.app.config.BusinessException;
import com.app.config.ConnectionManager;
import com.app.config.DataBaseConfig;
import com.app.domain.model.secretPassword.gateway.SecretDeletePassRepository;
import com.app.domain.model.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class SecretDeleteRepositoryAdapter implements SecretDeletePassRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataBaseConfig dbConfig;

    @Override
    public Mono<String> delete(String id) {
        StringBuilder sql = new StringBuilder();
        MapSqlParameterSource source = new MapSqlParameterSource();

        sql.append(dbConfig.getDeleteSecretPass());
        source.addValue("in_secret_password_id", id);

        try{
            int delete = jdbcTemplate.update(sql.toString(), source);
            if(delete < 1){
                return Mono.just(Constant.SUCCESSFUL_DELETE_ZERO_PASSWORD_CODE);
            }
        }catch(Exception e){
            throw new BusinessException(Constant.ERROR_SECRET_PASS_CODE);
        }finally {
            ConnectionManager.closeJdbc(jdbcTemplate);
        }

        return Mono.just(Constant.SUCCESSFUL_DELETE_PASSWORD_CODE);
    }
}
