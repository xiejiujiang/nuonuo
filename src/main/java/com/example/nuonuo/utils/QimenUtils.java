package com.example.nuonuo.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class QimenUtils {

    public static Map<String, String> getParamsFromUrl(String url)
    {

        Map<String, String> requestParams = null;

        try
        {
            requestParams = new HashMap<String, String>();
            String fullUrl = URLDecoder.decode(url, "UTF-8");

            String[] urls = fullUrl.split("\\?");

            if (urls.length == 2)
            {

                String[] paramArray = urls[1].split("&");

                for (String param : paramArray)
                {

                    String[] params = param.split("=");

                    if (params.length == 2)
                    {

                        requestParams.put(params[0], params[1]);

                    }

                }

            }

        }
        catch (UnsupportedEncodingException e)
        {
            requestParams = null;
        }

        return requestParams;

    }
}
