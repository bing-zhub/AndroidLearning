# TextView

## 文字 大小颜色

## 显示不下使用 ...

## 文字+icon

## 中划线 下划线

## 跑马灯

# Button
继承TextView

## 文字大小 颜色

## 自定义背景形状

## 自定义按压效果

## 点击事件

# EditText
同样继承自TextView

## 常用属性

## 监听事件
输入框变化
## 制作登录界面

# RadioButton

## 常用属性

## 自定义样式

## 监听事件

# CheckBox

## 常用属性

## 自定义样式

## 监听事件

# ImageView

## Button的其他衍生控件: ToggleButton/Switch

## 常用属性

## 加载网络图片

## scaleType

### fitXY
撑满空间, 宽高比可能发生改变

### fitCenter
保持宽高比缩放, 直至能够完全显示

### centerCrop
保持宽高比缩放 直至完全覆盖空间 裁剪显示

# 列表视图ListView
AppCompatActivity继承自Activity 默认带有标题栏等
## 常用属性

## Adapter接口

## Demo演示

# 网格视图 GridView

## 常用属性

## Adapter接口

## Demo演示

# 滚动视图ScrollView

ScrollView中只能有一个元素

## 垂直滚动: ScrollView

## 水平滚动: HorizontalScrollView

# RecyclerView(1)

RecyclerView能够灵活实现大数据集的展示, 视图的复用管理比ListView更好, 能够显示列表/网格/瀑布流等形式, 且不同的ViewHolder能够实现item多元化的功能.

但是使用起来会稍微麻烦一点, 并且没有类似ListView的onItemClickListener监听事件, 需要开发者自己实现.

