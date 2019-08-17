package com.qingshixun.project.crm.module.city.controller;

import com.qingshixun.project.crm.model.CityModel;
import com.qingshixun.project.crm.module.city.service.CityService;
import com.qingshixun.project.crm.web.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/12 - 17:03
 */
@Controller
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("/list/data")
    @ResponseBody
    public ResponseData list(Model model, @RequestParam Map<String,Object> params){
        ResponseData responseData = new ResponseData();
        List<CityModel> list = cityService.getCityList(params);
        responseData.setData(list);
        return responseData;
    }
}
