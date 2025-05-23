package Drva;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

public class zadaca6 { //
    // BinarySearchTree class
    public static class BinarySearchTree<E extends Comparable<E>> {

        /**
         * The tree root.
         */
        private BNode<E> root;

        /**
         * Construct the tree.
         */
        public BinarySearchTree() {
            root = null;
        }

        /**
         * Insert into the tree; duplicates are ignored.
         *
         * @param x the item to insert.
         */
        public void insert(E x) {
            root = insert(x, root);
        }

        /**
         * Remove from the tree. Nothing is done if x is not found.
         *
         * @param x the item to remove.
         */
        public void remove(E x) {
            root = remove(x, root);
        }

        /**
         * Find the smallest item in the tree.
         *
         * @return smallest item or null if empty.
         */
        public E findMin() {
            return elementAt(findMin(root));
        }

        /**
         * Find the largest item in the tree.
         *
         * @return the largest item of null if empty.
         */
        public E findMax() {
            return elementAt(findMax(root));
        }

        /**
         * Find an item in the tree.
         *
         * @param x the item to search for.
         * @return the matching item or null if not found.
         */
        public BNode<E> find(E x) {
            return find(x, root);
        }

        /**
         * Make the tree logically empty.
         */
        public void makeEmpty() {
            root = null;
        }

        /**
         * Test if the tree is logically empty.
         *
         * @return true if empty, false otherwise.
         */
        public boolean isEmpty() {
            return root == null;
        }

        /**
         * Print the tree contents in sorted order.
         */
        public void printTree() {
            if (isEmpty()) {
                System.out.println("Empty tree");
            } else {
                printTree(root);
            }
        }

        /**
         * Internal method to get element field.
         *
         * @param t the node.
         * @return the element field or null if t is null.
         */
        private E elementAt(BNode<E> t) {
            if (t == null)
                return null;
            return t.info;
        }

        /**
         * Internal method to insert into a subtree.
         *
         * @param x the item to insert.
         * @param t the node that roots the tree.
         * @return the new root.
         */
        private BNode<E> insert(E x, BNode<E> t) {
            if (t == null) {
                t = new BNode<E>(x, null, null);
            } else if (x.compareTo(t.info) < 0) {
                t.left = insert(x, t.left);
            } else if (x.compareTo(t.info) > 0) {
                t.right = insert(x, t.right);
            } else ;  // Duplicate; do nothing
            return t;
        }

        /**
         * Internal method to remove from a subtree.
         *
         * @param x the item to remove.
         * @param t the node that roots the tree.
         * @return the new root.
         */
        @SuppressWarnings({"raw", "unchecked"})
        private BNode<E> remove(Comparable x, BNode<E> t) {
            if (t == null)
                return t;   // Item not found; do nothing

            if (x.compareTo(t.info) < 0) {
                t.left = remove(x, t.left);
            } else if (x.compareTo(t.info) > 0) {
                t.right = remove(x, t.right);
            } else if (t.left != null && t.right != null) { // Two children
                t.info = findMin(t.right).info;
                t.right = remove(t.info, t.right);
            } else {
                if (t.left != null)
                    return t.left;
                else
                    return t.right;
            }
            return t;
        }

        /**
         * Internal method to find the smallest item in a subtree.
         *
         * @param t the node that roots the tree.
         * @return node containing the smallest item.
         */
        private BNode<E> findMin(BNode<E> t) {
            if (t == null) {
                return null;
            } else if (t.left == null) {
                return t;
            }
            return findMin(t.left);
        }

        /**
         * Internal method to find the largest item in a subtree.
         *
         * @param t the node that roots the tree.
         * @return node containing the largest item.
         */
        private BNode<E> findMax(BNode<E> t) {
            if (t == null) {
                return null;
            } else if (t.right == null) {
                return t;
            }
            return findMax(t.right);
        }

        /**
         * Internal method to find an item in a subtree.
         *
         * @param x is item to search for.
         * @param t the node that roots the tree.
         * @return node containing the matched item.
         */
        private BNode<E> find(E x, BNode<E> t) {
            if (t == null)
                return null;

            if (x.compareTo(t.info) < 0) {
                return find(x, t.left);
            } else if (x.compareTo(t.info) > 0) {
                return find(x, t.right);
            } else {
                return t;    // Match
            }
        }

        /**
         * Internal method to print a subtree in sorted order.
         *
         * @param t the node that roots the tree.
         */
        private void printTree(BNode<E> t) {
            if (t != null) {
                printTree(t.left);
                System.out.println(t.info);
                printTree(t.right);
            }
        }

        public void printTreeWithIndent() {
            printTreeWithIndent(root, 0);
        }

        private void printTreeWithIndent(BNode<E> t, int indent) {
            if (t != null) {
                printTreeWithIndent(t.left, indent + 1);
                for (int i = 0; i < indent; i++) System.out.print("   ");
                System.out.println(t.info);
                printTreeWithIndent(t.right, indent + 1);
            }
        }

        public BNode<E> getRoot() {
            return root;
        }
    }

    public static class BNode<E> {

        public E info;
        public BNode<E> left;
        public BNode<E> right;

        static int LEFT = 1;
        static int RIGHT = 2;

        public BNode<E> parent;

        public BNode(E info, BNode<E> parent) {
            this.parent = parent;
            this.info = info;
            left = null;
            right = null;
        }

        public BNode(E info) {
            this.parent = null;
            this.info = info;
            left = null;
            right = null;
        }

        public BNode(E info, BNode<E> left, BNode<E> right) {
            this.info = info;
            this.left = left;
            this.right = right;
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int q = input.nextInt();
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        for (int i = 0; i < n + q; i++) {
            String command = input.next();
            if (command.equals("insert")) {
                int value = input.nextInt();
                tree.insert(value);
            } else if (command.equals("ask")) {
                int value = input.nextInt();
                BNode<Integer> node = tree.find(value);
                if (node != null) {
                    System.out.println(countDepth(tree.getRoot(), node)+1);
                }
            }
        }

        input.close();
    }

    public static int countDepth(BNode<Integer> root, BNode<Integer> node) {
        if (node == null) {
            return 0;
        }

        if (root == null) {
            return -1;
        }

        if (root.info.equals(node.info)) {
            return 0;
        }

        int leftDepth = countDepth(root.left, node);
        if (leftDepth >= 0) {
            return leftDepth + 1;
        }

        int rightDepth = countDepth(root.right, node);
        if (rightDepth >= 0) {
            return rightDepth + 1;
        }

        return -1;
    }
}
