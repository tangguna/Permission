package com.tangguna.thpermission.handle;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.tangguna.thpermission.RxPermission;
import com.tangguna.thpermission.callback.OpenSettingsCallback;
import com.tangguna.thpermission.callback.RequestCallback;
import com.tangguna.thpermission.view.Settings;

/**
 * Permission
 * <p>
 * Created by tanghui on 2020/10/29
 * Copyright © 2020年10月29日. All rights reserved.
 * <p>
 * Describe:检测是否具有悬浮窗权限，和实现了授权申请方法
 */
public class AlertWindowHandle extends Handle {
    @Override
    public Handle.CheckResult Check(RxPermission obj, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(android.provider.Settings.canDrawOverlays(obj.GetContext())){
                return Handle.CheckResult.Resolve;
            }else{
                return CheckResult.FinalReject;
            }
        }else{
            return Handle.CheckResult.Resolve;
        }
    }

    @Override
    public void Request(RxPermission obj, String permission, final RequestCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + obj.GetActivity().getPackageName()));

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
