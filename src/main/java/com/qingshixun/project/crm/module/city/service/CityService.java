package com.qingshixun.project.crm.module.city.service;

import com.qingshixun.project.crm.core.BaseService;
import com.qingshixun.project.crm.model.CityModel;
import com.qingshixun.project.crm.module.city.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/12 - 17:00
 */
@Service
@Transactional
public class CityService extends BaseService {

    @Autowired
    private CityDao cityDao;


    public List<CityModel> getCityList(Map<String,Object> params){
        return cityDao.getCityList(params);
    }
}
