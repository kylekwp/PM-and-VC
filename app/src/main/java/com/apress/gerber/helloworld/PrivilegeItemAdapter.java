package com.apress.gerber.helloworld;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hxxxbxxhpvds on 4/11/2017.
 */

public class PrivilegeItemAdapter extends ArrayAdapter<PrivilegeItem>{

    private int resourceID;
    private Context myContext;
    private PrivilegeItem pi;

    public PrivilegeItemAdapter(@NonNull Context context, @LayoutRes int resource, List<PrivilegeItem> objects) {
        super(context, resource, objects);
        resourceID = resource;
        myContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        pi = getItem(position); // getting the item view

        View view = LayoutInflater.from(getContext()).inflate(resourceID, null);

        ImageView piImage = (ImageView) view.findViewById(R.id.imgViewPriLisItem);
        TextView piName = (TextView) view.findViewById(R.id.TextView_privilegeName);
        TextView piwhoOwns = (TextView) view.findViewById(R.id.TextView_whoOwns);

        piImage.setImageResource(pi.getImageID());
        if(piName!=null) {
            piName.setText(pi.getName());
        }
        piwhoOwns.setText(pi.getWhoOwns());

        Switch onOffSwitch = (Switch)  view.findViewById(R.id.simpleSwitch);

        if(onOffSwitch != null) {
            onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                    /*
                    if (isChecked == false) {
                        Settings.Secure.putString(myContext.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES , pi.getWhoOwns());
                        Settings.Secure.putInt(myContext.getContentResolver(),
                                Settings.Secure.ACCESSIBILITY_ENABLED, 1);
                    } else {
                        Settings.Secure.putString(myContext.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, pi.getWhoOwns());
                        Settings.Secure.putInt(myContext.getContentResolver(),
                                Settings.Secure.ACCESSIBILITY_ENABLED, 0);

                    }
                    */
                    Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    myContext.startActivity(intent);

                }

            });
        }



        return view;
    }

}
