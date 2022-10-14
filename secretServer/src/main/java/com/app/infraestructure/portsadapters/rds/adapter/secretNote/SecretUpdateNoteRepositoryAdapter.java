package com.app.infraestructure.portsadapters.rds.adapter.secretNote;

import com.app.config.BusinessException;
import com.app.config.ConnectionManager;
import com.app.config.DataBaseConfig;
import com.app.domain.model.secretNote.gateway.SecretUpdateNoteRepository;
import com.app.domain.model.secretNote.secretNote;
import com.app.domain.model.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class SecretUpdateNoteRepositoryAdapter implements SecretUpdateNoteRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataBaseConfig dbConfig;

    @Override
    public Mono<String> update(secretNote note) {
        StringBuilder sql = new StringBuilder();
        MapSqlParameterSource source = new MapSqlParameterSource();

        sql.append(dbConfig.getUpdateSecretNote());
        source.addValue("in_secret_note_id", note.getId());
        source.addValue("in_secret_note_name", note.getName());
        source.addValue("in_secret_note_notes", note.getNotes());
        try{
            int update = jdbcTemplate.update(sql.toString(), source);
            if(update < 1){
                return Mono.just(Constant.SUCCESSFUL_UPDATE_ZERO_NOTE_CODE);
            }
        }catch(Exception e){
            throw new BusinessException(Constant.ERROR_SECRET_NOTE_CODE);
        }finally {
            ConnectionManager.closeJdbc(jdbcTemplate);
        }

        return Mono.just(Constant.SUCCESSFUL_UPDATE_NOTE_CODE);
    }
}
