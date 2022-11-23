package com.iea.gateway_management.model.service.abstractservice;

import com.google.gson.JsonElement;
import com.iea.gateway_management.channel.model.repository.TransactionServiceCallable;
import com.iea.gateway_management.model.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// ***17 -> TransactionService
public abstract class AbstractTransactionService implements EntityService<JsonElement, Integer>
{
    @Autowired
    protected TransactionServiceCallable transactionServiceCallable;

    public abstract List<JsonElement> getAllTransactionsOfUser(Integer userID);
}
