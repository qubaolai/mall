<template>
  <!-- <el-tree :data="menus" :props="defaultProps" @node-click="handleNodeClick"></el-tree> -->
  <div>
    <el-switch v-model="drag"
      active-text="开启拖拽"
      inactive-text="关闭拖拽">
    </el-switch>
    <el-tree
      :data="menus"
      show-checkbox
      node-key="catId"
      @node-drop="handleDrop"
      :props="defaultProps"
      :expand-on-click-node="false"
      :default-expanded-keys="opend"
      :allow-drop="allowDrop"
      :draggable="drag"
    >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button type="text" size="mini" v-if="node.level <= 2" @click="() => append(data)">Append</el-button>
          <el-button type="text" size="mini" @click="() => edit(data)">Edit</el-button>
          <el-button type="text" size="mini" v-if="node.childNodes.length == 0" @click="() => remove(node, data)">Delete</el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog :title="title" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <el-form :model="category">
        <el-form-item label="图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <el-form :model="category">
        <el-form-item label="计量单位">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="reset">取 消</el-button>
        <el-button type="primary" @click="submitData()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// 这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
// 例如：import 《组件名称》 from '《组件路径》';
export default {
  name: 'categoryManager',
  // import引入的组件需要注入到对象中才能使用
  data () {
    // 这里存放数据
    return {
      // 所有待修改节点数据
      updateNodes: [],
      // 节点最大深度
      maxLevel: 0,
      // 弹层标题
      title: '',
      // 提交方式
      subType: '',
      menus: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      // 节点拖动
      drag: false,
      // 展开的节点
      opend: [],
      dialogFormVisible: false,
      category: {
        catId: '',
        // 分类名称
        name: '',
        // 父分类id
        parentCid: '',
        // 分类等级
        catLevel: '',
        // 是否显示
        showStatus: 1,
        // 排序
        sort: 0,
        // 图标地址
        icon: null,
        // 计量单位
        productUnit: null
      }
    }
  },
  // 监听属性 类似于data概念
  computed: {},
  // 监控data中的数据变化
  watch: {},
  // 方法集合
  methods: {
    // 节点拖拽成功后出发函数
    handleDrop (draggingNode, dropNode, dropType) {
      // 当前节点的最新父节点id
      let pId = 0
      // 当前节点的最新排序
      let siblings = []
      if (dropType === 'before' || dropType === 'after') {
        pId = dropNode.parent.data.catId === undefined ? 0 : dropNode.parent.data.catId
        // 当前节点的最新排序
        siblings = dropNode.parent.childNodes
      } else {
        pId = dropNode.data.catId
        // 当前节点的最新排序
        siblings = dropNode.childNodes
      }
      for (let i = 0; i < siblings.length; i++) {
        if (siblings[i].data.catId === draggingNode.data.catId) {
          // 当前节点节点发生变化
          let nowLevel = draggingNode.level
          if (siblings[i].level !== draggingNode.level) {
            // 变化
            // 修改当前节点
            nowLevel = siblings[i].level
            // 子节点层级修改
            this.updateChildredLevel(siblings[i])
          }
          this.updateNodes.push({catId: siblings[i].data.catId, sort: i, parentCid: pId, catLevel: nowLevel})
        } else {
          this.updateNodes.push({catId: siblings[i].data.catId, sort: i})
        }
      }
      // 发送请求修改菜单
      this.$http({
        url: this.$http.adornUrl('/product/category/update/updateCateGoryList'),
        method: 'post',
        data: this.$http.adornData(this.updateNodes, false)
      }).then(res => {
        if (res.data.code === 0) {
          this.$notify.success(
            '操作成功！'
          )
          this.updateNodes = []
          this.maxLevel = 0
          this.getMenuTree()
          this.opend = [pId]
        }
      })
    },
    // 修改子节点层级
    updateChildredLevel (node) {
      if (node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          let cNode = node.childNodes[i].data
          this.updateNodes.push({catId: cNode.catId, catLevel: node.childNodes[i].level})
          this.updateChildredLevel(node.childNodes[i])
        }
      }
    },
    // 节点拖拽判定
    allowDrop (draggingNode, dropNode, type) {
      this.countLevel(draggingNode.data)
      let deep = (this.maxLevel - draggingNode.data.catLevel) + 1
      if (type === 'inner') {
        return (deep + dropNode.level) <= 3
      } else {
        return (deep + dropNode.parent.level) <= 3
      }
    },
    // 统计子节点最大深度
    countLevel (node) {
      // 是否存在子节点
      if (node.children) {
        node.children.forEach(item => {
          if (item.catLevel > this.maxLevel) {
            this.maxLevel = item.catLevel
          }
          this.countLevel(item)
        })
      }
    },
    // 保存方法
    save () {
      console.log('this.category', this.category)
      this.$http({
        url: this.$http.adornUrl('/product/category/save'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
      }).then(({ data }) => {
        if (data.code === 0) {
          this.$notify.success(
            '添加成功！'
          )
          this.dialogFormVisible = false
          this.getMenuTree()
          this.opend = [this.category.parentCid]
          this.reset()
        }
      })
    },
    // 获取菜单树
    getMenuTree () {
      this.$http({
        url: this.$http.adornUrl('/product/category/list/tree'),
        method: 'get'
      }).then(({ data }) => {
        this.menus = data.categoryEntities
        console.log('menus', this.menus)
      })
    },
    // 添加按钮
    append (data) {
      this.title = '添加菜单'
      this.subType = 'add'
      this.dialogFormVisible = true
      this.category.parentCid = data.catId
      this.category.catLevel = data.catLevel + 1
    },
    // 打开修改弹层及数据回显
    edit (data) {
      this.dialogFormVisible = true
      this.title = '编辑菜单'
      this.subType = 'edit'
      // 查询菜单
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: 'get'
      }).then(({ data }) => {
        const { catId, name, icon, productUnit, parentCid } = data.category
        this.category.catId = catId
        this.category.parentCid = parentCid
        this.category.name = name
        this.category.icon = icon
        this.category.productUnit = productUnit
      })
    },
    // 修改菜单
    editCategory () {
      const {catId, name, icon, productUnit} = this.category
      this.$http({
        url: this.$http.adornUrl(`/product/category/update`),
        method: 'post',
        data: this.$http.adornData({catId, name, icon, productUnit}, false)
      }).then(({ data }) => {
        if (data.code === 0) {
          this.$notify.success(
            '修改成功！'
          )
          this.dialogFormVisible = false
          this.getMenuTree()
          this.opend = [this.category.parentCid]
          this.reset()
        }
      })
    },
    // 提交数据方法
    submitData () {
      if (this.subType === 'add') {
        this.save()
      } else {
        this.editCategory()
      }
    },
    // 菜单删除
    remove (node, data) {
      let catIds = [data.catId]
      this.$confirm(`是否删除【${data.name}】菜单?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/category/delete'),
          method: 'post',
          data: this.$http.adornData(catIds, false)
        }).then(({data}) => {
          this.$notify.success(
            '删除成功！'
          )
          this.getMenuTree()
          this.opend = [node.parent.data.catId]
        })
      }).catch(() => {
        this.$notify.info('取消删除')
      })
    },
    reset () {
      this.dialogFormVisible = false
      this.category.catId = ''
      this.category.name = ''
      // 父分类id
      this.category.parentCid = ''
      // 分类等级
      this.category.catLevel = ''
      // 是否显示
      this.category.showStatus = 1
      // 排序
      this.category.sort = 0
      // 图标地址
      this.category.icon = null
      // 计量单位
      this.category.productUnit = null
    }
  },
  // 生命周期 - 创建完成（可以访问当前this实例）
  created () {
    this.getMenuTree()
  },
  // 生命周期 - 挂载完成（可以访问DOM元素）
  mounted () {},
  beforeCreate () {}, // 生命周期 - 创建之前
  beforeMount () {}, // 生命周期 - 挂载之前
  beforeUpdate () {}, // 生命周期 - 更新之前
  updated () {}, // 生命周期 - 更新之后
  beforeDestroy () {}, // 生命周期 - 销毁之前
  destroyed () {}, // 生命周期 - 销毁完成
  activated () {}, // 如果页面有keep-alive缓存功能，这个函数会触发
  deactivated () {} // keep-alive缓存离开之后触发
}
</script>
<style scoped>
</style>