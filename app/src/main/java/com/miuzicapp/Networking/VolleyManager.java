package com.miuzicapp.Networking;

import android.app.Activity;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.miuzicapp.Interfaces.ListenerVolleyResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jose.sanchez on 06/09/2015.
 */
public class VolleyManager implements
        Response.Listener<JSONObject>,
        Response.ErrorListener{


    private static final String API = "http://api.openweathermap.org/data/2.5/weather";

    private static VolleyManager volleyManager;
    private Activity activity;
    private RequestType request;
    private ListenerVolleyResponse listener;

    public static synchronized VolleyManager getInstance(){
        if (volleyManager == null){
            volleyManager = new VolleyManager();
        }
        return volleyManager;
    }

    private VolleyManager(){ }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    public void setListener(ListenerVolleyResponse listener){
        this.listener = listener;
    }

    public void sendRequest(RequestType request, Map<String, String> params){
        this.request = request;
        JSONRequest jsonRequest = new JSONRequest(Request.Method.GET, url(params), null, this, this);
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(5000, 2, 1f));
        VolleyController.getInstance().addToRequestQueue(jsonRequest);
    }

    private String url(Map params){
        //String url = API + request.getMethod();
        return API + getParams(params);
    }

    @Override
    public void onResponse(JSONObject response) {
        listener.onResponse(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        try {
            JSONObject jsonError = new JSONObject();
            jsonError.put("error", VolleyErrorHelper.getMessage(error, activity));
            listener.onResponse(jsonError);
        } catch (JSONException e) {}
    }

    public String getParams(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        for(String key : params.keySet()) {
            Object value = params.get(key);
            if (value != null) {
                try {
                    value = URLEncoder.encode(String.valueOf(value), StandardCharsets.UTF_8.name());
                    if (builder.length() > 0)
                        builder.append("&");
                    builder.append(key).append("=").append(value);
                } catch (UnsupportedEncodingException e) {  }
            }
        }
        return "?" + builder.toString();
    }
}
