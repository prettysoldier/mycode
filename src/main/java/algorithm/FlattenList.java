package algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个列表，该列表中的每个元素要么是个列表，要么是整数。将其变成一个只包含整数的简单列表。
 *
 * @author hsj
 * @create 2019/12/24
 */
public class FlattenList {

    public List<Integer> flatten(List<NestedInteger> nestedList) {
        List<Integer> data = new ArrayList<>();
        Stack<Iterator<NestedInteger>> stack = new Stack<>();
        stack.push(nestedList.iterator());
        while(!stack.isEmpty()){
            Iterator<NestedInteger> iter = stack.pop();
            while(iter.hasNext()){
                NestedInteger next = iter.next();
                if(next.isInteger()){
                    data.add(next.getInteger());
                }else{
                    stack.push(iter);
                    stack.push(next.getList().iterator());
                    break;
                }
            }

        }

        return data;
    }
}
class NestedInteger{
    private boolean isInteger = true;
    private int val;
    private List<NestedInteger> list;
    public boolean isInteger(){
        return isInteger;
    }
    public Integer getInteger(){
        return val;
    }

    public List<NestedInteger> getList(){
        return list;
    }
}