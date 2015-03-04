package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.init;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import materialhappy.happybaby.com.materialhappy.HappyApplication;
import materialhappy.happybaby.com.materialhappy.R;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.db.DatabaseHelper;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.entities.Contact;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.Constants;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.PrefManager;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.Titanic;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.TitanicTextView;

/**
 * Created by BayarkhuuWork on 2/25/2015.
 */
public class LoadBaseDataSplashScreenActivity extends Activity implements Constants {
    private String TAG = LoadBaseDataSplashScreenActivity.this.getClass().getName();
    private PrefManager prefManager;
    private Dao<Contact, Integer> contactDAO;
    private DatabaseHelper databaseHelper = null;
    private ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingdatasplashscreen);
        image = (ImageView) findViewById(R.id.image);
        prefManager = new PrefManager(this);
        if (DEBUG) {
            prefManager.setInitDataPrepared(false);
        }
        if (prefManager.getInitDataPrepared()) {

            launchWalkthroughActivity();

        } else {
            Titanic titanic = new Titanic();
            TitanicTextView titanic_tv = (TitanicTextView) findViewById(R.id.titanic_tv);
            titanic.start(titanic_tv);

//                                try {
//                                    contactDAO = getHelper().getContactDao();
//                                } catch (SQLException e) {
//                                    e.printStackTrace();
//                                }

            JsonArrayRequest req = new JsonArrayRequest(URL + "/contacts.php",
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            //  try {

                            Log.d(TAG, response.toString());
                            new LongOperation().execute(response);

//                            Toast.makeText(getApplicationContext(),
//                                    response.toString(), Toast.LENGTH_LONG).show();
//                            for (int i = 0; i < response.length(); i++) {
//
//                                JSONObject contactJson = (JSONObject) response
//                                        .get(i);
//
//                                Contact contact = new Contact();
//                                contact.setId(Integer.parseInt(contactJson.getString("id")));
//                                contact.setType(contactJson.getString("type"));
//                                contact.setName(contactJson.getString("name"));
//                                contact.setAddress(contactJson.getString("address"));
//                                contact.setPhone1(contactJson.getString("phonenumber1"));
//                                contact.setPhone2(contactJson.getString("phonenumber2"));
//                                contact.setPhone3(contactJson.getString("phonenumber3"));
//                                Log.d(TAG,"contact "+contact.getName());
////                                try {
////                                    contactDAO.create(contact);
////                                } catch (SQLException e) {
////                                    e.printStackTrace();
////                                }
//                            }

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

    private class LongOperation extends AsyncTask<JSONArray, Void, String> {

        @Override
        protected String doInBackground(JSONArray... params) {
            try {
                // Parsing json array response
                // loop through each json object
                for (int i = 0; i < params[0].length(); i++) {

                    JSONObject contactJson = (JSONObject) params[0]
                            .get(i);

                    final Contact contact = new Contact();
                    contact.setId(Integer.parseInt(contactJson.getString("id")));
                    contact.setType(contactJson.getString("type"));
                    contact.setName(contactJson.getString("name"));
                    contact.setImageName(contactJson.getString("imagename"));
                    contact.setAddress(contactJson.getString("address"));
                    contact.setDistrict(contactJson.getString("district"));
                    contact.setPhone1(contactJson.getString("phonenumber1"));
                    contact.setPhone2(contactJson.getString("phonenumber2"));
                    contact.setPhone3(contactJson.getString("phonenumber3"));
                    try {
                        contactDAO = getHelper().getContactDao();
                        contactDAO.create(contact);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (!contact.getImageName().isEmpty()) {
                        Log.d(TAG, URL + "/img/contact/" + contact.getImageName());
                        ImageRequest ir = new ImageRequest(URL + "/img/contact/" + contact.getImageName(), new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                saveToInternalSorage(response, contact.getImageName());
                            }
                        }, 0, 0, null, null);
                        HappyApplication.getInstance().getRequestQueue().add(ir);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            launchWalkthroughActivity();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this,
                    DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

		/*
         * You'll need this in your class to release the helper when done.
		 */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    private void launchWalkthroughActivity() {
        Intent intent = new Intent(this, WalkthroughActivity.class);
        prefManager.setInitDataPrepared(true);
        startActivity(intent);
        finish();
    }

    private String saveToInternalSorage(Bitmap bitmapImage, String imageName) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir(CONTACT_IMAGES_DIR, Context.MODE_PRIVATE);
        File mypath = new File(directory, imageName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }
}
