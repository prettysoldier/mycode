/**
 * 思考题
 * 
 */

var object = {   
	name : "My Object",   
	o1 : {
		name1 : 'o1',
		o2 : {
			name2 : 'o2',
			f: function (){return this.name2},
			f1: function (){
				var name1 = 'asdf';
				return this.name1}
		}
	}
}; 

console.log(object.o1.o2.f());  
console.log(object.o1.o2.f1());  