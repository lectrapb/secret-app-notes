package com.app.infraestructure.portsadapters.rds.adapter;

import com.app.config.BusinessException;
import com.app.config.ConnectionManager;
import com.app.config.DataBaseConfig;
import com.app.domain.model.user.User;
import com.app.domain.model.user.gateway.UserSearchRepository;
import com.app.domain.model.util.Constant;
import com.app.infraestructure.portsadapters.rds.entities.UserValidateDTO;
import com.app.infraestructure.portsadapters.rds.mapper.MapperSearchByMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class SearchRepositoryAdapter implements UserSearchRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DataBaseConfig dbConfig;

    List<UserValidateDTO> list ;
    StringBuilder sql;
    MapSqlParameterSource sqlParam;
    @Autowired
    public SearchRepositoryAdapter(NamedParameterJdbcTemplate jdbcTemplate,
                                   DataBaseConfig dbConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.dbConfig = dbConfig;
    }

    @Override
    public Mono<User> findByEmail(String email) {

        sql = new StringBuilder();
        sqlParam = new MapSqlParameterSource();

        sql.append(dbConfig.getSearchUserByEmail());
        sqlParam.addValue("email", email);
        sqlParam.addValue("id", "");

        try{
            list = jdbcTemplate.query(sql.toString(), sqlParam,
                        new BeanPropertyRowMapper<>(UserValidateDTO.class));
        }catch(Exception e){
            throw  new BusinessException(Constant.ERROR_SIGNUP_USER_CODE);
        }finally {
            ConnectionManager.closeJdbc(jdbcTemplate);
        }

        if(!list.isEmpty()){
            return Mono.just(MapperSearchByMail.toModel(list.get(0)));
        }
        return Mono.empty();
    }

    @Override
    public Mono<User> findById(String id) {

        StringBuilder sql = new StringBuilder();
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();

        sql.append(dbConfig.getSearchUserByEmail());
        sqlParam.addValue("email", "");
        sqlParam.addValue("id", id);

        try{
            list = jdbcTemplate.query(sql.toString(), sqlParam,
                    new BeanPropertyRowMapper<>(UserValidateDTO.class));
        }catch(Exception e){
            throw  new BusinessException(Constant.ERROR_SIGNUP_USER_CODE);
        }finally {
            ConnectionManager.closeJdbc(jdbcTemplate);
        }

        if(!list.isEmpty()){
            return Mono.just(MapperSearchByMail.toModel(list.get(0)));
        }
        return Mono.empty();
    }
}
