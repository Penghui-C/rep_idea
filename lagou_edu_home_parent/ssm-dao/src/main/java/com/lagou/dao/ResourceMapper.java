package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;

import java.util.List;

public interface ResourceMapper {


    /*（
        资源分页&多条件查询
     */

    public List<Resource> findAllResourceByPage(ResourseVo resourseVo);

    /*
    *   删除资源分类下对应的资源（根据category_id删除）
    * */
    public void deleteResourceByCategoryId(Integer categoryId);
}
