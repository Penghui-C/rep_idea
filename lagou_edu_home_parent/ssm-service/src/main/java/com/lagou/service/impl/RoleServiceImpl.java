package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;


    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);

        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        //1. 清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2. 为角色分配菜单

        for (Integer mid : roleMenuVo.getMenuIdList()) {

            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");


            roleMapper.roleContextMenu(role_menu_relation);
        }

    }

    @Override
    public void deleteRole(Integer roleid) {

        // 调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleid);

        roleMapper.deleteRole(roleid);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer id) {

        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();

        for (ResourceCategory resourceCategory: allResourceCategory) {

            List<Resource> resourceListByRoleId = roleMapper.findResourceListByRoleId(id, resourceCategory.getId());
            resourceCategory.setResourceList(resourceListByRoleId);

        }

        return allResourceCategory;
    }

    @Override
    public void roleContextResource(RoleResourceRelationVO roleResourceRelationVO) {

        roleMapper.deleteRoleResourceRelationByRoleId(roleResourceRelationVO.getRoleId());

        for(Integer resourceId: roleResourceRelationVO.getResourceIdList()) {

            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();

            roleResourceRelation.setResourceId(resourceId);
            roleResourceRelation.setRoleId(roleResourceRelationVO.getRoleId());

            Date date = new Date();
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);

            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");

            roleMapper.saveRoleResourceRelationByRoleResourceRelation(roleResourceRelation);
        }
    }
}
