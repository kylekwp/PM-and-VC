
package com.apress.gerber.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/**
 * Coded by Hongbin
 *
 * */

public class MainActivity extends AppCompatActivity {




    //get a list of installed apps.



    private List<PrivilegeItem> pitList = new ArrayList<PrivilegeItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initExample1();
        PrivilegeItemAdapter adapter = new PrivilegeItemAdapter(
                MainActivity.this, R.layout.cell, pitList);
        TextView textView = (TextView) findViewById( R.id.isRooted);
        ListView listView = (ListView) findViewById( R.id.listPrivilege);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent newActivity = new Intent(view.getContext(), list_of_permissions.class);
                newActivity.putExtra("name",pitList.get(position).getName());
                startActivity(newActivity);
            }
        });

        if((new root_checker()).isDeviceRooted()) {
            textView.setText("TheDevice is Rooted");
            textView.setTextColor(Color.RED);
        }
        else{
            textView.setText("TheDevice is Not Rooted");
        }






    }

    private void initExample1() {
        PackageManager pm =getPackageManager();
        Drawable appIcon = null;
        List<PackageInfo> appInstall = pm.getInstalledPackages(PackageManager.GET_PERMISSIONS );
        if(appInstall==null){

            Log.d("dd","it is null");
        }
        for (PackageInfo pkg : appInstall) {



            try
            {
                appIcon = pm.getApplicationIcon(pkg.packageName);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            String[] pInfo = pkg.requestedPermissions;
            int pSize =0;
            if(pInfo!=null) {
                 pSize= pInfo.length;
            }

            PrivilegeItem Location = new PrivilegeItem( pkg.packageName, appIcon  ,"Granted ("+pSize+"/100)");
            pitList.add(Location);


        }


    }


}
