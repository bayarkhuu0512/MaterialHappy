package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.init;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import materialhappy.happybaby.com.materialhappy.MainActivity;
import materialhappy.happybaby.com.materialhappy.R;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.PrefManager;

/**
 * Created by BayarkhuuWork on 2/27/2015.
 */
public class SetupUserDataActivity extends Activity{
    private PrefManager prefManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);
        launchMainActivity();
    }
    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        prefManager.setInitHasSetup(true);
        startActivity(intent);
        finish();
    }
}

