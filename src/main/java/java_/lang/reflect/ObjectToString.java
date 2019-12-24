package java_.lang.reflect;


import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @author hsj
 * @create 2019/12/12
 */
public class ObjectToString {

    public static void main(String[] args) {

        System.out.println(new ObjectToString().toString(new B()));

    }
    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj){
        if(obj == null) {
            return "null";
        }
        if(visited.contains(obj)){
            return "...";
        }
        visited.add(obj);

        Class c = obj.getClass();

        if(c == String.class){
            return (String)obj;
        }
        if(c.isArray()){
            String r = c.getComponentType() + "[]{";
            for(int i = 0; i < Array.getLength(obj); i++){
                if(i > 0){
                    r += ",";
                }
                Object val = Array.get(obj, i);
                if(c.getComponentType().isPrimitive()){
                    r += val;
                }else{
                    r += toString(val);
                }
            }
            return r + "}";
        }
        // 其他对象
        String r = c.getName();
        do{
            Field[] fields = c.getDeclaredFields();
            if(fields.length == 0){
                return r;
            }
            r += "[";
            AccessibleObject.setAccessible(fields, true);
            for(Field f : fields){
                if(!Modifier.isStatic(f.getModifiers())){
                    if(!r.endsWith("[")){
                        r += ",";
                    }
                    r += f.getName() + "=";
                    try{
                        Class t = f.getType();
                        Object val = f.get(obj);
                        if(t.isPrimitive()){
                            r += val;
                        }else{
                            r += toString(val);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            r += "]";
            c = c.getSuperclass();
        }while(c != null && c != Object.class);

        return r;
    }
}

class B {
    private String s = "asdf";

    private int[] ints = {1, 2, 3};

    private double[][] d = {{1D, 2D, 3D}, {4D, 5D, 6D}};
}