package com.qingshixun.project.crm.module.product.controller;

import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.ProductModel;
import com.qingshixun.project.crm.module.product.service.ProductService;
import com.qingshixun.project.crm.util.ImageUtils;
import com.qingshixun.project.crm.web.ResponseData;
import com.qingshixun.project.crm.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/8 - 10:07
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;
    //进入商品列表
    @RequestMapping(value = "/list")
    public String productPage(Model model){
        model.addAttribute("imagePath", ImageUtils.DEFAULT_IMAGE_PATH);
        return "/product/list";
    }
    //编辑商品信息
    @RequestMapping(value = "/form/{productId}")
    @ResponseBody
    public String productForm(Model model, @PathVariable Long productId){
        ProductModel product = null;
        if (0L==productId){
            product = new ProductModel();
        }else {
            product = productService.getProduct(productId);
        }
        model.addAttribute(product);
        model.addAttribute("imagePath", ImageUtils.DEFAULT_IMAGE_PATH);
        return "/product/form";
    }
    //获得所有商品信息
    @RequestMapping(value = "/list/data")
    @ResponseBody
    public PageContainer productList(Model model,@RequestParam Map<String,String> params){
        return productService.getProductPage(params);
    }
    //获得所有商品信息 productSelec
    @RequestMapping(value = "/list/select")
    @ResponseBody
    public PageContainer getSelectProduct(Model model, @RequestParam Map<String,String> params){
        return productService.getSelectProductPage(params);
    }
    //保存/新增商品信息
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData productSave(Model model, @ModelAttribute("product") ProductModel product, HttpServletRequest request, MultipartRequest file){
        ResponseData responseData = new ResponseData();
        try {
            productService.saveProduct(product,getRealPath(),file);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }
    //删除商品信息
    @RequestMapping(value = "/delete/{productId}")
    @ResponseBody
    public ResponseData productDelete(Model model,@PathVariable Long productId){
        logger.debug("delete product:"+productId);
        ResponseData responseData = new ResponseData();
        try {
            productService.deleteProduct(productId);
        }catch (org.hibernate.exception.ConstraintViolationException e){
            responseData.setStatus("3");
            logger.error(e.getMessage());
        }
        return responseData;
    }
    //修改商品状态
    @RequestMapping(value = "/change/{productId}")
    @ResponseBody
    public ResponseData productChange(Model model,@PathVariable Long productId){
        logger.debug("change product:"+productId);
        ResponseData responseData = new ResponseData();
        try {
            productService.changeProductStatus(productId);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }
    //获取所有供常见问答选择的产品信息
    @RequestMapping(value = "/list/problem")
    @ResponseBody
    public PageContainer problem(Model model,@RequestParam Map<String,String> params){
        return productService.getProblemProductPage(params);
    }
    //获取常见问答选择的产品信息
    @RequestMapping(value = "/getSelectedProduct/{productId}")
    @ResponseBody
    public ResponseData getSelectedProduct(Model model,@PathVariable Long productId){
        ResponseData responseData = new ResponseData();
        try {
            ProductModel product = productService.getProduct(productId);
            responseData.setData(product);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }
    //进入产品选择页面
    @RequestMapping(value = "/product")
    public String productSelectPage(Model model){
        return "/product/product";
    }
    //进入订单选择产品页面
    @RequestMapping(value = "/products")
    public String products(Model model) {
        model.addAttribute("imagePath", ImageUtils.DEFAULT_IMAGE_PATH);
        // 转向（forward）前端页面，文件：/WEB-INF/views/order/product.jsp
        return "/product/products";
    }
}
