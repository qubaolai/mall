package com.almond.ware.dao;

import com.almond.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 18:52:58
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
