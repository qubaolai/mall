package com.almond.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.almond.common.utils.PageUtils;
import com.almond.product.entity.SpuInfoEntity;

import java.util.Map;

/**
 * spu信息
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 19:08:27
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

