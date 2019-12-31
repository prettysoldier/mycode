如果用protected来修饰变量与方法： 若子类Child1, Child2与父类Parent在同一个包，则Child1与Child2可以相互访问变量和方法
如果有一个子类c在另一个包，也c无法访问与父类在同一个包的子类a,b的变量与方法，但是与父类在一个包的子类a,b可以访问c的变量，但不能访问方法。

说明 public > protected > default(包) > private

