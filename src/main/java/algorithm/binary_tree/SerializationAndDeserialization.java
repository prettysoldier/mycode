package algorithm.binary_tree;

import org.apache.commons.lang.StringUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author hsj
 * @create 2019/11/22
 */
public class SerializationAndDeserialization {

    public static void main(String[] args) {

        BinaryTree root = BinaryTree.initial(1, 2, 3, 4, 5);
        String ret = serialize(root);
        System.out.println(ret);

        BinaryTree head = deserialize(ret);
        ShowBinaryTree.showTree(head);
    }

    private static final String NULL = "#";
    private static final String SPLIT = "!";
    private static final String NULL_AND_SPLIT = "#!";


    public static String serialize(BinaryTree root){
        if(root == null){
            return NULL_AND_SPLIT;
        }
        String ret = root.getValue() + SPLIT;
        ret += serialize(root.getLeft());
        ret += serialize(root.getRight());
        return ret;
    }

    public static BinaryTree deserialize(String tree){
        if(StringUtils.isBlank(tree)){
            return null;
        }
        String[] values = tree.split(SPLIT);
        Queue<String> queue = new LinkedList<>();
        for(String value : values){
            queue.offer(value);
        }
        return buildPreOrder(queue);
    }

    private static BinaryTree buildPreOrder(Queue<String> queue){
        String currStr = queue.poll();
        if(NULL.equals(currStr)){
            return null;
        }
        BinaryTree curr = new BinaryTree(Integer.parseInt(currStr));
        curr.setLeft(buildPreOrder(queue));
        curr.setRight(buildPreOrder(queue));
        return curr;
    }
    
}
