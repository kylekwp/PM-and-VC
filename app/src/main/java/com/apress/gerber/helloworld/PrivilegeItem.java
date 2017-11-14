
package com.apress.gerber.helloworld;

/**
 * Created by Hxxxbxxhpvds on 4/11/2017.
 */

public class PrivilegeItem {
    private String name;
    private int imageID;
    private String whoOwns;

    public PrivilegeItem(String name, int id, String whoOwns){
        this.name = name;
        this.imageID = id;
        this.whoOwns = whoOwns;
    }

    public String getName() {
        return name;
    }

    public int getImageID() {
        return imageID;
    }

    public String getWhoOwns() {
        return whoOwns;
    }
}
