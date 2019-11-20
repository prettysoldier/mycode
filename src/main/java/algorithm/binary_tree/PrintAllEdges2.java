package algorithm.binary_tree;

/**
 * 打印所有的边界节点
 * @author hsj
 * @create 2019/11/20
 */
public class PrintAllEdges2 {
    public static void main(String[] args) {
        
        BinaryTree root = PrintAllEdges.buildTree();
        IterateBinaryTreeWithRecursion.preOrderRecur(root);
        System.out.println();
        PrintAllEdges.printAllEdges(root);
        printEdges(root);
        
    }

    static void printEdges(BinaryTree root){
        if(root == null){
            return;
        }
        System.out.print(root.getValue() + " ");

        if(root.getLeft() != null && root.getRight() != null){
            printLeftEdge(root.getLeft(), true);
            printRightEdge(root.getRight(), true);
        }else{
            printEdges(root.getLeft() == null ? root.getRight() : root.getLeft());
        }
        System.out.println();
    }

    private static void printLeftEdge(BinaryTree head, boolean print){
        if(head == null){
            return;
        }
        if(print || (head.getLeft() == null && head.getRight() == null)){
            System.out.print(head.getValue() + " ");
        }
        printLeftEdge(head.getLeft(), true);
        printLeftEdge(head.getRight(), head.getLeft() == null ? true : false);
    }

    private static void printRightEdge(BinaryTree head, boolean print){
        if(head == null){
            return;
        }
        printRightEdge(head.getLeft(), head.getRight() == null ? true : false);

        printRightEdge(head.getRight(), true);

        if(print || (head.getLeft() == null && head.getRight() == null)){
            System.out.print(head.getValue() + " ");
        }
    }


}
