package com.qingshixun.project.crm.module.region.dao;

import com.qingshixun.project.crm.core.BaseHibernateDao;
import com.qingshixun.project.crm.model.RegionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cy
 * @data 2019/8/12 - 9:32
 */
@Repository
public class RegionDao extends BaseHibernateDao<RegionModel,Long> {

    public List<RegionModel> getRegionList(){
        return find();
    }
}
