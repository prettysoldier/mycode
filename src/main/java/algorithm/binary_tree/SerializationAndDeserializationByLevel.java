package algorithm.binary_tree;

import org.apache.commons.lang.StringUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author hsj
 * @create 2019/11/22
 */
public class SerializationAndDeserializationByLevel {

    public static void main(String[] args) {

        BinaryTree root = BinaryTree.initial(1, 2, 3, 4, 5);
        String ret = serializeByLevel(root);
        System.out.println(ret);

        BinaryTree head = deserializeByLevel(ret);
        ShowBinaryTree.showTree(head);
    }

    public static String serializeByLevel(BinaryTree root){
        if(root == null){
            return NULL_AND_SPLIT;
        }
        StringBuilder sb = new StringBuilder(root.getValue() + "").append(SPLIT);
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            BinaryTree curr = queue.poll();
            if(curr.getLeft() != null){
                queue.offer(curr.getLeft());
                sb.append(curr.getLeft().getValue()).append(SPLIT);
            } else {
                sb.append(NULL_AND_SPLIT);
            }
            if(curr.getRight() != null){
                queue.offer(curr.getRight());
                sb.append(curr.getRight().getValue()).append(SPLIT);
            } else {
                sb.append(NULL_AND_SPLIT);
            }
        }
        return sb.toString();
    }

    public static BinaryTree deserializeByLevel(String tree){
        if(StringUtils.isBlank(tree)){
            return null;
        }
        String[] values = tree.split(SPLIT);
        Queue<BinaryTree> queue = new LinkedList<>();
        BinaryTree root = buildNodeByString(values[0]);
        if(root != null){
            queue.offer(root);
        }

        BinaryTree curr = null;
        int i = 1;
        while(!queue.isEmpty()){
            curr = queue.poll();
            curr.setLeft(buildNodeByString(values[i++]));
            curr.setRight(buildNodeByString(values[i++]));
            if(curr.getLeft() != null){
                queue.offer(curr.getLeft());
            }
            if(curr.getRight() != null){
                queue.offer(curr.getRight());
            }
        }

        return root;
    }

    private static BinaryTree buildNodeByString(String s){

        if(NULL.equals(s)){
            return null;
        }
        return new BinaryTree(Integer.parseInt(s));
    }

    private static final String NULL = "#";
    private static final String SPLIT = "!";
    private static final String NULL_AND_SPLIT = "#!";
}
