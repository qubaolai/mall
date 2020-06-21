package com.almond.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.almond.common.validation.group.AddGroup;
import com.almond.common.validation.group.EditGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.almond.product.entity.BrandEntity;
import com.almond.product.service.BrandService;
import com.almond.common.utils.PageUtils;
import com.almond.common.utils.R;


/**
 * 品牌
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 19:08:27
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand/*, BindingResult result*/) {
//        if (result.hasErrors()) {
//            HashMap<String, String> errResult = new HashMap<>();
//            result.getFieldErrors().forEach(item ->{
//                // 获取错误消息
//                String message = item.getDefaultMessage();
//                // 获取校验错误的字段
//                String field = item.getField();
//                errResult.put(field, message);
//            });
//            return R.error(400, "提交数据不合法").put("data", errResult);
//        }else {
//            brandService.save(brand);
//        }
        brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@Validated(EditGroup.class) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds) {
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
