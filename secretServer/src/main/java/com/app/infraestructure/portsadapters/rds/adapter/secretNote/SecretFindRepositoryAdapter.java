package com.app.infraestructure.portsadapters.rds.adapter.secretNote;

import com.app.config.BusinessException;
import com.app.config.ConnectionManager;
import com.app.config.DataBaseConfig;
import com.app.domain.model.secretNote.gateway.SecretFindNoteRepository;
import com.app.domain.model.secretNote.secretFindNoteRequestDTO;
import com.app.domain.model.secretNote.secretFindNoteResponseDTO;
import com.app.domain.model.secretPassword.secretFindResponseDTO;
import com.app.domain.model.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class SecretFindRepositoryAdapter implements SecretFindNoteRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataBaseConfig dbConfig;

    @Override
    public Mono<List<secretFindNoteResponseDTO>> find(secretFindNoteRequestDTO noteRequestDTO) {
        List<secretFindNoteResponseDTO> list;
        StringBuilder sql = new StringBuilder();
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();

        sql.append(dbConfig.getSelectSecretNote());
        sqlParam.addValue("in_StartIndex", noteRequestDTO.getPage());
        sqlParam.addValue("in_Count", noteRequestDTO.getRank());
        sqlParam.addValue("in_user_uid_fk", noteRequestDTO.getUser());

        try{
            list = jdbcTemplate.query(sql.toString(), sqlParam,
                    (rs, rowNum) -> new secretFindNoteResponseDTO(rs.getString("id"),
                            rs.getString("name"),rs.getString("notes"),
                            rs.getInt("contador")));
        }catch(Exception e){
            throw  new BusinessException(Constant.ERROR_SECRET_NOTE_CODE);
        }finally {
            ConnectionManager.closeJdbc(jdbcTemplate);
        }

        if(!list.isEmpty()){
            return Mono.just(list);
        }
        return Mono.empty();
    }
}
