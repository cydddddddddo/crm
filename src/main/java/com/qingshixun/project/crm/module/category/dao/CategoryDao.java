package com.qingshixun.project.crm.module.category.dao;

import com.qingshixun.project.crm.core.BaseHibernateDao;
import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.CategoryModel;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/13 - 10:01
 */
@Repository
public class CategoryDao extends BaseHibernateDao<CategoryModel,Long> {
    //查询所有产品类别信息
    public List<CategoryModel> getAllCategorys(){
        return find();
    }
    //查询相应产品类别信息
    public PageContainer getCategoryPage(Map<String,String> params){
        Criterion categoryName = createLikeCriterion("name","%"+params.get("name")+"%");
        return getDataPage(params,categoryName);
    }
}
