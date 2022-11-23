package com.iea.gateway_management.model.service;

import com.iea.gateway_management.model.entity.User;
import com.iea.gateway_management.model.service.abstractservice.AbstractUserService;
import com.iea.gateway_management.utility.Util;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends AbstractUserService
{
    @Override
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    @Override
    public User save(User entity)
    {
        try
        {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            entity.setCreated(new Date());
            return userRepository.save(entity);
        }
        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionMessage(e);
            return new User();
        }
    }

    @Override
    public void deleteByID(Integer id)
    {
        try
        {
            if(id != null)
            {
                userRepository.deleteById(id);
            }
        }
        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionMessage(e);
        }
    }
}
