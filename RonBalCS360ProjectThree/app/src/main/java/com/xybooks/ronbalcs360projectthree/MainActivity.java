package com.xybooks.ronbalcs360projectthree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.*;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "users").build();
    }

    public void RegisterClick(View v){
        EditText usernameText = (EditText) findViewById(R.id.editTextUsername);
        EditText passwordText = (EditText) findViewById(R.id.editTextTextPassword);
        String nameTextValue = usernameText.getText().toString();
        String passwordTextValue = passwordText.getText().toString();
        if(!checkLogin(nameTextValue, passwordTextValue)){
            createUser(nameTextValue, passwordTextValue);
        }
    }

    public void LoginClick(View v){
        EditText usernameText = (EditText) findViewById(R.id.editTextUsername);
        EditText passwordText = (EditText) findViewById(R.id.editTextTextPassword);
        String nameTextValue = usernameText.getText().toString();
        String passwordTextValue = passwordText.getText().toString();
        if(checkLogin(nameTextValue, passwordTextValue)){
            setContentView(R.layout.display_all);
        }
    }

    private boolean checkLogin(String username, String password){
        UserDao userDao = db.userDao();
        User user = userDao.findByName(username, password);
        return user != null;
    }

    private void createUser(String username, String password){
        UserDao userDao = db.userDao();
        User user = new User();
        user.userName = username;
        user.password = password;
        userDao.insertAll(user);
    }
}