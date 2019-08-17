package com.qingshixun.project.crm.module.contact.service;

import com.qingshixun.project.crm.core.BaseService;
import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.ContactModel;
import com.qingshixun.project.crm.module.contact.dao.ContactDao;
import com.qingshixun.project.crm.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/16 - 15:31
 */
@Service
@Transactional
public class ContactService extends BaseService {

    @Autowired
    private ContactDao contactDao;

    //获得单页联系人数据
    public PageContainer getContacts(Map<String,String> params){
        return contactDao.getContactPage(params);
    }

    //获得联系人数据集合（名称查询）
    public List<ContactModel> getContactList(String value){
        return contactDao.getContactList(value);
    }

    //保存
    public void saveContact(ContactModel contact){
        if ("".equals(contact.getCode())){
            contact.setCode("CON"+System.currentTimeMillis());
        }
        contact.setUpdateTime(DateUtils.timeToString(new Date()));
        contactDao.save(contact);
    }
    //删除
    public void deleteContact(Long contactId){
        contactDao.delete(contactId);
    }
    //id查询联系人
    public ContactModel getContact(Long contactId){
        return contactDao.get(contactId);
    }

    //获得可选择的
    public PageContainer getSelectContacts(Map<String,String> params){
        return contactDao.getSelectContactPage(params);
    }
}
