
package com.apress.gerber.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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


    }

    private void initExample1() {
        PackageManager pm =getPackageManager();

        List<PackageInfo> appInstall = pm.getInstalledPackages(PackageManager.GET_PERMISSIONS );
        if(appInstall==null){

            Log.d("dd","it is null");
        }
        for (PackageInfo pkg : appInstall) {

            String[] pInfo = pkg.requestedPermissions;
            int pSize =0;
            if(pInfo!=null) {
                 pSize= pInfo.length;
            }

            PrivilegeItem Location = new PrivilegeItem( pkg.packageName, R.drawable.pi_location2_56x56 ,"Granted ("+pSize+"/100)");
            pitList.add(Location);


        }


    }


}
