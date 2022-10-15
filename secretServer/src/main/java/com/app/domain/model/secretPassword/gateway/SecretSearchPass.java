package com.app.domain.model.secretPassword.gateway;

import com.app.domain.model.secretPassword.secretFindRequestDTO;
import com.app.domain.model.secretPassword.secretFindResponseDTO;
import reactor.core.publisher.Mono;

import java.util.List;


public interface SecretSearchPass {
    Mono<List<secretFindResponseDTO>> find(secretFindRequestDTO findRequestDTO);
}
