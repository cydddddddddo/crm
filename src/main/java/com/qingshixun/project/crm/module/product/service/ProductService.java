package com.qingshixun.project.crm.module.product.service;

import com.qingshixun.project.crm.core.BaseService;
import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.ProductModel;
import com.qingshixun.project.crm.model.ProductStatus;
import com.qingshixun.project.crm.module.product.dao.ProductDao;
import com.qingshixun.project.crm.util.DateUtils;
import com.qingshixun.project.crm.util.ImageUtils;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/8 - 10:08
 */
@Service
@Transactional
public class ProductService extends BaseService {

    @Autowired
    private ProductDao productDao;

    /**
     * 查询单页产品信息
     * @param params
     * @return
     */
    public PageContainer getProductPage(Map<String,String> params){
        return productDao.getProductPage(params);
    }

    /**
     * 按照类别查询产品信息
     * @return
     */
    public List<ProductModel> getAllProduct(){
        return productDao.find();
    }

    /**
     * 按照产品id查询产品信息
     * @param productId
     * @return
     */
    public ProductModel getProduct(Long productId) {
        return productDao.get(productId);
    }

    /**
     * 查询所有订单可以选择的产品信息
     * @param params
     * @return
     */
    public PageContainer getSelectProductPage(Map<String,String> params){
        //获得产品数组
        String[] productIds = params.get("productIds").toString().split(",");
        List<Long> list = new ArrayList<Long>();
        for (int i = 0; i < productIds.length; i++) {
            if ("".equals(productIds[i])){
                list.add(0L);
            }else {
                list.add(Long.parseLong(productIds[i]));
            }
        }
        return productDao.getSelectProductPage(params,list);
    }

    /**
     * 查询所有订单选择的产品信息(此处没有调用)
     * @param productIds
     * @return
     */
    public List<ProductModel> getSelectedProducts(Long[] productIds){
        return productDao.getSelectedProducts(productIds);
    }

    /**
     * 根据名称搜索产品
     * @param value
     * @return
     */
    public List<ProductModel> getProductList(String value){
        return productDao.getProductList(value);
    }

    /**
     * 保存产品
     * @param product
     * @param rootPath
     * @param file
     * @throws Exception
     */
    public void saveProduct(ProductModel product, String rootPath, MultipartRequest file)throws Exception {
        if (null!=file){
            MultipartFile imageFile = file.getFile("file");
            if (null!=imageFile){
                Map<String,Object> images = ImageUtils.saveImage(rootPath,imageFile,false);
                product.setPicture(images.get("imageName").toString());
            }
        }

        product.setUpdateTime(DateUtils.timeToString(new Date()));
        if (null == product.getStatus()){
            product.setStatus(ProductStatus.stopStatus);
        }

        if ("".equals(product.getCode())){
            product.setCode("PRT"+System.currentTimeMillis());
        }
        productDao.save(product);
    }

    /**
     * 保存产品
     * @param product
     */
    public void saveProduct(ProductModel product){
        productDao.save(product);
    }

    /**
     * 修改产品状态
     * @param departId
     */
    public void changeProductStatus(Long departId){
        ProductModel product = getProduct(departId);
        if (product.getStatus().equals(ProductStatus.stopStatus)){
            product.setStatus(ProductStatus.saleStatus);
        }else {
            product.setStatus(ProductStatus.stopStatus);
        }
        product.setUpdateTime(DateUtils.timeToString(new Date()));
        productDao.save(product);
    }

    /**
     * 删除产品
     * @param departId
     */
    public void deleteProduct(Long departId){
        productDao.delete(departId);
    }

    /**
     * 所有可供常见问答选择的产品信息
     * @param params
     * @return
     */
    public PageContainer getProblemProductPage(Map<String,String> params){
        return productDao.getProblemProductPage(params);
    }

}
