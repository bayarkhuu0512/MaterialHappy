package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.contact;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.Constants;

/**
 * Created by Aagii on 3/2/2015.
 */
public class ContactDetailActivity extends Activity implements Constants {
    private DatabaseHelper databaseHelper = null;
    private Dao<Contact, Integer> contactDAO;
    private Contact contact;
    private TextView name;
    private ImageView imageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail);
        name = (TextView) findViewById(R.id.name);
        imageContainer = (ImageView) findViewById(R.id.imageContainer);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        try {
            contactDAO = getHelper().getContactDao();
            QueryBuilder<Contact, Integer> contactQb = contactDAO
                    .queryBuilder();
            Where where = contactQb.where();
            where.eq("id", id);
            contact = contactQb.queryForFirst();
//            name.setText(contact.getName());
//            if (!contact.getImageName().isEmpty()) {
//                Drawable d = Drawable.createFromPath(getApplicationInfo().dataDir + "/app_" + CONTACT_IMAGES_DIR + "/" + contact.getImageName());
//                imageContainer.setImageDrawable(d);
//            }
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
