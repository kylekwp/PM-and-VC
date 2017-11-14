
package com.apress.gerber.helloworld;

import android.graphics.drawable.Drawable;

/**
 * Created by Hxxxbxxhpvds on 4/11/2017.
 */

public class PrivilegeItem {
    private String name;
    private Drawable image;
    private String whoOwns;

    public PrivilegeItem(String name, Drawable img, String whoOwns){
        this.name = name;
        this.image = img;
        this.whoOwns = whoOwns;
    }

    public String getName() {
        return name;
    }

    public Drawable  getDrawable() {
        return image;
    }

    public String getWhoOwns() {
        return whoOwns;
    }
}
