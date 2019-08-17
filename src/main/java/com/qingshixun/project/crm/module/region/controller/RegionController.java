package com.qingshixun.project.crm.module.region.controller;

import com.qingshixun.project.crm.model.RegionModel;
import com.qingshixun.project.crm.module.region.service.RegionService;
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
 * @data 2019/8/12 - 9:32
 */
@Controller
@RequestMapping(value = "/region")
public class RegionController extends BaseController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "/list/data")
    @ResponseBody
    public ResponseData list(Model model){
        ResponseData responseData = new ResponseData();
        List<RegionModel> list = regionService.getRegionList();
        responseData.setData(list);
        return responseData;
    }
}
