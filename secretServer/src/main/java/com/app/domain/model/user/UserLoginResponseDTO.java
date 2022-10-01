package com.app.domain.model.user;

import lombok.Data;

@Data
public class UserLoginResponseDTO {

      private String name;
      private String email;
      private String token;

      public UserLoginResponseDTO() {}

      public UserLoginResponseDTO(String name, String email, String token) {

            this.name = name;
            this.email = email;
            this.token = token;

      }
}
