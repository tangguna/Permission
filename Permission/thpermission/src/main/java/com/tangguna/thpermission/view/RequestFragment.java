package com.tangguna.thpermission.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.tangguna.thpermission.callback.RequestPermissionsCallBack;

import java.util.ArrayList;

/**
 * AndroidProject
 * <p>
 * Created by tanghui on 2020/8/27
 * Copyright © 2020年08月27日. All rights reserved.
 * <p>
 * Describe:
 */
public class RequestFragment extends Fragment {

    private Activity ThisActivity;
    private ArrayList<String> Permissions;
    private RequestPermissionsCallBack Callback;
    /**
     *
     * @param activity
     * @param permissions
     * @param callBack
     *        APP动态权限申请
     */
    public static void RequestPermissions(Activity activity, ArrayList<String> permissions, RequestPermissionsCallBack callBack){
        RequestFragment requestFragment = new RequestFragment();
        requestFragment.ThisActivity=activity;
        requestFragment.Permissions=permissions;
        requestFragment.Callback=callBack;
        activity.getFragmentManager().beginTransaction().add(requestFragment, activity.getClass().getName()).commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(Permissions.toArray(new String[0]),101);
        }else {
            throw new RuntimeException("API小于23无需手动授权请求");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==101) {
            ThisActivity.getFragmentManager().beginTransaction().remove(this).commit();
            Callback.onResult(permissions, grantResults);
            ThisActivity=null;
            Callback=null;
        }
    }
}
