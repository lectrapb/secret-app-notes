package com.app.infraestructure.portsadapters.rds.adapter;

import com.app.config.BusinessException;
import com.app.config.ConnectionManager;
import com.app.config.DataBaseConfig;
import com.app.domain.model.user.User;
import com.app.domain.model.user.gateway.UserSignUpRepository;
import com.app.domain.model.util.Constant;
import com.app.infraestructure.portsadapters.rds.entities.UserCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryAdapter implements UserSignUpRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private DataBaseConfig dbConfig;
    @Override
    public Mono<Void> save(User user) {

        List<UserCreateDTO> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        MapSqlParameterSource params = new  MapSqlParameterSource();

        sql.append(dbConfig.getCreateUser());
        params.addValue("id",       user.getUid() );
        params.addValue("name",     user.getName() );
        params.addValue("email",    user.getEmail() );
        params.addValue("password", user.getPassword());
        params.addValue("image",    user.getImage());
        params.addValue("google",   user.isGoogle());
        params.addValue("role",     user.getRole());

        try{
            list = jdbcTemplate.query(sql.toString(), params,
                    new BeanPropertyRowMapper<>(UserCreateDTO.class));
        }catch(Exception e){
            throw new BusinessException(Constant.ERROR_SIGNUP_USER_CODE);
        }finally {
            ConnectionManager.closeJdbc(jdbcTemplate);
        }
        return Mono.empty();
    }


}
