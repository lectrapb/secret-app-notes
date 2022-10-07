package com.app.infraestructure.portsadapters.rds.adapter.secretPassword;

import com.app.config.BusinessException;
import com.app.config.ConnectionManager;
import com.app.config.DataBaseConfig;
import com.app.domain.model.secretPassword.gateway.SecretSearchPass;
import com.app.domain.model.secretPassword.secretFindRequestDTO;
import com.app.domain.model.secretPassword.secretFindResponseDTO;
import com.app.domain.model.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class SecretSearchRepositoryAdapter implements SecretSearchPass {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataBaseConfig dbConfig;
    @Override
    public Mono<List<secretFindResponseDTO>> find(secretFindRequestDTO findRequestDTO) {
        List<secretFindResponseDTO> list;
        StringBuilder sql = new StringBuilder();
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();

        sql.append(dbConfig.getSelectSecretPass());
        sqlParam.addValue("in_StartIndex", findRequestDTO.getPage());
        sqlParam.addValue("in_Count", findRequestDTO.getRank());
        sqlParam.addValue("in_user_uid_fk", findRequestDTO.getUser());

        try{
            list = jdbcTemplate.query(sql.toString(), sqlParam,
                    (rs, rowNum) -> new secretFindResponseDTO(rs.getString("id"),
                            rs.getString("name"),rs.getString("username"),
                            rs.getString("password"), rs.getString("uri"),
                            rs.getInt("contador")));
        }catch(Exception e){
            throw  new BusinessException(Constant.ERROR_SECRET_PASS_CODE);
        }finally {
            ConnectionManager.closeJdbc(jdbcTemplate);
        }

        if(!list.isEmpty()){
            return Mono.just(list);
        }
        return Mono.empty();
    }
}
