package com.qingshixun.project.crm.module.department.service;

import com.google.common.collect.Sets;
import com.qingshixun.project.crm.core.BaseService;
import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.DepartmentModel;
import com.qingshixun.project.crm.model.RoleModel;
import com.qingshixun.project.crm.module.department.dao.DepartmentDao;
import com.qingshixun.project.crm.module.role.dao.RoleDao;
import com.qingshixun.project.crm.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Cy
 * @data 2019/8/5 - 19:06
 */
@Service
@Transactional
public class DepartmentService extends BaseService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 获得部门分页信息
     * @param params
     * @return
     */
    public PageContainer getDepartmentPage(Map<String,String> params){
        return departmentDao.getDepartmentPage(params);
    }

    /**
     * 利用基类的find方法获得所有部门List
     * @return
     */
    public List<DepartmentModel> getAllDepartments(){
        return departmentDao.find();
    }

    /**
     * 根据id获得部门信息
     * @param departmentId
     * @return
     */
    public DepartmentModel getDepartment(Long departmentId){
        return departmentDao.get(departmentId);
    }

    /**
     * 保存部门信息
     * @param department
     */
    public void saveDepartment(DepartmentModel department){
        department.setUpdateTime(DateUtils.timeToString(new Date()));
        departmentDao.save(department);
    }

    /**
     * 根据id删除部门信息
     * @param departmentId
     */
    public void deleteDepartment(Long departmentId){
        departmentDao.delete(departmentId);
    }

    /**
     * 设置部门角色信息
     * @param roleId
     * @param departmentId
     */
    public void saveRelates(Long[] roleId,Long departmentId){
        //获得部门实体
        DepartmentModel department = departmentDao.get(departmentId);
        //角色信息容器
        Set<RoleModel> roles = Sets.newHashSet();

        for (int i = 0; i < roleId.length; i++) {
            //获得角色信息
            RoleModel role = roleDao.get(roleId[i]);
            //添加角色信息
            roles.add(role);
        }
        //设置角色信息
        department.setRoles(roles);
        //保持部门
        departmentDao.save(department);
    }


}
