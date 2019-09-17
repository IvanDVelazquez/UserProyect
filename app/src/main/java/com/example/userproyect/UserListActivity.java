package com.example.userproyect;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.UserAdapter;
import butterknife.BindView;

public class UserListActivity extends ButterknifeBind implements UserAdapter.Userlistener {
    @BindView(R.id.userListView)
    ListView userListView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    public static final String EXTRA_USER = "EXTRA_USER";
    public static final String EXTRA_ARRAY = "EXTRA_ARRAY";
    public static final String EXTRA_BUNDLE = "EXTRA_BUNDLE";
    private ArrayList<User> users = new ArrayList<>();
    UserAdapter adapter;
    public Handler handler;
    public View view;
    public boolean isloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new UserAdapter(users, this, this);
        fillArray(adapter);
        setToolbar();
        setBackgroundColor();

    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.userList));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void setBackgroundColor() {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getColor(R.color.colorPrimary));
    }

    public void fillArray(final UserAdapter adapter) {
        String url = "https://randomuser.me/api/?inc=name,picture,email,location&results=70&format=pretty";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String string = object.toString();
                        Gson gson = new Gson();
                        User user = gson.fromJson(string, User.class);
                        users.add(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                userListView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    @Override
    public int getContentView() {
        return (R.layout.activity_user_list);
    }

    @Override
    public void onclick(User user) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_ARRAY, users);
//        detailIntent.putExtra(EXTRA_ARRAY,users);
        detailIntent.putExtra(EXTRA_BUNDLE, bundle);
        detailIntent.putExtra(EXTRA_USER, user);
        startActivity(detailIntent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

}
