package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.contact;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import materialhappy.happybaby.com.materialhappy.R;

/**
 * Created by BayarkhuuWork on 2/15/2015.
 */
public class ContactFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        LinearLayout hospitalContainer = (LinearLayout) rootView.findViewById(R.id.hospitalContainer);
        hospitalContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactListActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
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
