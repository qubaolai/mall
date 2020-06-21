package com.almond.product.entity.father;

import com.almond.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class CategoryFatherEntity {
    @TableField(exist = false)
    private List<CategoryEntity> children;
}
