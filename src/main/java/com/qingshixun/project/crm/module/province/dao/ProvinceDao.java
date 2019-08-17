package com.qingshixun.project.crm.module.province.dao;

import com.qingshixun.project.crm.core.BaseHibernateDao;
import com.qingshixun.project.crm.model.ProvinceModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cy
 * @data 2019/8/12 - 19:45
 */
@Repository
public class ProvinceDao extends BaseHibernateDao<ProvinceModel, Long> {

    public List<ProvinceModel> getProvinceList(){
            return find();
    }
}
