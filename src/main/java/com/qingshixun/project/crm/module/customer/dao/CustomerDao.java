package com.qingshixun.project.crm.module.customer.dao;

import com.qingshixun.project.crm.core.BaseHibernateDao;
import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.CustomerModel;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 客户管理Dao类
 * @author Cy
 * @data 2019/8/6 - 10:45
 */
@Repository
public class CustomerDao extends BaseHibernateDao<CustomerModel,Long> {


    public PageContainer getCustomerPage(Map<String,String> params){
        //创建根据客户名称查询条件
        Criterion customerName = createLikeCriterion("name","%"+params.get("name")+"%");
        //查询，并返回查询到的客户结果信息
        return getDataPage(params,customerName);
    }

    /**
     * 获取所有订单可以选择的客户信息列表(订单模块)
     * @param params
     * @return
     */
    public PageContainer getSelectCustomerPage(Map<String,String> params){
        Criterion status = createCriterion("status.code","USRS_Active");
        return getDataPage(params,status);
    }

    /**
     * 根据名字返回集合
     * @param value
     * @return
     */
    public List<CustomerModel> getCustomerList(String value){
        //创建根据客户名称查询条件
        Criterion customerName = createLikeCriterion("name","%"+value+"%");
        //查询并返回结果,List
        return find(customerName);
    }
}
