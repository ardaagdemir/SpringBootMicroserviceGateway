package com.iea.gateway_management.model.service.abstractservice;

import com.iea.gateway_management.model.entity.User;
import com.iea.gateway_management.security.jwt.JWTProvidable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

// ***11 -> AuthenticationService
public abstract class AbstractAuthenticationService
{
    @Autowired
    protected JWTProvidable providable;

    @Autowired
    protected AuthenticationManager authenticationManager;

    public abstract String signInAndReturnJWT(User signInUser);
}
