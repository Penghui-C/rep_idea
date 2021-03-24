package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Autowired
    private ResourceMapper resourceMapper;


    @Override
    public List<ResourceCategory> findAllResourceCategory() {

        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();

        return allResourceCategory;
    }

    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {

        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");


        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }

    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {

        Date date = new Date();
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setUpdatedBy("system");

        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    @Override
    public void deleteResourceCategory(Integer id) {

        //根据分类id删除其资源
        resourceMapper.deleteResourceByCategoryId(id);
        //根据id删除资源分类信息
        resourceCategoryMapper.deleteResourceCategory(id);

    }
}
