package com.qingshixun.project.crm.module.category.controller;

import com.qingshixun.project.crm.core.PageContainer;
import com.qingshixun.project.crm.model.CategoryModel;
import com.qingshixun.project.crm.module.category.service.CategoryService;
import com.qingshixun.project.crm.web.ResponseData;
import com.qingshixun.project.crm.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Cy
 * @data 2019/8/13 - 10:01
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    //进入产品类别列表页面
    @RequestMapping(value = "/list")
    public String categoryPage(Model model){
        return "/category/list";
    }
    //获取所有产品类别信息
    @RequestMapping(value = "/list/data")
    @ResponseBody
    public ResponseData categoryList(Model model){
        ResponseData responseData = new ResponseData();
        List<CategoryModel> category = categoryService.getAllCategorys();
        responseData.setData(category);
        return responseData;
    }
    //获取所有产品类别分页信息
    @RequestMapping(value = "/page/data")
    @ResponseBody
    public PageContainer page(Model model, @RequestParam Map<String,String> params){
        PageContainer category = categoryService.getCategoryPage(params);
        return category;
    }
    //进入产品类别编辑页面
    @RequestMapping(value = "/form/{categoryId}",method = RequestMethod.GET)
    public String categoryForm(Model model, @PathVariable Long categoryId){
        CategoryModel category = null;
        if (categoryId == 0L ){
            category = new CategoryModel();
        }else {
            category = categoryService.getCategory(categoryId);
        }
        model.addAttribute("categoryModel",category);
        return "/category/form";
    }
    //保存产品类别
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData productCategorySave(Model model, @ModelAttribute("category") CategoryModel category){
        ResponseData responseData = new ResponseData();
        try {
            categoryService.saveCategory(category);
        }catch (org.hibernate.exception.ConstraintViolationException e){
            logger.error(e.getMessage());
            responseData.setError(e.getMessage());
            responseData.setStatus("2");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }
    //删除产品类别
    @RequestMapping(value = "/delete/{categoryId}")
    @ResponseBody
    public ResponseData categoryDelete(Model model, @PathVariable Long categoryId){
        ResponseData responseData = new ResponseData();
        try {
            categoryService.deleteCategory(categoryId);
        }catch (org.hibernate.exception.ConstraintViolationException e){
            logger.error(e.getMessage());
            responseData.setError(e.getMessage());
            responseData.setStatus("2");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            responseData.setError(e.getMessage());
        }
        return responseData;
    }
}
