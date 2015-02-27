package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.init;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import materialhappy.happybaby.com.materialhappy.R;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.PrefManager;

public class WalkthroughActivity extends Activity {
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walkthrough);
        prefManager = new PrefManager(this);
        launchTermOfUseActivity();
    }
    private void launchTermOfUseActivity() {
        Intent intent = new Intent(this, TermOfUseActivity.class);
        prefManager.setInitWalkThroughViewed(true);
        startActivity(intent);
        finish();
    }
}
