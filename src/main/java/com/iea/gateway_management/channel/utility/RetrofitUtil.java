package com.iea.gateway_management.channel.utility;

import com.iea.gateway_management.utility.Util;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.logging.Level;

public final class RetrofitUtil
{
    // burası request'i çalıştırıp response üzerinden body üretmek için
    // Callable'lar üzerinden Call<?> döndüren metotlar buraya paslanır
    // ve response'un body'si üretilir.
    public static <T> T callGenericBlock(Call<T> request)
    {
        try
        {
            Response<T> response = request.execute();

            if(!response.isSuccessful())
            {
                String message = "Unsuccessful response. Error reason: " + response.errorBody().string();
                Util.writeToLogFile(RetrofitUtil.class, Level.INFO, message);
            }

            return response.body(); // response uretip body'si getirilir
        }
        catch (IOException e)
        {
            Util.showGeneralExceptionMessage(e);

            return null;
        }
    }
}
