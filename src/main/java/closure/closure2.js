/**
 * 思考题
 * 
 */
var name = "The Window";   
var object = {   
	name : "My Object",   
	getNameFunc : function(){   
		var that = this;
		return function(){
			alert(that.name);
			return this.name;   
		};   
	}   
};   


alert(object.getNameFunc()());  // "My Object" "The Window"