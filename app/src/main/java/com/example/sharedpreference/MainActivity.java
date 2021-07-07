package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameText,passwordText;
    private Button savebutton,loadbutton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText=findViewById(R.id.EditTextId1);
        passwordText=findViewById(R.id.EditTextId2);
        savebutton=findViewById(R.id.Button1Id);
        loadbutton=findViewById(R.id.Button2Id);
        textView=findViewById(R.id.TextViewId);

        savebutton.setOnClickListener(this);
        loadbutton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

      if(view.getId()==R.id.EditTextId1){

          String username=usernameText.getText().toString();
          String password=passwordText.getText().toString();

          if(username.equals("")&&password.equals("")){
              Toast.makeText(getApplicationContext(),"Please Enter some Data",Toast.LENGTH_SHORT).show();
          }

          else {

              SharedPreferences sharedPreferences = getSharedPreferences("User Details", Context.MODE_PRIVATE);
              SharedPreferences.Editor editor = sharedPreferences.edit();
              editor.putString("usernameKey", username);
              editor.putString("passwordKey", password);
              editor.commit();
              Toast.makeText(getApplicationContext(),"Data stored Successfully",Toast.LENGTH_SHORT).show();

          }

      }
      else if(view.getId()==R.id.EditTextId2){

          SharedPreferences sharedPreferences = getSharedPreferences("User Details", Context.MODE_PRIVATE);
          if(sharedPreferences.contains("usernameKey")&& sharedPreferences.contains("passwordKey")){
              String username=sharedPreferences.getString("userKey","Data Not Found");
              String password=sharedPreferences.getString("passwordKey","Data Not Found");
              textView.setText(username+"\n"+password);
          }
        }

    }
}