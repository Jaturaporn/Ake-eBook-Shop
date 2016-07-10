package scs.khumsalai.jaturaporn.akeebookshop;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends AppCompatActivity {

    // Explicit
    private TextView textView;
    private ListView listView;
    private String nameString, surnameString,urlJSON;

    //private static final String urlJSON = "http://swiftcodingthai.com/9july/get_product_master.php"


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);


        // setup Constant
        MyConstant myConstant = new MyConstant();
        urlJSON = myConstant.getUrlJSONproduct();


        // Initial Widget
        textView = (TextView) findViewById(R.id.textView7); // = textView.findview ...
        listView = (ListView) findViewById(R.id.listView);


        //Show View
        nameString = getIntent().getStringExtra("Name");
        surnameString = getIntent().getStringExtra("Surname");
        textView.setText("Welcome " + nameString + " " + surnameString);


        // Syn And Create ListView
        SynProduct synProduct = new SynProduct(this, urlJSON, listView);
        synProduct.execute();

    }   // Main Method

    private class SynProduct extends AsyncTask<Void, Void, String> {
        //explicit
        private Context context;
        private String myURL;
        private ListView myListView;
        private String[] bookStrings,priceStrings,iconStrings;



        public SynProduct(Context context, String myURL, ListView myListView) {
            this.context = context;
            this.myURL = myURL;
            this.myListView = myListView;
        } // SyncProduct


        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(myURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                Log.d("ShopV2", "e doInBack -->");
                return null;
            }

        } // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("ShopV2", "JSON -->" + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                // จองหน่วยความจำ เท่ากับ Length ของ JSON
                bookStrings = new String[jsonArray.length()];
                priceStrings = new String[jsonArray.length()];
                iconStrings = new String[jsonArray.length()];

                for (int i=0;i<jsonArray.length();i+=1) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    bookStrings[i] = jsonObject.getString("Name");
                    priceStrings[i] = jsonObject.getString("Price");
                    iconStrings[i] = jsonObject.getString("Cover");

                }   // For

                MyAdapter myAdapter = new MyAdapter(context, bookStrings,
                        priceStrings, iconStrings);
                myListView.setAdapter(myAdapter);
U

            } catch (Exception e) {
                Log.d("ShopV2", "e onPost -->" + e.toString());
            }


        } // onPost

    }   //SynProduct Class



}   // Main Class
