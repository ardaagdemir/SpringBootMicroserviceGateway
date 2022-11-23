package com.iea.gateway_management.utility;

public enum ExceptionMessageType
{
    FIND_BY_ID("Entity could not be found."),
    INSERT("Entity could not be inserted."),
    DELETE("Delete operation could not be successful."),
    UPDATE("Update operation could not be successful."),
    GET_ALL("Entity list could not be created.");

    private String value;

    private ExceptionMessageType(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
