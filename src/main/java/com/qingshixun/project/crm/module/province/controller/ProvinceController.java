package com.qingshixun.project.crm.module.province.controller;

import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.ProvinceModel;
import com.qingshixun.project.crm.module.province.service.ProvinceService;
import com.qingshixun.project.crm.web.ResponseData;
import com.qingshixun.project.crm.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Cy
 * @data 2019/8/12 - 17:31
 */
@Controller
@RequestMapping(value = "/province")
public class ProvinceController{

    @Autowired
    private ProvinceService provinceService;

    @RequestMapping(value = "/list/data")
    @ResponseBody
    public ResponseData list(Model model){
        ResponseData responseData = new ResponseData();
        List<ProvinceModel> province = provinceService.getProvinceList();
        responseData.setData(province);
        return responseData;
    }
}
