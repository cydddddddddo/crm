package com.qingshixun.project.crm.module.supplier.controller;

import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.SupplierModel;
import com.qingshixun.project.crm.module.supplier.service.SupplierService;
import com.qingshixun.project.crm.web.ResponseData;
import com.qingshixun.project.crm.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/14 - 10:20
 */
@Controller
@RequestMapping(value = "/supplier")
public class SupplierController extends BaseController {

    @Autowired
    private SupplierService supplierService;

    //进入供应商页面
    @RequestMapping(value = "/list")
    public String supplierPage(Model model) {
        return "/supplier/list";
    }

    //获得供应商数据
    @RequestMapping(value = "/list/data")
    @ResponseBody
    public PageContainer supplierList(Model model, @RequestParam Map<String, String> params) {
        PageContainer supplier = supplierService.getSupplierPage(params);
        return supplier;
    }

    //进入编辑页面（新增/修改调用）
    @RequestMapping(value = "/form/{supplierId}")
    public String supplierForm(Model model, @PathVariable Long supplierId) {
        SupplierModel supplier = null;
        if (supplierId.equals(0L)) {
            supplier = new SupplierModel();
        } else {
            supplier = supplierService.getSupplier(supplierId);
        }
        model.addAttribute(supplier);
        return "/supplier/form";
    }

    //保存数据
    @RequestMapping(value = "/save")
    @ResponseBody
    public ResponseData supplierSave(Model model, @Valid @ModelAttribute("supplier") SupplierModel supplier) {
        ResponseData responseData = new ResponseData();
//        supplierService.hasIdentical(supplier);
        /*SupplierModel temp = new SupplierModel(supplier);
        if (supplierService.hasIdentical(temp)){
            responseData.setError("?");
            return responseData;
        }*/
        try {
            supplierService.saveSupplier(supplier);
            /*if (!temp){
                responseData.setError("有问题啊兄弟");
                return responseData;
            }*/
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }

    //删除数据
    @RequestMapping(value = "/delete/{supplierId}")
    @ResponseBody
    public ResponseData supplier(Model model, @PathVariable Long supplierId) {
        logger.debug("detele supplier:" + supplierId);
        ResponseData responseData = new ResponseData();
        try {
            supplierService.deleteSupplier(supplierId);
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            responseData.setStatus("3");
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }


    @RequestMapping(value = "/supplier")
    public String supplierSelectPage(Model model) {
        return "/supplier/supplier";
    }

    //产品管理模块调用（获得表单数据）
    @RequestMapping(value = "/list/select")
    @ResponseBody
    public PageContainer supplierSelect(Model model, @RequestParam Map<String, String> params) {
        PageContainer supplier = supplierService.getSelectSupplierPage(params);
        return supplier;
    }

    //产品管理模块调用（保存所选）
    @RequestMapping(value = "/getSelectedSupplier/{supplierId}")
    @ResponseBody
    public ResponseData getSelectedProducts(Model model, @PathVariable Long supplierId) {
        ResponseData responseData = new ResponseData();
        try {
            SupplierModel supplier = supplierService.getSupplier(supplierId);
            responseData.setData(supplier);
        } catch (Exception e) {
            // 异常处理
            logger.error(e.getMessage(), e);
            responseData.setError(e.getMessage());
        }
        // 返回处理结果（json 格式）
        return responseData;
    }
}

