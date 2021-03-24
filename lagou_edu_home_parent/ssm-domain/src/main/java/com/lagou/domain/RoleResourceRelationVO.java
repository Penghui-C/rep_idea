package com.lagou.domain;

import java.util.ArrayList;

//接收为角色分配资源实体类
public class RoleResourceRelationVO {

    private int roleId;
    private ArrayList<Integer> resourceIdList;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public ArrayList<Integer> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(ArrayList<Integer> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}
