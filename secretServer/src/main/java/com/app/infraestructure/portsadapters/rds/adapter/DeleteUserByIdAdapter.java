package com.app.infraestructure.portsadapters.rds.adapter;

import com.app.config.BusinessException;
import com.app.config.DataBaseConfig;
import com.app.domain.model.user.gateway.UserRemoveRepository;
import com.app.domain.model.util.Constant;
import com.app.infraestructure.portsadapters.rds.entities.UserValidateDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class DeleteUserByIdAdapter implements UserRemoveRepository {


    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DataBaseConfig dbConfig;

    StringBuilder sql;
    MapSqlParameterSource sqlParam;

    @Autowired
    public DeleteUserByIdAdapter(NamedParameterJdbcTemplate jdbcTemplate, DataBaseConfig dbConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.dbConfig = dbConfig;
    }

    @Override
    public Mono<Void> deleteById(String id) {

        sql = new StringBuilder();
        sqlParam = new MapSqlParameterSource();
        sql.append(dbConfig.getDeleteUserById());
        sqlParam.addValue("id", id);

        try{
            jdbcTemplate.query(sql.toString(), sqlParam,
                    new BeanPropertyRowMapper<>(Object.class));
        }catch (Exception ex ){
            throw new BusinessException(Constant.ERROR_REMOVE_USER);
        }


        return Mono.empty();
    }
}
