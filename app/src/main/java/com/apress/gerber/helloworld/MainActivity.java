
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static final int PERMISSIONS_GRANTED = 0;
    public static final int PERMISSIONS_DENIED = 1;
    private static final int PERMISSION_REQUEST_CODE = 0;
    private static final String EXTRA_PERMISSIONS =
            "me.chunyu.clwang.permission.extra_permission";
    private static final String PACKAGE_URL_SCHEME = "package:";

    private Button buttonSysSet;

    //get a list of installed apps.






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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSysSet:
                showMissingPermissionDialog();break;
            default:
                break;
        }
    }

    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.toSysset_title);
        builder.setMessage(R.string.toSysset_text);

        builder.setNegativeButton(R.string.toSysset_quit, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                setResult(PERMISSIONS_DENIED);
                finish();
            }
        });

        builder.setPositiveButton(R.string.toSysset_set, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.show();
    }

    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        //intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

}
