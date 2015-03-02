package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.contact;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import materialhappy.happybaby.com.materialhappy.R;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.db.DatabaseHelper;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.entities.Contact;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.Constants;

/**
 * Created by BayarkhuuWork on 2/15/2015.
 */
public class ContactCategoryFragment extends Fragment implements Constants{

    private DatabaseHelper databaseHelper = null;
    private Dao<Contact, Integer> contactDAO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        LinearLayout hospitalContainer = (LinearLayout) rootView.findViewById(R.id.hospitalContainer);
        TextView hospitalCatListing= (TextView) rootView.findViewById(R.id.hospitalCatListing);
        LinearLayout doctorContainer = (LinearLayout) rootView.findViewById(R.id.doctorContainer);
        TextView doctorCatListing= (TextView) rootView.findViewById(R.id.doctorCatListing);
        LinearLayout masseurContainer = (LinearLayout) rootView.findViewById(R.id.masseurContainer);
        TextView masseurCatListing= (TextView) rootView.findViewById(R.id.masseurCatListing);


        try {
            contactDAO = getHelper().getContactDao();
            QueryBuilder<Contact, Integer> contactQb = contactDAO
                    .queryBuilder();
            Where whereType1 = contactQb.where();
            whereType1.eq("type", CONTACT_HOSPITAL);
            hospitalCatListing.setText(getResources().getString(R.string.catListingNum, contactQb.query().size()));

            Where whereType2 = contactQb.where();
            whereType2.eq("type", CONTACT_DOCTOR);
            doctorCatListing.setText(getResources().getString(R.string.catListingNum, contactQb.query().size()));

            Where whereType3 = contactQb.where();
            whereType3.eq("type", CONTACT_MASSEUR);
            masseurCatListing.setText(getResources().getString(R.string.catListingNum, contactQb.query().size() ));

        } catch (SQLException e) {
            e.printStackTrace();
        }


        hospitalContainer.setOnClickListener(listener);
        doctorContainer.setOnClickListener(listener);
        masseurContainer.setOnClickListener(listener);

        return rootView;
    }

    View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            int type = CONTACT_HOSPITAL;
            switch (v.getId())
            {
                case R.id.hospitalContainer:
                type = CONTACT_HOSPITAL;
                    break;

                case R.id.doctorContainer:
                    type = CONTACT_DOCTOR;
                    break;

                case R.id.masseurContainer:
                    type = CONTACT_MASSEUR;
                    break;

                default:
                    type = CONTACT_HOSPITAL;
                    break;
            }
            Toast.makeText(getActivity(),type+"",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(), ContactListActivity.class);
            intent.putExtra("type",type);
            startActivity(intent);

        }
    };

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getActivity(),
                    DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
