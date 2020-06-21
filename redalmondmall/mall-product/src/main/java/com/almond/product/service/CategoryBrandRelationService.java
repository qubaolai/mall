package com.almond.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.almond.common.utils.PageUtils;
import com.almond.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 19:08:27
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

