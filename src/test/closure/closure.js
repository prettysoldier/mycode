
function f1(){
	n=999;
	function f2(){
		alert(n);
	}
	return f2;
}
var result=f1();
result(); // 999
//1）定义：
//闭包就是能够读取其他函数内部变量的函数。
//由于在Javascript语言中，只有函数内部的子函数才能读取局部变量，因此可以把闭包简单理解成“定义在一个函数内部的函数”。
//所以，在本质上，闭包就是将函数内部和函数外部连接起来的一座桥梁。
/**
 * 什么是闭包:
当内部函数 在定义它的作用域 的外部 被引用时,就创建了该内部函数的闭包 ,
如果内部函数引用了位于外部函数的变量,当外部函数调用完毕后,这些变量在内存不会被 释放,因为闭包需要它们.
 */
//2）用途：
//闭包可以用在许多地方。它的最大用处有两个，一个是前面提到的可以读取函数内部的变量，另一个就是让这些变量的值始终保持在内存中。

/*三、闭包内的微观世界
如果要更加深入的了解闭包以及函数a和嵌套函数b的关系，我们需要引入另外几个概念：函数的执行环境(excution context)、活动对象(call object)、作用域(scope)、作用域链(scope chain)。以函数a从定义到执行的过程为例阐述这几个概念。
当定义函数a的时候，js解释器会将函数a的作用域链(scope chain)设置为定义a时a所在的“环境”，如果a是一个全局函数，则scope chain中只有window对象。
当执行函数a的时候，a会进入相应的执行环境(excution context)。
在创建执行环境的过程中，首先会为a添加一个scope属性，即a的作用域，其值就为第1步中的scope chain。即a.scope=a的作用域链。
然后执行环境会创建一个活动对象(call object)。活动对象也是一个拥有属性的对象，但它不具有原型而且不能通过JavaScript代码直接访问。创建完活动对象后，把活动对象添加到a的作用域链的最顶端。此时a的作用域链包含了两个对象：a的活动对象和window对象。
下一步是在活动对象上添加一个arguments属性，它保存着调用函数a时所传递的参数。
最后把所有函数a的形参和内部的函数b的引用也添加到a的活动对象上。在这一步中，完成了函数b的的定义，因此如同第3步，函数b的作用域链被设置为b所被定义的环境，即a的作用域。
到此，整个函数a从定义到执行的步骤就完成了。此时a返回函数b的引用给c，又函数b的作用域链包含了对函数a的活动对象的引用，也就是说b可以访问到a中定义的所有变量和函数。函数b被c引用，函数b又依赖函数a，因此函数a在返回后不会被GC回收。
当函数b执行的时候亦会像以上步骤一样。因此，执行时b的作用域链包含了3个对象：b的活动对象、a的活动对象和window对象，如下图所示：

如图所示，当在函数b中访问一个变量的时候，搜索顺序是：
先搜索自身的活动对象，如果存在则返回，如果不存在将继续搜索函数a的活动对象，依次查找，直到找到为止。
如果函数b存在prototype原型对象，则在查找完自身的活动对象后先查找自身的原型对象，再继续查找。这就是Javascript中的变量查找机制。
如果整个作用域链上都无法找到，则返回undefined。
小结，本段中提到了两个重要的词语：函数的定义与执行。文中提到函数的作用域是在定义函数时候就已经确定，而不是在执行的时候确定（参看步骤1和3）。用一段代码来说明这个问题：
function f(x) { 
var g = function () { return x; }
return g;
}
var h = f(1);
alert(h()); 
这段代码中变量h指向了f中的那个匿名函数(由g返回)。
假设函数h的作用域是在执行alert(h())确定的，那么此时h的作用域链是：h的活动对象->alert的活动对象->window对象。
假设函数h的作用域是在定义时确定的，就是说h指向的那个匿名函数在定义的时候就已经确定了作用域。那么在执行的时候，h的作用域链为：h的活动对象->f的活动对象->window对象。
如果第一种假设成立，那输出值就是undefined；如果第二种假设成立，输出值则为1。
运行结果证明了第2个假设是正确的，说明函数的作用域确实是在定义这个函数的时候就已经确定了。*/