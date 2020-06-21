package com.almond.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.almond.common.utils.PageUtils;
import com.almond.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 18:52:58
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

