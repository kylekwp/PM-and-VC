package com.apress.gerber.helloworld;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class list_of_permissions extends AppCompatActivity {


    private List<PrivilegeItem> pitList = new ArrayList<PrivilegeItem>();
    private String name ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_permissions);

        Intent myIntent = getIntent(); // gets the previously created intent
        name = myIntent.getStringExtra("name");

        search_example(name);
        PrivilegeItemAdapter adapter = new PrivilegeItemAdapter(
                list_of_permissions.this, R.layout.layout, pitList);
        ListView listView = (ListView) findViewById( R.id.pList);
        listView.setAdapter(adapter);

        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                final int apiLevel = Build.VERSION.SDK_INT;
                Intent intent = new Intent();
                if (apiLevel >= 9) {

                    startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + name)));
                } else {
                    final String appPkgName = (apiLevel == 8 ? "pkg" : "com.android.settings.ApplicationPkgName");
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                    intent.putExtra(appPkgName,name);
                    startActivity(intent);
                }

            }
        });




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
                    PrivilegeItem Location = new PrivilegeItem( pkg.packageName, null , pInfo[i]);
                    pitList.add(Location);

                }

            }




        }


    }
}
