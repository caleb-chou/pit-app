package com.cc.pitrpishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                        Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError E) {
                        // Handle Errors
                        result.setText("Error:" + E.getMessage());
                    }
                });
                queue.add(stringRequest);
            }

        });
        Button post = (Button) findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String url = "http://172.18.15.52:8080";
                postCommand(getApplicationContext(), "a", url);
            }
        });
    }
    private void handleRequest() {

    }
    private static void postCommand(final Context context, final String data, final String url) {
        RequestQueue q = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                Toast.makeText(context, "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError e) {
                Toast.makeText(context, "Response:  " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("Content-type", data); //Add the data you'd like to send to the server.
                return MyData;
            }
        };
        q.add(sr);
    }
}
