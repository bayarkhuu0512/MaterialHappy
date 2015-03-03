package materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.contact;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import materialhappy.happybaby.com.materialhappy.R;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.entities.Contact;
import materialhappy.happybaby.com.materialhappy.materialhappy.happybaby.com.materialhappy.utils.Constants;

/**
 * Created by Aagii on 2/27/2015.
 */
public class ContactListAdapter extends
        RecyclerView.Adapter<ContactListAdapter.ViewHolder> implements Constants{
    String LOG_TAG = ContactListAdapter.class.getName();
    private Context mContext;
    private List<Contact> contacts;
    private Typeface roboto_light;

    public ContactListAdapter(Context context, List<Contact> list){
        mContext = context;
        roboto_light = Typeface.createFromAsset(mContext.getAssets(),
                Constants.ROBOTO_LIGHT);
        contacts = list;
        Log.d(LOG_TAG, "Mlist " + contacts.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.contact_row, parent, false);

        return new ViewHolder(v);
    }

    Contact contact = null;

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        contact = contacts.get(position);

        viewHolder.name.setTypeface(roboto_light);
      //  viewHolder.address.setTypeface(roboto_light);
//        viewHolder.type.setTypeface(roboto_light);
//        viewHolder.phone1.setTypeface(roboto_light);
//        viewHolder.phone2.setTypeface(roboto_light);
//        viewHolder.phone3.setTypeface(roboto_light);

        viewHolder.name.setText(contact.getName());

     //   Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.contact_bg1);


       // Bitmap bitmap = BitmapFactory.decodeFile(mContext.getApplicationInfo().dataDir+"app_images/berry-angled2.png");
        Drawable d = Drawable.createFromPath(mContext.getApplicationInfo().dataDir+"/"+CONTACT_IMAGES_DIR+"/berry-angled2.png");
        viewHolder.imageLayout.setBackgroundDrawable(d);
      //  viewHolder.imageLayout.setBackgroundResource(R.drawable.contact_bg1);

        if(position%2==0) {
            viewHolder.imageStatus.setBackgroundResource(R.drawable.contact_offline);
        } else {
            viewHolder.imageStatus.setBackgroundResource(R.drawable.contact_online);
        }
        //    viewHolder.address.setText(contact.getAddress());

        viewHolder.rowContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ContactDetailActivity.class);
                intent.putExtra("id",contacts.get(position).getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout imageLayout;
        public ImageView imageStatus;
        public TextView name;
        public TextView address;
        public TextView type;
        public TextView phone1;
        public TextView phone2;
        public TextView phone3;

        public LinearLayout rowContainer;

        public ViewHolder(View v) {
            super(v);
            rowContainer = (LinearLayout) v.findViewById(R.id.rowContainer);
            name = (TextView) v.findViewById(R.id.name);
            imageLayout = (RelativeLayout) v.findViewById(R.id.imageLayout);
            imageStatus  = (ImageView) v.findViewById(R.id.imageStatus);
//            address = (TextView) v.findViewById(R.id.shortAddress);
//            type = (TextView) v.findViewById(R.id.type);
//            phone1 = (TextView) v.findViewById(R.id.phone1);
//            phone2 = (TextView) v.findViewById(R.id.phone2);
//            phone3 = (TextView) v.findViewById(R.id.phone3);
        }

    }


}