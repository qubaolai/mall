package com.almond.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.almond.product.entity.CategoryEntity;
import com.almond.product.service.CategoryService;
import com.almond.common.utils.PageUtils;
import com.almond.common.utils.R;



/**
 * 商品三级分类
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 19:08:27
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表: 查询所有分类-子分类，返回树形结构数据
     */
    @GetMapping("/list/tree")
    public R list(@RequestParam Map<String, Object> params){
        List<CategoryEntity> categoryEntities = categoryService.listWithTree();

        return R.ok().put("categoryEntities", categoryEntities);
    }
    /**
     * 批量修改菜单
     */
    @PostMapping("/update/updateCateGoryList")
    public R updateCateGoryList(@RequestBody List<CategoryEntity> categoryEntityList){
        //批量修改
        categoryService.updateBatchById(categoryEntityList);
        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds){
        //TODO 1.检查菜单是否被引用
        //物理删除
		categoryService.removeByIds(Arrays.asList(catIds));
		//

        return R.ok();
    }

}
