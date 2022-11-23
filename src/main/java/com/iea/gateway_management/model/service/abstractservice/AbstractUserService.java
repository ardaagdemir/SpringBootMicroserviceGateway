package com.iea.gateway_management.model.service.abstractservice;

import com.iea.gateway_management.model.entity.User;
import com.iea.gateway_management.model.repository.UserRepository;
import com.iea.gateway_management.model.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public abstract class AbstractUserService implements EntityService<User, Integer>
{
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    public abstract Optional<User> findByUsername(String username);
}
