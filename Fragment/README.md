# Fragment

## Fragment有自己的生命周期
## Fragment依赖于Activity
如果Activity被销毁, Fragment随之被销毁
## Fragment和Activity的定位
Fragment可以通过`getActivity()`获取所在Activity
Activity可以通过`FragmentManager`类的`findFragmentById()`或`findFragmentByTag()`获取Fragment
## Fragment和Activity是多对多的关系
一个Fragment可以在多个Activity中使用
一个Activity可以包含多个Fragment

## 常用方法
`onCreateView` 与 `onViewCreated`
`onCreateView`中返回View
`onViewCreated`中写功能

实例化Fragment
`Fragment frag = new Fragment();`
把Fragment添加到Activity
`getFragmentManager().beginTransaction().add(R.id.fragmentId, frag).commitAllowingStateLoss();`

替换

`getFragmentManager().beginTransaction().replace(R.id.fragmentId, frag).commitAllowingStateLoss();`

`onAttach()`Fragment和Activity产生依赖关系
`onDetch()`Fragment和Activity脱离舒服, 二者无关系

## Fragment中getActivity()为null
Fragment依赖于Activity, 当app切换到后台等
Activity调用`onDestory()` 导致Activity为null
从此getActivity()返回值为null
所以 解决方案为 设立一个全局变量
mAct 当执行onAttach的时候 把context强转为Activity
之后的getActivity() 用mAct 代替
⬆️过于强硬
可以通过判断getActivity是否为null再继续之后的操作
总比Crash好
或者
在onDestory中结束异步任务 降低错误发生概率

## Activity向Fragment传递参数

创建一个静态方法

```java
    public static Afragment newInstance(String text){
        Afragment f = new Afragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        f.setArguments(bundle);
        return f;
    }
```

```java
getArguments().getString("text");
```
## Fragment回退栈

返回上一个Fragment时, View会被重建, 导致许多参数被重写. 

解决方法: 先隐藏 再添加

``` java
getFragmentManager().beginTransaction().hide(OldFragment).add(R.id.fragmentId, newFragment).addToBackStack(null).commit();
```

其中oldFragment通过`getFragment().findFragmentByTag`等方法获取.

newFragment通过new关键字实例化.

## Fragment和Activity通信

**可行不推荐**
mActivity含有SetData方法
在Fragment中调用getActivity 并将其强转成mActivity 调用Activity中的SetData方法即可传参
`((mActivity)getActivity).setData()`

通过回调接口实现. 具体看源码..(不太理解)

1.在Fragment里声明接口
2.在Activity中实现接口
3.在Fragment onAttch()中为listener赋值

