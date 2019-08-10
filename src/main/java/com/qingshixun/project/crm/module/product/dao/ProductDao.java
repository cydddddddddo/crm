package com.qingshixun.project.crm.module.product.dao;

import com.qingshixun.project.crm.core.BaseHibernateDao;
import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.ProductModel;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/8 - 10:08
 */
@Repository
public class ProductDao extends BaseHibernateDao<ProductModel, Long> {

    /**
     * 查询单页产品信息
     *
     * @param params
     * @return
     */
    public PageContainer getProductPage(Map<String, String> params) {
        Criterion productName = createLikeCriterion("name", "%" + params.get("name") + "%");
        return getDataPage(params, productName);
    }

    /**
     * 按照产品分类查询产品信息
     *
     * @param contactCategoryId
     * @return
     */
    public List<ProductModel> getContacts(Long contactCategoryId) {
        Criterion criterion = createCriterion("category.id", contactCategoryId);
        return find(criterion);
    }

    /**
     * 所有常见问答可以选择产品信息
     * @param params
     * @return
     */
    public PageContainer getProblemProductPage(Map<String,String> params){
        //创建查询条件（产品状态在售）
        Criterion productName = createCriterion("status.code","PROS_Sale");
        //查询并返回查询到的产品结果信息
        return getDataPage(params,productName);
    }

    /**
     * 查询所有订单可以选择的产品信息
     * @param params
     * @param list
     * @return
     */
    public PageContainer getSelectProductPage(Map<String, String> params, List<Long> list) {
        // 创建查询条件(产品状态是在售)
        Criterion productName = createCriterion("status.code", "PROS_Sale");
        Criterion gtValue = createGtCriterion("inventory", 0);
        Criterion notInIds = createNotInCriterion("id", list);
        // 查询，并返回查询到的产品结果信息
        return getDataPage(params, productName, notInIds, gtValue);
    }

    /**
     * 查询所有订单选择的产品信息
     * @param productIds
     * @return
     */
    public List<ProductModel> getSelectedProducts(Long[] productIds){
        Criterion ids = createInCriterion("id",productIds);
        return find(ids);
    }

    /**
     * 根据名称搜索产品
     * @param value
     * @return
     */
    public List<ProductModel> getProductList(String value) {
        Criterion productName = createLikeCriterion("name", "%" + value + "%");
        return find(productName);
    }
}
