package com.almond.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.almond.common.utils.PageUtils;
import com.almond.product.entity.AttrAttrgroupRelationEntity;

import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 19:08:27
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

