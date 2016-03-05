package search;

/**
 *
 */
public class BinarySearchTree {

    int binary = 0b00;

    public Tree getTree() {
        return tree;
    }

    private Tree tree;

    public BinarySearchTree() {
        this.tree = new Tree();
    }

    class Node {
        int data;
        Node left;
        Node right;

        int selfDepth;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    class Tree {

        public Node getRoot() {
            return root;
        }

        // root Node
        private Node root;

        public int getTreeDepth() {
            return treeDepth;
        }

        // 木の深さ
        private int treeDepth = 0;

        Tree() {
            root = null;
        }

        public void insertNode(int newData) {
            insertNode(newData, root);
        }

        public void insertNode(int newData, Node node) {

            // nodeが一つもない場合
            if (node == null) {
                root = new Node(newData);
                root.selfDepth = 0;

                treeDepth = 0;
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

                    if (node.right == null) {
                        node.left.selfDepth = node.selfDepth + 1;

                        if (treeDepth < node.left.selfDepth) {
                            // tree depthを更新
                            treeDepth = node.left.selfDepth;
                        }

                    }
                }

            } else if (node.data < newData) {

                // newDataがnodeのデータよりも大きい場合

                if (node.right != null) {
                    insertNode(newData, node.right);
                } else {

                    // 右へ追加
                    node.right = new Node(newData);

                    if (node.left == null) {
                        node.right.selfDepth = node.selfDepth + 1;

                        if (treeDepth < node.right.selfDepth) {
                            // tree depthを更新
                            treeDepth = node.right.selfDepth;
                        }

                    }
                }
            }
        }

        public void displayAll(Node node) {

            if (node == null) {
                // end
                return;
            }

            System.out.println(node.data);

            if (node.left != null) {
                displayAll(node.left);
            }

            if (node.right != null) {
                displayAll(node.right);
            }

        }

        public boolean find(Node node, int target) {

            if (node == null) {
                return false;
            }

            if (node.data > target) {
                //小さい値なので、右側にはない
                return find(node.left, target);
            } else if (node.data < target) {
                // 大きい値なので、左側にはない
                return find(node.right, target);
            }

            return true;
        }

    }

}
