package com.example.userproyect;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Adapter.UserAdapter;
import butterknife.BindView;

import static com.example.userproyect.UserListActivity.EXTRA_ARRAY;
import static com.example.userproyect.UserListActivity.EXTRA_BUNDLE;
import static com.example.userproyect.UserListActivity.EXTRA_USER;

public class DetailActivity extends ButterknifeBind implements UserAdapter.Userlistener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.userImageIv)
    ImageView largeUserIv;
    @BindView(R.id.nameTv)
    TextView nameTv;
    @BindView(R.id.emailTv)
    TextView emailTv;
    @BindView(R.id.locationTv)
    TextView locationTv;
    @BindView(R.id.otherUsersTv)
    TextView otherUsersTv;
    @BindView(R.id.listviewdetail)
    ListView listView;

    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(EXTRA_USER);
        Bundle bndl = intent.getBundleExtra(EXTRA_BUNDLE);
        users = (ArrayList<User>) bndl.getSerializable(EXTRA_ARRAY);
        UserAdapter adapter = new UserAdapter(users, this, this);
        listView.setAdapter(adapter);
        otherUsersTv.setText(R.string.otherusers);
        initializeView(user);
        setBackgroundColor();
        setToolbar();
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.userdetail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setBackgroundColor() {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getColor(R.color.colorPrimary));
    }

    public void initializeView(User user) {
        nameTv.setText(String.format(getString(R.string.concat3), user.getName().getTitle(), user.getName().getFirst(), user.getName().getLast()));
        emailTv.setText(String.format(getString(R.string.concat2), getString(R.string.email), user.getEmail()));
        locationTv.setText(String.format(getString(R.string.concat3), getString(R.string.location), user.getLocation().getCity(), user.getLocation().getState()));
        Picasso.with(this).load(user.getPicture().getLarge()).into(largeUserIv);

    }

    @Override
    //save the contact id to pass it to ContactsActivity
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_detail;
    }

    @Override
    public void onclick(User user) {
        initializeView(user);
    }
}
