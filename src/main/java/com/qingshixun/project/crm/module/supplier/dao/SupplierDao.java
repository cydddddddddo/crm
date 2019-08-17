package com.qingshixun.project.crm.module.supplier.dao;

import com.qingshixun.project.crm.core.BaseHibernateDao;
import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.SupplierModel;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/14 - 10:20
 */
@Repository
public class SupplierDao extends BaseHibernateDao<SupplierModel,Long> {


    public PageContainer getSupplierPage(Map<String,String> params){
        Criterion name = createLikeCriterion("name","%"+params.get("name")+"%");
        return getDataPage(params,name);
    }

    public PageContainer getSelectSupplier(Map<String,String> params){
        return getDataPage(params);
    }

    public List<SupplierModel> getSupplier(String name){
        String sql = "SELECT * FROM qsx_supplier WHERE name ='"+name+"'";
        List list = (ArrayList<SupplierModel>)queryBySql(sql);
        return list;
    }

    public List<SupplierModel> getSupplierList(){
        return getAll();
    }
}
