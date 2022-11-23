package com.iea.gateway_management.model.service;

import com.google.gson.JsonElement;
import com.iea.gateway_management.channel.utility.RetrofitUtil;
import com.iea.gateway_management.model.service.abstractservice.AbstractTransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService extends AbstractTransactionService
{
    @Override
    public List<JsonElement> getAllTransactionsOfUser(Integer userID)
    {
        return RetrofitUtil.callGenericBlock(transactionServiceCallable.getAllTransactionsOfAuthorizedUser(userID));
    }

    @Override
    public List<JsonElement> getAll()
    {
        return null;
    }

    @Override
    public JsonElement save(JsonElement entity)
    {
        return RetrofitUtil.callGenericBlock(transactionServiceCallable.save(entity));
    }

    @Override
    public void deleteByID(Integer id)
    {
        RetrofitUtil.callGenericBlock(transactionServiceCallable.deleteByID(id));
    }
}
