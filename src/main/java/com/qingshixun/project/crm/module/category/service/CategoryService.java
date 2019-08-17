package com.qingshixun.project.crm.module.category.service;

import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.CategoryModel;
import com.qingshixun.project.crm.module.category.dao.CategoryDao;
import com.qingshixun.project.crm.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/13 - 10:02
 */
@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    //获取所有产品类别信息
    public List<CategoryModel> getAllCategorys(){
        return categoryDao.getAllCategorys();
    }
    //获取所有产品类别信息分页信息
    public PageContainer getCategoryPage(Map<String,String> params){
        return  categoryDao.getCategoryPage(params);
    }
    //根据id获取产品类别信息
    public CategoryModel getCategory(Long categoryId){
        return categoryDao.get(categoryId);
    }
    //保存产品类别信息
    public void saveCategory(CategoryModel category){
        category.setCreateTime(DateUtils.timeToString(new Date()));
        categoryDao.save(category);
    }
    //删除产品类别信息
    public void  deleteCategory(Long categoryId){
        categoryDao.delete(categoryId);
    }
}
