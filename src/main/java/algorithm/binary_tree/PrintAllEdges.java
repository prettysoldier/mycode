package algorithm.binary_tree;

/**
 * 打印所有的边界节点
 * @author hsj
 * @create 2019/11/20
 */
public class PrintAllEdges {
    public static void main(String[] args) {
        
        BinaryTree root = buildTree();
        IterateBinaryTreeWithRecursion.preOrderRecur(root);
        System.out.println();

        printAllEdges(root);
        
    }

    static void printAllEdges(BinaryTree root){
        if(root == null){
            return;
        }
        // 计算数高度
        int height = BinaryTree.getHeight(root);
        System.out.println("tree height : " + height);
        // 获取每一行的最左节点和最右节点
        BinaryTree[][] leftRightMap = new BinaryTree[height][2];
        setLeftRightMap(root, 0 , leftRightMap);

        // 打印左边界
        for(int i = 0; i < leftRightMap.length; i++){
            System.out.print(leftRightMap[i][0].getValue() + " ");
        }
        // 用先序遍历打印底行的非左右节点的边界
        printBottomEdges(root, 0, leftRightMap);

        // 打印右边界：排除左右边界相同的节点
        for(int i = leftRightMap.length -1; i > 0; i--){
            if(leftRightMap[i][0] != leftRightMap[i][1]){

                System.out.print(leftRightMap[i][1].getValue() + " ");
            }
        }
        System.out.println();

    }

    static void setLeftRightMap(BinaryTree node, int deep, BinaryTree[][] leftRightMap){
        if(node == null){
            return;
        }
        leftRightMap[deep][0] = leftRightMap[deep][0] == null ? node : leftRightMap[deep][0];
        leftRightMap[deep][1] = node;
        setLeftRightMap(node.getLeft(), deep + 1, leftRightMap);
        setLeftRightMap(node.getRight(), deep + 1, leftRightMap);
    }

    private static void printBottomEdges(BinaryTree node, int deep, BinaryTree[][] leftRightMap){
        if(node == null){
            return;
        }

        if(node.getLeft() == null && node.getRight() == null
                && node != leftRightMap[deep][0] && node != leftRightMap[deep][1]){
            System.out.print(node.getValue() + " ");
        }
        printBottomEdges(node.getLeft(), deep + 1, leftRightMap);
        printBottomEdges(node.getRight(), deep + 1, leftRightMap);
    }


    static BinaryTree buildTree(){
        BinaryTree node1 = new BinaryTree(1);
        BinaryTree node2 = new BinaryTree(2);
        BinaryTree node3 = new BinaryTree(3);
        BinaryTree node4 = new BinaryTree(4);
        BinaryTree node5 = new BinaryTree(5);
        BinaryTree node6 = new BinaryTree(6);
        BinaryTree node7 = new BinaryTree(7);
        BinaryTree node8 = new BinaryTree(8);
        BinaryTree node9 = new BinaryTree(9);
        BinaryTree node10 = new BinaryTree(10);
        BinaryTree node11 = new BinaryTree(11);
        BinaryTree node12 = new BinaryTree(12);
        BinaryTree node13 = new BinaryTree(13);
        BinaryTree node14 = new BinaryTree(14);
        BinaryTree node15 = new BinaryTree(15);
        BinaryTree node16 = new BinaryTree(16);

        node1.setLeft(node2);
        node1.setRight(node3);

        node2.setRight(node4);

        node3.setLeft(node5);
        node3.setRight(node6);

        node4.setLeft(node7);
        node4.setRight(node8);

        node5.setLeft(node9);
        node5.setRight(node10);

        node8.setRight(node11);

        node9.setLeft(node12);

        node11.setLeft(node13);
        node11.setRight(node14);

        node12.setLeft(node15);
        node12.setRight(node16);

        return node1;
    }
}
