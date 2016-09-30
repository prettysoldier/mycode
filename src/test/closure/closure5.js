function outerFun()
{
 //没有var 
 a =0;
 alert(a);  
}
var a=4;
outerFun();
alert(a);
/**
结果为 0,0 真是奇怪,为什么呢?

*/


<function></function>
!