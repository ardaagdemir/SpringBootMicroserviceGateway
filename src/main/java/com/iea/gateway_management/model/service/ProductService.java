package com.iea.gateway_management.model.service;

import com.google.gson.JsonElement;
import com.iea.gateway_management.channel.utility.RetrofitUtil;
import com.iea.gateway_management.model.service.abstractservice.AbstractProductService;
import org.springframework.stereotype.Service;

import java.util.List;

// ***15 -> TransactionServiceCallable
@Service
public class ProductService extends AbstractProductService
{
    @Override
    public List<JsonElement> getAll()
    {
        return RetrofitUtil.callGenericBlock(productServiceCallable.getAll());
    }

    @Override
    public JsonElement save(JsonElement entity)
    {
        return RetrofitUtil.callGenericBlock(productServiceCallable.save(entity));
    }

    @Override
    public void deleteByID(Integer id)
    {
        RetrofitUtil.callGenericBlock(productServiceCallable.deleteByID(id));
    }
}
