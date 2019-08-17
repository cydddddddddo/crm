package com.qingshixun.project.crm.module.supplier.service;

import com.qingshixun.project.crm.core.BaseService;
import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.SupplierModel;
import com.qingshixun.project.crm.module.supplier.dao.SupplierDao;
import com.qingshixun.project.crm.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/14 - 10:20
 */
@Service
@Transactional
public class SupplierService extends BaseService {

    @Autowired
    private SupplierDao supplierDao;

    /**
     * 获得全部或单个数据
     * @param params
     * @return
     */
    public PageContainer getSupplierPage(Map<String,String> params){
        return supplierDao.getSupplierPage(params);
    }

    public SupplierModel getSupplier(Long supplierId){
        return supplierDao.get(supplierId);
    }

    public void saveSupplier(SupplierModel supplier){
        /*if (hasIdentical(supplier.getName(),supplier.getMobile(),supplier.getEmail(),supplier.getInstruction())){
            return false;
        }*/
        if ("".equals(supplier.getCode())||null == supplier.getCode()){
            supplier.setCode("SUP"+System.currentTimeMillis());
        }
        supplier.setUpdateTime(DateUtils.timeToString(new Date()));
        supplierDao.save(supplier);
    }

    public void deleteSupplier(Long supplierId){
        supplierDao.delete(supplierId);
    }

    public PageContainer getSelectSupplierPage(Map<String,String> params){
        return supplierDao.getSelectSupplier(params);
    }



    public List<SupplierModel> getSupplier(String name){
        return supplierDao.getSupplier(name);
    }
    public List<SupplierModel> getSupplierList(){
        return supplierDao.getSupplierList();
    }
    public boolean hasIdentical(String name,String mobile,String email,String instruction){
        List<SupplierModel> list = getSupplierList();
        for (SupplierModel supplierModel :list) {
            if ((name.equals(supplierModel.getName()))&&(mobile.equals(supplierModel.getMobile()))&&(email.equals(supplierModel.getEmail()))&&(instruction.equals(supplierModel.getInstruction()))){
                return true;
            }else {
                continue;
            }
        }
        return false;
    }
}
