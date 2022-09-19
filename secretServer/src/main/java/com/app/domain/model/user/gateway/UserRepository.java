package com.app.domain.model.user.gateway;

import com.app.domain.model.user.User;

public interface UserRepository {

    void saveUser(User user );
}
