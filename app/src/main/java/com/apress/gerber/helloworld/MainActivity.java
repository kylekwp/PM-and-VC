package com.apress.gerber.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
    }

    private void initExample1() {
        PrivilegeItem Location = new PrivilegeItem("Location", R.drawable.pi_location2_56x56 ,"Granted (25/50)");
        pitList.add(Location);
        PrivilegeItem Storage = new PrivilegeItem("Storage", R.drawable.pi_storage1_56x56 ,"Granted (47/50)");
        pitList.add(Storage);
        PrivilegeItem Calender = new PrivilegeItem("Calender", R.drawable.pi_calendar_56x56 ,"Granted (11/50)");
        pitList.add(Calender);
        PrivilegeItem Phone = new PrivilegeItem("Phone", R.drawable.pi_phone_56x56 ,"Granted (5/50)");
        pitList.add(Phone);
        PrivilegeItem Camera = new PrivilegeItem("Camera", R.drawable.pi_camera1_56x56 ,"Granted (0/50)");
        pitList.add(Camera);
        PrivilegeItem Msg = new PrivilegeItem("Msg", R.drawable.pi_msg1_56x56 ,"Granted (1/50)");
        pitList.add(Msg);
        PrivilegeItem Sensor = new PrivilegeItem("Sensor", R.drawable.pi_sensor1_56x56 ,"Granted (17/50)");
        pitList.add(Sensor);
        PrivilegeItem Contacts = new PrivilegeItem("Contacts", R.drawable.pi_contacts1_56x56 ,"Granted (20/50)");
        pitList.add(Contacts);
        PrivilegeItem Microphone = new PrivilegeItem("Microphone", R.drawable.pi_microphone1_56x56 ,"Granted (3/50)");
        pitList.add(Microphone);

    }


}
