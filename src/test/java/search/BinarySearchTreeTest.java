package search;

import org.junit.Test;

/**
 *
 */
public class BinarySearchTreeTest {

    @Test
    public void testTree() {

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        BinarySearchTree.Tree tree = binarySearchTree.getTree();

        tree.insertNode(22);
        tree.insertNode(23);
        tree.insertNode(24);
        tree.insertNode(25);
        tree.insertNode(26);
        tree.insertNode(16);
        tree.insertNode(209043);
        tree.insertNode(1);

        tree.displayAll(tree.getRoot());

        System.out.println(tree.getTreeDepth());
    }

}