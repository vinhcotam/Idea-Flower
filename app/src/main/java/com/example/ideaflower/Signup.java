package com.example.ideaflower;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Intent intent = getIntent();
        ConnectDB();
        createEvent();

    }
    String email;
    String password;
    String name;
    TextView textView;
    EditText editText;
    void createEvent(){
        Button button = (Button) findViewById(R.id.signup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = (EditText) findViewById(R.id.sign_email);
                email = editText.getText().toString();
                editText = (EditText) findViewById(R.id.sign_password);
                password = editText.getText().toString();
                editText = (EditText) findViewById(R.id.sign_name);
                name = editText.getText().toString();
                boolean isOK = true;
                if(email.equals("")){
                    isOK=false;
                    textView = findViewById(R.id.checkemail);
                    textView.setText("Bạn chưa nhập email");
                }
                if(password.equals("")){
                    isOK=false;
                    textView = findViewById(R.id.checkpassword);
                    textView.setText("Bạn chưa nhập mật khẩu");
                }
                if(name.equals("")){
                    name="Vô Danh";
                }
                if(!isOK){
                    return;
                }
                String sql = "select * from Account where Email='"+email+"'";
                Cursor cursor = db.rawQuery(sql, null);
                cursor.moveToNext();
                try{
                    cursor.getString(0);
                }
                catch(Exception e){
                    sql = "insert into Account values('"+email+"','"+ password+"','"+name+"')";
                    db.execSQL(sql);
                    Toast.makeText(Signup.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                    return;
                }
                textView = findViewById(R.id.checkemail);
                textView.setText("Email này đã tồn tại");
            }
        });
    }
    SQLiteDatabase db=null;
    void ConnectDB(){
        db=openOrCreateDatabase("FlowerStore.db", MODE_PRIVATE, null);
        //tạo table nếu chưa có
        String sql = "create table if not exists Account(Email char(50) primary key, Password char(50), Name char(50))";
        db.execSQL(sql);
    }
}