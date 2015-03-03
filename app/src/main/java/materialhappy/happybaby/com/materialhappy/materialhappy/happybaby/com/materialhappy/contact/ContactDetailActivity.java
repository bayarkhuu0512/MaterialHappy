package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;

import materialhappy.happybaby.com.materialhappy.R;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.db.DatabaseHelper;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.entities.Contact;

/**
 * Created by Aagii on 3/2/2015.
 */
public class ContactDetailActivity extends Activity {
    private DatabaseHelper databaseHelper = null;
    private Dao<Contact, Integer> contactDAO;
    private Contact contact;
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail);
        name = (TextView) findViewById(R.id.name);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        try {
            contactDAO = getHelper().getContactDao();
            QueryBuilder<Contact, Integer> contactQb = contactDAO
                    .queryBuilder();
            Where where = contactQb.where();
            where.eq("id",id);
            contact = contactQb.queryForFirst();
            name.setText(contact.getName());
        } catch (SQLException e) {
            e.printStackTrace();
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
    public void onDestroy() {
        super.onDestroy();

		/*
		 * You'll need this in your class to release the helper when done.
		 */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
