package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.contact;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import materialhappy.happybaby.com.materialhappy.R;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.entities.Contact;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.Constants;

/**
 * Created by Aagii on 2/27/2015.
 */
public class ContactListAdapter extends
        RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    String LOG_TAG = ContactListAdapter.class.getName();
    private Context mContext;
    private List<Contact> mList;
    private Typeface roboto_light;

    public ContactListAdapter(Context context, List<Contact> list) {
        mContext = context;
        roboto_light = Typeface.createFromAsset(mContext.getAssets(),
                Constants.ROBOTO_LIGHT);
        mList = list;
        Log.d(LOG_TAG, "Mlist " + mList.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.contact_row, parent, false);
        return new ViewHolder(v);
    }

    Contact contact = null;

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        contact = mList.get(position);

        viewHolder.name.setTypeface(roboto_light);
        viewHolder.address.setTypeface(roboto_light);
        viewHolder.type.setTypeface(roboto_light);
        viewHolder.phone1.setTypeface(roboto_light);
        viewHolder.phone2.setTypeface(roboto_light);
        viewHolder.phone3.setTypeface(roboto_light);

        viewHolder.name.setText(contact.getName());
        viewHolder.address.setText(contact.getAddress());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView address;
        public TextView type;
        public TextView phone1;
        public TextView phone2;
        public TextView phone3;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            address = (TextView) v.findViewById(R.id.address);
            type = (TextView) v.findViewById(R.id.type);
            phone1 = (TextView) v.findViewById(R.id.phone1);
            phone2 = (TextView) v.findViewById(R.id.phone2);
            phone3 = (TextView) v.findViewById(R.id.phone3);
        }
    }
}