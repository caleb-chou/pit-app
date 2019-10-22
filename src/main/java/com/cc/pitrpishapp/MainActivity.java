package com.cc.pitrpishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = (Button) findViewById(R.id.test);
        final TextView result = (TextView) findViewById(R.id.result);

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://www.google.com";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    public void onResponse(String response) {
                        // Handle Response
                        result.setText(response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError E) {
                        // Handle Errors
                        result.setText("Error:" + E.getMessage());
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> data = new HashMap<String, String>();
                        data.put("submit", "a");
                        return data;
                    }
                };
                queue.add(stringRequest);
            }

        });
    }
    private void handleRequest() {

    }
}
