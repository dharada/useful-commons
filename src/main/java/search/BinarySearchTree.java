package search;

/**
 *
 */
public class BinarySearchTree {

    int binary = 0b00;

    // root Node
    private Node root;

    // 木の深さ
    private int depth = 0;

    public void insertNode(int newData, Node node) {

        // nodeが一つもない場合
        if (node == null) {
            root = new Node(newData);
            return;

        } else if (node.data == newData) {

            // 値が重複している場合は、追加しない。
            return;

        } else if (node.data > newData) {

            // newDataがnodeのデータよりも小さい場合

            if (node.left != null) {
                insertNode(newData, node.left);
            } else {
                // 左に追加
                node.left = new Node(newData);

                //                if (node.right == null) {
                //                    depth++;
                //                }
            }

        } else if (node.data < newData) {

            // newDataがnodeのデータよりも大きい場合

            if (node.right != null) {
                insertNode(newData, node.right);
            } else {
                // 右へ追加
                node.right = new Node(newData);

                //                if (node.left == null) {
                //                    depth++;
                //                }

            }
        }
    }

    class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    class Tree {

        Tree() {
            root = null;
        }

    }

}
