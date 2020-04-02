package com.miniblocks.androidfileselector.file_selector.util;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class AskPermission {

    public static final int PERMISSION_CODE = 7020;
    private Activity mActivity;
    private ArrayList<String> permissionsToRequest = new ArrayList<>();
    public AskPermission(Activity context, String ...permissions){
        mActivity = context;
        for(String perm: permissions){
            if(ContextCompat.checkSelfPermission(context, perm) !=
                    PackageManager.PERMISSION_GRANTED){
                permissionsToRequest.add(perm);
            }
        }
    }



    public void request(){
        String[] permissions = Arrays.copyOf(permissionsToRequest.toArray(),
                permissionsToRequest.size(), String[].class);
        ActivityCompat.requestPermissions(mActivity, permissions, PERMISSION_CODE);
    }

}
