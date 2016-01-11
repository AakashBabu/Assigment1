package com.example.ravi.assigment1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> CountryName = new ArrayList<String>();
    ListAdapter listAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://www.gopasto.com/webservice/api/country/get";

         listView = (ListView) findViewById(R.id.listView);
         new JsonParsing().execute(new String[]{});
    }

    class JsonParsing extends AsyncTask<String , Integer , String>{
        String request;

        @Override
        protected String doInBackground(String... params) {
            String url = "https://www.gopasto.com/webservice/api/country/get";
            try {
                ConnectingWebService obj = new ConnectingWebService();
                request = obj.request(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return request;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject element = new JSONObject(s);
                Log.e("Json",""+element);

                JSONArray elements = element.getJSONArray("countries");
                for(int i=0;i < elements.length(); i++){
                    JSONObject element1 = elements.getJSONObject(i);
                    String C_short = element1.getString("country_short_name");
                    CountryName.add(C_short);
                }

                Log.e("Cont", "" + CountryName.size());

                listAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1 , CountryName);
                listView.setAdapter(listAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
