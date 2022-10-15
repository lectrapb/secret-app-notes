package com.app.infraestructure.portsadapters.rds.adapter.secretNote;

import com.app.config.BusinessException;
import com.app.config.ConnectionManager;
import com.app.config.DataBaseConfig;
import com.app.domain.model.secretNote.gateway.SecretCreateNoteRepository;
import com.app.domain.model.secretNote.secretNote;
import com.app.domain.model.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class SecretCreateRepositoryAdapter implements SecretCreateNoteRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private DataBaseConfig dataBaseConfig;

    @Override
    public Mono<Void> save(secretNote secretNote) {
        StringBuilder sql = new StringBuilder();
        MapSqlParameterSource source = new MapSqlParameterSource();

        sql.append(dataBaseConfig.getCreateSecretNote());
        source.addValue("in_secret_note_id", secretNote.getId());
        source.addValue("in_secret_note_name", secretNote.getName());
        source.addValue("in_secret_note_notes", secretNote.getNotes());
        source.addValue("in_user_uid_fk", secretNote.getUser_uid());

        try{
            int insert = jdbcTemplate.update(sql.toString(), source);
            if(insert > 0){
                System.out.println("Se realiza insert");
            } else {
                System.out.println("No se realiza insert");
            }
        }catch (Exception e){
            throw new BusinessException(Constant.ERROR_SECRET_NOTE_CODE);
        }finally {
            ConnectionManager.closeJdbc(jdbcTemplate);
        }

        return Mono.empty();
    }
}
