package com.tangguna.thpermission;

import com.tangguna.thpermission.permissions.Permission;

import java.util.ArrayList;

/**
 * AndroidProject
 * <p>
 * Created by tanghui on 2020/8/27
 * Copyright © 2020年08月27日. All rights reserved.
 * <p>
 * Describe:
 */
public class TPermission extends Permission {
    /**
     * 获取权限名称列表"权限名1,权限名2,权限名3"
     */
    static public String QueryNames(ArrayList<String> permissions){
        StringBuilder names=new StringBuilder();
        for (String n : permissions) {
            names.append(TPermission.QueryName(n)).append(",");
        }
        if(names.length()>0){
            names.setLength(names.length()-1);
        }
        return names.toString();
    }
}
