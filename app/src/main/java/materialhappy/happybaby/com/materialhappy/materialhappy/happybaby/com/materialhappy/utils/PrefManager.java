package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class PrefManager implements Constants {

	public String TAG = PrefManager.class.getName();

	SharedPreferences pref;
	public static PrefManager instance;
	Editor editor;
	Context _context;

	// Constructor
	public PrefManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, 0);
		editor = pref.edit();
	}

	public static PrefManager getSessionInstance() {
		return instance;
	}

	public static void setSessionInstance(PrefManager session) {
		instance = session;
	}

	/**
	 * Clear session details
	 * */
	public void logoutUser() {
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();
	}

	public boolean getInitDataPrepared() {
		return pref.getBoolean(PREF_INIT_DATAPREPARED, false);
	}

	public void setInitDataPrepared(boolean prepared) {
		editor.putBoolean(PREF_INIT_DATAPREPARED, prepared);
		editor.commit();
	}

    public boolean getInitWalkThroughViewed() {
        return pref.getBoolean(PREF_INIT_WALKTHROUGHVIEWED, false);
    }

    public void setInitWalkThroughViewed(boolean viewed) {
        editor.putBoolean(PREF_INIT_WALKTHROUGHVIEWED, viewed);
        editor.commit();
    }

    public boolean getInitHasSetup() {
        return pref.getBoolean(PREF_INIT_HASSETUP, false);
    }

    public void setInitHasSetup(boolean setup) {
        editor.putBoolean(PREF_INIT_HASSETUP, setup);
        editor.commit();
    }
}
