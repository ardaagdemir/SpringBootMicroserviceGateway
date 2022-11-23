package com.iea.gateway_management.utility;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Util
{
    private Util() {}

    public static void writeToLogFile(Class<?> clazz, Level level, String message)
    {
        Logger logger = Logger.getLogger(clazz.getPackage().getName());

        try
        {
            Handler handler = new FileHandler("D:/log/Spring Boot/IEA/microservices.log");

            logger.addHandler(handler);

            logger.log(level, message);
        }
        catch (IOException e)
        {
            System.err.println("Message: " + createGeneralExceptionMessage(e));
        }
        catch (SecurityException e)
        {
            System.err.println("Message: " + createGeneralExceptionMessage(e));
        }
        catch (IllegalArgumentException e)
        {
            System.err.println("Message: " + createGeneralExceptionMessage(e));
        }
    }

    public static String createGeneralExceptionMessage(Exception e)
    {
        return e.getClass().getSimpleName() + " has been occured. " +
                "Exception message: " + e.getMessage();
    }

    public static void showGeneralExceptionMessage(Exception e) {
        System.err.println(e.getClass().getSimpleName() + " has been occured. " +
                "Exception message: " + e.getMessage());
    }

}
