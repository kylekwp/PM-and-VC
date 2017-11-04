package com.apress.gerber.helloworld;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hxxxbxxhpvds on 4/11/2017.
 */

public class PrivilegeItemAdapter extends ArrayAdapter<PrivilegeItem>{

    private int resourceID;

    public PrivilegeItemAdapter(@NonNull Context context, @LayoutRes int resource, List<PrivilegeItem> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PrivilegeItem pi = getItem(position); // getting the item view

        View view = LayoutInflater.from(getContext()).inflate(resourceID, null);

        ImageView piImage = (ImageView) view.findViewById(R.id.imgViewPriLisItem);
        TextView piName = (TextView) view.findViewById(R.id.TextView_privilegeName);
        TextView piwhoOwns = (TextView) view.findViewById(R.id.TextView_whoOwns);

        piImage.setImageResource(pi.getImageID());
        piName.setText(pi.getName());
        piwhoOwns.setText(pi.getWhoOwns());

        return view;
    }

}
