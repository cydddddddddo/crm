package com.qingshixun.project.crm.module.city.dao;

import com.qingshixun.project.crm.core.BaseHibernateDao;
import com.qingshixun.project.crm.model.CityModel;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/12 - 16:57
 */
@Repository
public class CityDao extends BaseHibernateDao<CityModel, Long> {
    public List<CityModel> getCityList(Map<String,Object> params){
        //根据省份编码模糊查询条件
        Criterion proCode = createCriterion("province.code", params.get("proCode"));
        return find(proCode);
    }
}
