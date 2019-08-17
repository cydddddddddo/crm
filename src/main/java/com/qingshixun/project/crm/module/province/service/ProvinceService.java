package com.qingshixun.project.crm.module.province.service;

import com.qingshixun.project.crm.core.BaseService;
import com.qingshixun.project.crm.model.ProvinceModel;
import com.qingshixun.project.crm.module.province.dao.ProvinceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Cy
 * @data 2019/8/12 - 19:47
 */
@Service
@Transactional
public class ProvinceService extends BaseService {

    @Autowired
    private ProvinceDao provinceDao;

    public List<ProvinceModel> getProvinceList(){
        return provinceDao.getProvinceList();
    }
}
