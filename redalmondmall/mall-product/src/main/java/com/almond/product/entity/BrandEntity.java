package com.almond.product.entity;

import com.almond.common.annotation.ListValue;
import com.almond.common.validation.group.AddGroup;
import com.almond.common.validation.group.EditGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 *
 * @author qubaolai
 * @email qubaolai@gmail.com
 * @date 2020-05-13 19:08:27
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @Null(message = "品牌id必须为空", groups = {AddGroup.class})
    @NotNull(message = "品牌id必须不为空", groups = {EditGroup.class})
    @TableId
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message = "名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
    @NotBlank(message = "logo不能为空", groups = {AddGroup.class})
    @URL(message = "logo必须为合法url地址", groups = {AddGroup.class, EditGroup.class})
    private String logo;
    /**
     * 介绍
     */
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @ListValue(value = {0, 1}, groups = {AddGroup.class, EditGroup.class})
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @NotBlank(message = "首字母不能为空")
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母", groups = {AddGroup.class, EditGroup.class})
    private String firstLetter;
    /**
     * 排序
     */
    @NotNull(message = "排序不能为空", groups = {AddGroup.class, EditGroup.class})
    @Min(value = 0, message = "排序必须大于等于0", groups = {AddGroup.class, EditGroup.class})
    private Integer sort;

}
