package com.example.ravi.assigment1;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectingWebService{

    public String request(String URLL) throws IOException {
        InputStream inputStream = null;

        try {
            String result = " ";
            URL url = new URL(URLL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            String line;

            conn.connect();
            inputStream = conn.getInputStream();
            StringBuilder as = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((line = reader.readLine()) != null ){
                as.append(line);
            }
            result = as.toString();
            Log.w("Result : ", result);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                inputStream.close();
            }
        }

        return null;
    }

}
