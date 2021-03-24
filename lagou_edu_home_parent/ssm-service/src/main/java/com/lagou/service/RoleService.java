package com.lagou.service;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.RoleResourceRelationVO;

import java.util.List;

public interface RoleService {

    /*
        查询所有角色（条件）
     */
    public List<Role>  findAllRole(Role role);

    /*
    根据角色ID查询该角色关联的菜单信息ID [1,2,3,5]
 */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /*
        为角色分配菜单
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    /*
        删除角色
     */
    public void deleteRole(Integer roleid);

    /*
    *   查询当前角色所具有的资源信息
    * */
    public List<ResourceCategory> findResourceListByRoleId(Integer id);

    /*
    *   为角色分配资源
    * */
    public void roleContextResource(RoleResourceRelationVO roleResourceRelationVO);
}
