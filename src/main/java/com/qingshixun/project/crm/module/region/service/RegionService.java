package com.qingshixun.project.crm.module.region.service;

import com.qingshixun.project.crm.core.BaseService;
import com.qingshixun.project.crm.model.RegionModel;
import com.qingshixun.project.crm.module.region.dao.RegionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Cy
 * @data 2019/8/12 - 9:33
 */
@Service
@Transactional
public class RegionService extends BaseService {

    @Autowired
    private RegionDao regionDao;

    public List<RegionModel> getRegionList(){
        return regionDao.getRegionList();
    }

}
