package com.app.config;

import com.enzoic.client.Enzoic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VerifyConfig {

    @Bean
    public Enzoic enzoic(@Value("${enzoic.api_key}") String api_key,@Value("${enzoic.api_secret}")  String api_secret){
        return new Enzoic(api_key, api_secret);
    }
}
