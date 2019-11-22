package algorithm.binary_tree;

/**
 * @author hsj
 * @create 2019/11/21
 */
public class ShowBinaryTree {

    public static void main(String[] args) {

        BinaryTree root = PrintAllEdges.buildTree();

        showTree(root);
    }

    private static final String HEAD_PREFIX = "H";
    private static final String LEFT_PREFIX = "L";
    private static final String RIGHT_PREFIX = "R";
    /**
     * 每个节点的显示长度：(最大整数的显示是Integer.MIN_VALUE，用十进制显示是11位)
     * + (前缀：1) + (前后一个空格：2) = 14
     */
    private static final int LENGTH = Integer.MIN_VALUE;


    public static void showTree(BinaryTree root){
        printInOrder(root, 0, HEAD_PREFIX, LENGTH);
    }
    /**
     * 从右边开始打印， 打印后顺时针旋转90度。
     * 旋转的目的：为了是高度与缩进一致！
     * @param head
     * @param height
     * @param to
     * @param len
     */
    private static void printInOrder(BinaryTree head, int height, String to, int len){

        if(head == null){
            return;
        }
        printInOrder(head.getRight(), height + 1, RIGHT_PREFIX, len);
        String val = to + head.getValue();
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * LENGTH) + val);
        printInOrder(head.getLeft(), height + 1, LEFT_PREFIX, len);
    }


    private static String getSpace(int num){
        String space = " ";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < num; i ++){
            sb.append(space);
        }
        return sb.toString();
    }
}
