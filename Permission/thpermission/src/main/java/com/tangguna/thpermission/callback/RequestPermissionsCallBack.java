package com.tangguna.thpermission.callback;

/**
 * Permission
 * <p>
 * Created by tanghui on 2020/10/29
 * Copyright © 2020年10月29日. All rights reserved.
 * <p>
 * Describe:
 */
public interface RequestPermissionsCallBack {
    void onResult(String[] permissions, int[] grantResults);
}
