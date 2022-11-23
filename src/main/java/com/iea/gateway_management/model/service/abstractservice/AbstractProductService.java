package com.iea.gateway_management.model.service.abstractservice;

import com.google.gson.JsonElement;
import com.iea.gateway_management.channel.model.repository.ProductServiceCallable;
import com.iea.gateway_management.model.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;

// ***13 -> ProductServiceCallable
public abstract class AbstractProductService implements EntityService<JsonElement, Integer>
{
    @Autowired
    protected ProductServiceCallable productServiceCallable;
}
