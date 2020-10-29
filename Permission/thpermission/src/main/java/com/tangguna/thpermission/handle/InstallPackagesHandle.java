package com.tangguna.thpermission.handle;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.tangguna.thpermission.RxPermission;
import com.tangguna.thpermission.callback.OpenSettingsCallback;
import com.tangguna.thpermission.callback.RequestCallback;
import com.tangguna.thpermission.view.Settings;


/**
 * AndroidProject
 * <p>
 * Created by tanghui on 2020/8/27
 * Copyright © 2020年08月27日. All rights reserved.
 * <p>
 * Describe:检测是否有安装权限，和实现了授权申请方法
 */
public class InstallPackagesHandle extends Handle {
    @Override
    public Handle.CheckResult Check(RxPermission obj, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(obj.GetContext().getPackageManager().canRequestPackageInstalls()){
                return Handle.CheckResult.Resolve;
            }else{
                return Handle.CheckResult.FinalReject;
            }
        }else{
            return Handle.CheckResult.Resolve;
        }
    }

    @Override
    public void Request(RxPermission obj, String permission, final RequestCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + obj.GetActivity().getPackageName()));

            Settings.OpenSettings(obj.GetActivity(), intent, new OpenSettingsCallback() {
                @Override
                public void onResult() {
                    callback.onResult();
                }
            });
        }else{
            callback.onResult();
        }
    }
}
