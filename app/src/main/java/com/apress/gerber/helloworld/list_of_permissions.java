package com.apress.gerber.helloworld;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class list_of_permissions extends AppCompatActivity {


    private List<PrivilegeItem> pitList = new ArrayList<PrivilegeItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_permissions);

        Intent myIntent = getIntent(); // gets the previously created intent
        String name = myIntent.getStringExtra("name");

        search_example(name);
        PrivilegeItemAdapter adapter = new PrivilegeItemAdapter(
                list_of_permissions.this, R.layout.cell, pitList);
        ListView listView = (ListView) findViewById( R.id.pList);
        listView.setAdapter(adapter);


    }


    private void search_example(String name) {
        PackageManager pm =getPackageManager();

        List<PackageInfo> appInstall = pm.getInstalledPackages(PackageManager.GET_PERMISSIONS );
        if(appInstall==null){

            Log.d("dd","it is null");
        }
        for (PackageInfo pkg : appInstall) {

            if(pkg.packageName.equals(name)){
                String[] pInfo = pkg.requestedPermissions;
                int pSize =0;
                if(pInfo!=null) {
                    pSize= pInfo.length;
                }
                for(int i=0;i<pSize;i++){
                    PrivilegeItem Location = new PrivilegeItem( "", R.drawable.pi_location2_56x56 , pInfo[i]);
                    pitList.add(Location);

                }

            }




        }


    }
}
