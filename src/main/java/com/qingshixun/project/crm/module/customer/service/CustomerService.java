package com.qingshixun.project.crm.module.customer.service;

import com.qingshixun.project.crm.core.BaseService;
import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.CustomerModel;
import com.qingshixun.project.crm.model.UserStatus;
import com.qingshixun.project.crm.module.customer.dao.CustomerDao;
import com.qingshixun.project.crm.util.DateUtils;
import com.qingshixun.project.crm.util.Poi4Excel;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户管理Service类
 * @author Cy
 * @data 2019/8/6 - 10:45
 */
@Service
@Transactional
public class CustomerService extends BaseService {
    //注入客户管理Dao
    @Autowired
    private CustomerDao customerDao;

    /**
     * 获得所有客户信息
     * @param params
     * @return
     */
    public PageContainer getCustomerPage(Map<String,String> params){
        return customerDao.getCustomerPage(params);
    }

    /**
     * 根据id获得客户信息
     * @param customerId
     * @return
     */
    public CustomerModel getCustomer(Long customerId){
        return customerDao.get(customerId);
    }

    /**
     * 保存客户
     * @param customer
     */
    public void saveCustomer(CustomerModel customer){
        //改变客户状态，启用改禁用/禁用改启用
        if (customer.getStatus().equals(UserStatus.disabledStatus)){
            customer.setStatus(UserStatus.activeStatus);
        }else {
            customer.setStatus(UserStatus.disabledStatus);
        }
        //没有账号则创建账号（CUS+时间）
        if ("".equals(customer.getAccount())||null == customer.getAccount()){
            customer.setAccount("CUS"+System.currentTimeMillis());
        }
        //设置最后更新时间
        customer.setUpdateTime(DateUtils.timeToString(new Date()));
        customerDao.save(customer);
    }

    /**
     * 删除客户
     * @param customerId
     */
    public void deleteCustomer(Long customerId){
        customerDao.delete(customerId);
    }

    /**
     *查询所有客户
     * @return List
     */
    public List<CustomerModel> getAllCustomers(){
        return customerDao.find();
    }

    /**
     * excel导出客户信息
     * @param fileName
     * @param customer
     * @return
     * @throws IOException
     */
    public Workbook export(String fileName,List<CustomerModel> customer)throws IOException{
        //设置文件格式“.xlsx”
        String fileType="xlsx";

        //导出excel所需要的数据格式
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

        //导出excel所需的一行的数据格式
        ArrayList<String> listTitle = new ArrayList<String>();

        //数据标题信息
        listTitle.add("客户名");
        listTitle.add("所在区域");
        listTitle.add("省份");
        listTitle.add("城市");
        listTitle.add("地址");
        listTitle.add("创建时间");
        listTitle.add("最后更新时间");
        //导入标题
        list.add(listTitle);
        for (int i = 0; i < customer.size(); i++) {
            //存放excel的单行信息
            ArrayList<String> listBody = new ArrayList<String>();

            listBody.add(customer.get(i).getName());
            listBody.add(customer.get(i).getRegion().getName());
            listBody.add(customer.get(i).getProvince().getName());
            listBody.add(customer.get(i).getCity().getName());
            listBody.add(customer.get(i).getAddress());
            listBody.add(customer.get(i).getCreateTime());
            listBody.add(customer.get(i).getUpdateTime());

            list.add(listBody);
        }
        return Poi4Excel.writer(fileName,fileType,list);
    }

    /**
     * 获得所选择客户信息
     * @param params
     * @return
     */
    public PageContainer getSelectCustomerPage(Map<String,String> params){
        return customerDao.getSelectCustomerPage(params);
    }

    /**
     * 根据客户id查询客户
     * @param customerId
     * @return
     */
    public CustomerModel getSelectedCustomer(Long customerId){
        return customerDao.get(customerId);
    }

    /**
     * 根据名称搜索客户
     * @param value
     * @return
     */
    public List<CustomerModel> getCustomerList(String value){
        return customerDao.getCustomerList(value);
    }
}
