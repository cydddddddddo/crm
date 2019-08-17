package com.qingshixun.project.crm.module.contact.dao;

import com.qingshixun.project.crm.core.BaseHibernateDao;
import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.ContactModel;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/16 - 15:31
 */
@Repository
public class ContactDao extends BaseHibernateDao<ContactModel,Long> {

    //获得联系人单页信息
    public PageContainer getContactPage(Map<String,String> params){
        Criterion name = createLikeCriterion("name","%"+params.get("name")+"%");
        Criterion customerId = createCriterion("customer.id", Long.parseLong(params.get("customerId").toString()));
        return getDataPage(params,name,customerId);
    }
    //获得联系人集合
    public List<ContactModel> getContactList(String value){
        Criterion name = createLikeCriterion("name","%"+value+"%");
        return find(name);
    }


    public PageContainer getSelectContactPage(Map<String,String> params){
        return getDataPage(params);
    }
}
