package com.iea.gateway_management.model.service;

import com.iea.gateway_management.model.entity.User;
import com.iea.gateway_management.model.service.abstractservice.AbstractAuthenticationService;
import com.iea.gateway_management.security.model.UserPrinciple;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

// ***12 -> AbstractProductService
@Service
public class AuthenticationService extends AbstractAuthenticationService
{
    @Override
    public String signInAndReturnJWT(User signInUser)
    {
        // ***d
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(signInUser.getUsername(), signInUser.getPassword());

        // ***c
        Authentication authentication
                = authenticationManager.authenticate(token);

        // ***b
        // kimligi dogrulanmis kullanici cekilir
        UserPrinciple userPrinciple = (UserPrinciple)authentication.getPrincipal();

        // ***a
        // kimligi dogrulanmis kullaniciyi kullanarak token urettik
        return providable.generateToken(userPrinciple);
    }
}
