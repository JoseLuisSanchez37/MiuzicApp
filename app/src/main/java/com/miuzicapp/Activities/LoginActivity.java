package com.miuzicapp.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.miuzicapp.Interfaces.ListenerVolleyResponse;
import com.miuzicapp.Networking.RequestType;
import com.miuzicapp.Networking.VolleyManager;
import com.miuzicapp.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends ActionBarActivity implements ListenerVolleyResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        makeRequest(RequestType.GET_NEWS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void makeRequest(RequestType method) {
        VolleyManager.getInstance().setListener(this);
        VolleyManager.getInstance().setActivity(this);
        Map<String, String> map = new HashMap<>();
        map.put("q", "London,uk");
        VolleyManager.getInstance().sendRequest(method, map);
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.v("onResponse -->", response.toString());
    }

}
