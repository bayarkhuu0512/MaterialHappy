package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.init;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;

import materialhappy.happybaby.com.materialhappy.HappyApplication;
import materialhappy.happybaby.com.materialhappy.MainActivity;
import materialhappy.happybaby.com.materialhappy.R;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.Constants;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.PrefManager;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.Titanic;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.TitanicTextView;

/**
 * Created by BayarkhuuWork on 2/25/2015.
 */
public class LoadBaseDataSplashScreenActivity extends Activity implements Constants{
    private String TAG=LoadBaseDataSplashScreenActivity.this.getClass().getName();
    private PrefManager prefManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingdatasplashscreen);
        prefManager = new PrefManager(this);

        if(DEBUG)
        {
            prefManager.setInitDataPreparedSetup(false);
        }
        if (prefManager.getInitDataPreparedSetup()) {

            launchWalkthroughActivity();

        } else {
            Titanic titanic = new Titanic();
            TitanicTextView titanic_tv = (TitanicTextView) findViewById(R.id.titanic_tv);
            titanic.start(titanic_tv);


            JsonArrayRequest req = new JsonArrayRequest("http://happybaby.mn/app/contacts.php",
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            Toast.makeText(getApplicationContext(),
                                    response.toString(), Toast.LENGTH_LONG).show();
                            launchWalkthroughActivity();
//                        try {
//                            // Parsing json array response
//                            // loop through each json object
//                            jsonResponse = "";
//                            for (int i = 0; i < response.length(); i++) {
//
//                                JSONObject person = (JSONObject) response
//                                        .get(i);
//
//                                String name = person.getString("name");
//                                String email = person.getString("email");
//                                JSONObject phone = person
//                                        .getJSONObject("phone");
//                                String home = phone.getString("home");
//                                String mobile = phone.getString("mobile");
//
//                                jsonResponse += "Name: " + name + "\n\n";
//                                jsonResponse += "Email: " + email + "\n\n";
//                                jsonResponse += "Home: " + home + "\n\n";
//                                jsonResponse += "Mobile: " + mobile + "\n\n\n";
//
//                            }
//
//                            txtResponse.setText(jsonResponse);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getApplicationContext(),
//                                    "Error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {

                @Override
                protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                    try {
                        String jsonString = new String(response.data, "UTF-8");
                        return Response.success(new JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response));
                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));
                    } catch (JSONException je) {
                        return Response.error(new ParseError(je));
                    }
                }
            };

            HappyApplication.getInstance().getRequestQueue().add(req);
        }
    }

    public void skip(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void launchWalkthroughActivity() {
        Intent intent = new Intent(this, WalkthroughActivity.class);
        prefManager.setInitDataPreparedSetup(true);
        startActivity(intent);
        finish();
    }
}
