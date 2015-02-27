package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.init;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.PrefManager;

/**
 * Created by BayarkhuuWork on 2/27/2015.
 */
public class TermOfUseActivity extends Activity {
    private PrefManager prefManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);

        launchSetupActivity();
    }
    private void launchSetupActivity() {
        Intent intent = new Intent(this, SetupUserDataActivity.class);
        prefManager.setInitWalkThroughViewed(true);
        startActivity(intent);
        finish();
    }
}
