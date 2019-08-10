package com.qingshixun.project.crm.module.department.dao;

import com.qingshixun.project.crm.core.BaseHibernateDao;
import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.DepartmentModel;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/5 - 19:05
 */
@Repository
public class DepartmentDao extends BaseHibernateDao<DepartmentModel,Long> {
    /**
     * 获得部门分页信息
     * @return
     */
    public PageContainer getDepartmentPage(Map<String,String> params){
        //创建根据部门名称模糊查询条件
        Criterion name = createLikeCriterion("name","%"+params.get("name")
        +"%");
        return getDataPage(params,name);
    }

    public List<DepartmentModel> getDepartmentList(String value){
        Criterion name = createLikeCriterion("name","%"+value
                +"%");
        return find(name);
    }

}
