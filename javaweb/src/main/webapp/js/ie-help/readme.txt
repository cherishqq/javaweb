说明：
   本程序只在IE6下测试通过.
   本程序可以获取页面上变量,对象，方法，dom,js动态创建的dom节点等等，可在外部动态注入函数，层级获取对象

1. 安装
     右键点击 ie6_help_0.9.inf，选择安装即可。
2：使用
     在系统界面中右键点击，选择“ie help”菜单项，弹出需要输入的变量。
    (注意点：1：右键点击的位置,决定了能访问到哪些对象
	     2：每次要获取对象时，都需要从新右键点击ie help 输入，不能把结果框内的值删除继续输入)

    打开test/test.html 文件
    获取当前页面对象
    在body area 右击
   2.1: 获取各种类型的js变量
	分别输入： str,num,b,d,arr,o  查看输出结果（对于xml对象，只需要输入xmlObj.xml 会输出相关xml）
   2.2: 调用内部函数
	分别输入: init, init()    查看输出结果
   2.3: 执行外部函数
	在命令框中输入:  function test(){ return this.o;}
	ps: 标准的函数写法,可以引用到内部对象,需要加this指针

   2.4 获取dom 对象
       在命令框中分别输入: document / document.body 查看输出


   获取parent层对象方法：
     在 iframe area 右击
    2.5: 获得当前对象, 请参照2.1
    2.6: 获得parent 层对象
         分别输入：parent.init()/ parent.o

  对应于我们项目：就是在调试每个页面的时候，可以查看相关区域对应的页面中的各种变量

   
   
********************************************************************
Changelog
  
 2013-8-1 0.9 version
 1: 支持当前层级对象的输出，dom对象输出,
 2: 支持对象的json方式展示(不展示其父对象)
 3: 支持外部函数动态注入
 4：支持dom的层级关系调用

    
  

*********************************************************************

About me:
   name: 潘小津
   Phone:13779988849
   QQ: 282092036
   亿力吉奥智能电网中心	