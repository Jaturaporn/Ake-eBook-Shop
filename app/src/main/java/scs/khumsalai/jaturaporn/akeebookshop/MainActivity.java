package scs.khumsalai.jaturaporn.akeebookshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {



    //Explicit
    private EditText userEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //    ดึงหน้ากากมาครอบหน้า
    }   // Main Method

    //อันนี้ พิมพ์เอง
    public void clickSignupMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));  // เพื่อสั่งให้ เมื่อคลิกแล้วให้ Intent  ไปที่อีก Activity หนึ่ง ที่ชื่อ SignupActivity

    }
}   // Main Class
