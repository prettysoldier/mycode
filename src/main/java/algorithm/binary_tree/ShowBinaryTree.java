package algorithm.binary_tree;

/**
 * @author hsj
 * @create 2019/11/21
 */
public class ShowBinaryTree {

    public static void main(String[] args) {

        BinaryTree root = PrintAllEdges.buildTree();

        printInOrder(root, 0, "H", 17);
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
        printInOrder(head.getRight(), height + 1, "v", len);
        String val = to + head.getValue() + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * 17) + val);
        printInOrder(head.getLeft(), height + 1, "^", len);
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
