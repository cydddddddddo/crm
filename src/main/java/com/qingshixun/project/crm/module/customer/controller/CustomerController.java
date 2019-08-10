package com.qingshixun.project.crm.module.customer.controller;

/**
 * @author Cy
 * @data 2019/8/6 - 10:45
 */

import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.CustomerModel;
import com.qingshixun.project.crm.module.customer.service.CustomerService;
import com.qingshixun.project.crm.util.GarbledUtil;
import com.qingshixun.project.crm.web.ResponseData;
import com.qingshixun.project.crm.web.controller.BaseController;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 客户管理类
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController {

    //注入客户管理Service
    @Autowired
    private CustomerService customerService;

    /**
     * 进入客户列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String customerPage(Model model) {
        return "/customer/list";
    }

    /**
     * 获得所有客户数据
     * @param model
     * @param params
     * @return
     */
    @RequestMapping(value = "/list/data")
    @ResponseBody
    public PageContainer customerList(Model model, @RequestParam Map<String, String> params) {
        PageContainer customer = customerService.getCustomerPage(params);
        return customer;
    }

    /**
     * 进入客户编辑页面
     * @param model
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/form/{customerId}")
    public String customerForm(Model model, @PathVariable Long customerId) {
        CustomerModel customer = null;
        if (customerId.equals(0L)) {
            customer = new CustomerModel();
        } else {
            customer = customerService.getCustomer(customerId);
        }
        model.addAttribute(customer);
        return "/customer/form";
    }

    /**
     *  保存客户信息
     * @param model
     * @param customer
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public ResponseData customerSave(Model model, @Valid @ModelAttribute("customer") CustomerModel customer) {
        ResponseData responseData = new ResponseData();
        try {
            customerService.saveCustomer(customer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }

    /**
     * 删除客户
     * @param model
     * @param customerId
     * @return
     */
    @RequestMapping(value = "delete/{customerId}")
    @ResponseBody
    public ResponseData customerDelete(Model model, @PathVariable Long customerId) {
        logger.debug("delete customer:" + customerId);
        ResponseData responseData = new ResponseData();
        try {
            customerService.deleteCustomer(customerId);
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            responseData.setStatus("3");
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }

    /**
     * 修改客户
     * @param model
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/change/{customerId}")
    @ResponseBody
    public ResponseData customerChange(Model model, @PathVariable Long customerId) {
        logger.debug("change customer:" + customerId);
        ResponseData responseData = new ResponseData();
        try {
            CustomerModel customer = customerService.getCustomer(customerId);
            customerService.saveCustomer(customer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }

    /**
     * 获得所有订单可以选择的客户信息列表
     * @param model
     * @param params
     * @return
     */
    @RequestMapping(value = "/list/select")
    @ResponseBody
    public PageContainer customerSelect(Model model, @RequestParam Map<String, String> params) {
        PageContainer product = customerService.getSelectCustomerPage(params);
        return product;
    }

    /**
     * 获取选择的客户信息（订单模块）
     * @param model
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/getSelectedCustomer/{customerId}")
    @ResponseBody
    public ResponseData getSelectedProdects(Model model, @PathVariable Long customerId) {
        ResponseData responseData = new ResponseData();
        try {
            CustomerModel customer = customerService.getSelectedCustomer(customerId);
            responseData.setData(customer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }

    /**
     * excel保存
     * @param model
     * @param fileName
     * @param response
     */
    @RequestMapping(value = "/doExport/{fileName}", method = RequestMethod.GET)
    public void doExport(Model model, @PathVariable String fileName, HttpServletResponse response) {
        try {
            String value = "";
            if (GarbledUtil.isMessyCode(fileName)) {
                value = fileName;
            } else {
                value = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            }
            List<CustomerModel> contacts = customerService.getAllCustomers();
            Workbook wb = customerService.export(value, contacts);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + value + ".xlsx");
            OutputStream ouputStream = new BufferedOutputStream(response.getOutputStream());
            // 下载文件(写输出流)
            wb.write(ouputStream);
            // 刷新流
            ouputStream.flush();
            // 关闭流
            ouputStream.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 进入客户选择列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/customer")
    public String customerSelectPage(Model model) {
        // 转向（forward）前端页面，文件：/WEB-INF/views/customer/list.jsp
        return "/customer/customer";
    }
}

