package com.almond.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.almond.common.utils.PageUtils;
import com.almond.common.utils.Query;

import com.almond.product.dao.CategoryDao;
import com.almond.product.entity.CategoryEntity;
import com.almond.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //查询所有分类
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        //封装父子树形结构数据
        // 找到所有的一级分类 父分类id为0为以及分类
        List<CategoryEntity> parentMenu = categoryEntities.stream().filter(categoryEntity ->
            categoryEntity.getParentCid() == 0
        ).map(menu -> {
            menu.setChildren(getChildren(menu, categoryEntities));
            return menu;
        }).sorted((menu1, menu2)->{
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());

        return parentMenu;
    }

    /**
     * 递归获取每一个菜单的子菜单
     */
     private List<CategoryEntity> getChildren(CategoryEntity menu, List<CategoryEntity> allMenu){
         List<CategoryEntity> childrenMenuList = allMenu.stream().filter(categoryEntity -> {
             return categoryEntity.getParentCid() == menu.getCatId();
         }).map(categoryEntity -> {
             //递归找子菜单
             List<CategoryEntity> children = getChildren(categoryEntity, allMenu);
             categoryEntity.setChildren(children);
             return categoryEntity;
         }).sorted((menu1, menu2) -> {
             //对菜单排序
             return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
         }).collect(Collectors.toList());
         return childrenMenuList;
     }

}