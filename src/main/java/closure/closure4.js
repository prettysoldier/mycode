/**
 * 思考题
 * 
 */
function f1(){
	n=999;
	function f2(){
		var m = 100;
		function f3(){
			alert(n)
		}
		return f3;
	}
	return f2;
}
var result=f1();
result()();
