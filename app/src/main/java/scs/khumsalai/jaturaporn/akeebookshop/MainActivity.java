package scs.khumsalai.jaturaporn.akeebookshop;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    //Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private static final String urlJSON = "http://swiftcodingthai.com/9july/get_user_AKE.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //    ดึงหน้ากากมาครอบหน้า

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);


    }   // Main Method






    //Connect ฐานข้อมูล ให้ใช้วิธีนี้ ง่ายสุด

    private class SynUserTABLE extends AsyncTask<Void, Void, String> {


        //Explicit
        // ทำ Constructure คือการทำ Method ที่ขื่อเดียวกับ class
        private Context context;// ใช้ในการเชื่อมต่อ
        private String myURL, myUserString, myPasswordString,truePassword;     // มีค่าเท่ากับตัวแปร ใน Url
        private boolean statusABoolean = true;


        public SynUserTABLE(Context context, String myUserString, String myPasswordString, String myURL) {
            this.context = context;
            this.myUserString = myUserString;
            this.myPasswordString = myPasswordString;
            this.myURL = myURL;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(myURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) { // การ Error ที่ยอมรับได้
                Log.d("ShopV1", "e doInBack -->" + e.toString()); // การเขียน debugger
                return null;
            }


        }   // doInBack

        //ทำการ Override ขึ้นมา Alt + Insert


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("ShopV1", "JSON -->" + s);

            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length();i +=1) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (myUserString.equals(jsonObject.get("User"))) {

                        statusABoolean =false;
                        truePassword = jsonObject.getString("Password");

                    }


                }   // for

                if (statusABoolean) {
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context,"ไม่พบ User นี้","ไม่มี"+ myUserString + "ในฐานข้อมูลผู้ใช้");
                } else if (myPasswordString.equals(truePassword)) {

                    Toast.makeText(context,"Welcome",Toast.LENGTH_SHORT).show();


                } else {
                    MyAlert myAlert =new MyAlert();
                    myAlert.myDialog(context,"Wrong Password",
                            "Please Try Again");
                }


            } catch (Exception e) {
                Log.d("ShopV1", "e onPost-->" + e.toString());

            }

        }// onPost

    }   // SynUser Class


    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {
            //Have Spack
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "Have Space", "Please Fill All Every Blank");

        } else {
            //No Space
            SynUserTABLE synUserTABLE = new SynUserTABLE(this,userString,passwordString,urlJSON); // กด Crl+P เพื่อดู
            synUserTABLE.execute();

        }


    }   // clickSignin


    //อันนี้ พิมพ์เอง
    public void clickSignupMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));  // เพื่อสั่งให้ เมื่อคลิกแล้วให้ Intent  ไปที่อีก Activity หนึ่ง ที่ชื่อ SignupActivity

    }
}   // Main Class
