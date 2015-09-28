package com.miuzicapp.Interfaces;

import com.miuzicapp.Networking.RequestType;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jose.sanchez on 06/09/2015.
 */
public interface ListenerVolleyResponse {
    public void onResponse(JSONObject response);
}
