package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.userproyect.R;
import com.example.userproyect.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class UserAdapter extends BaseAdapter {
    private ArrayList<User> users;
    private Context context;
    private Userlistener userlistener;


    public UserAdapter(ArrayList<User> users, Context context, Userlistener userlistener) {
        this.users = users;
        this.context = context;
        this.userlistener = userlistener;
    }

    public void setUserlistener(Userlistener userlistener) {
        this.userlistener = userlistener;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        final User user = users.get(i);

        final View userView = inflater.inflate(R.layout.thumbnailview, null);

        ImageView thumbnail = userView.findViewById(R.id.userIv);
        TextView name = userView.findViewById(R.id.nameInListTv);
        Picasso.with(context).load(user.getPicture().getThumbnail()).resize(120, 120).into(thumbnail);
        CardView parent = userView.findViewById(R.id.linearLayoutParent);
        name.setText(user.getName().getFirst() + "  " + user.getName().getLast());

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlistener.onclick(user);
            }
        });

        return userView;
    }

    //interface OnClick
    public interface Userlistener {
        void onclick(User user);
    }
}
