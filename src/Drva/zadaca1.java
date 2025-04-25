package Drva;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class zadaca1 { // lab 1 za drva
    public interface Tree<E> {
        // //////////Accessors ////////////

        public Tree.Node<E> root();

        public Tree.Node<E> parent(Tree.Node<E> node);

        public int childCount(Tree.Node<E> node);

        // //////////Transformers ////////////
        public void makeRoot(E elem);

        public Tree.Node<E> addChild(Tree.Node<E> node, E elem);

        public void remove(Tree.Node<E> node);

        // //////////Iterator ////////////
        public Iterator<E> children(Tree.Node<E> node);

        // //////Inner interface for tree nodes ////////
        public interface Node<E> {

            public E getElement();

            public void setElement(E elem);

        }
    }

    public static class SLLTree<E> implements Tree<E> {

        // SLLNode is the implementation of the Node interface
        static class SLLNode<P> implements Node<P> {

            // Holds the links to the needed nodes
            SLLNode<P> parent, sibling, firstChild;

            // Hold the data
            P element;

            public SLLNode(P o) {
                element = o;
                parent = sibling = firstChild = null;
            }

            public P getElement() {
                return element;
            }

            public void setElement(P o) {
                element = o;
            }

        }

        protected SLLNode<E> root;

        public SLLTree() {
            root = null;
        }

        public Node<E> root() {
            return root;
        }

        public Tree.Node<E> parent(Tree.Node<E> node) {
            return ((SLLNode<E>) node).parent;
        }

        public int childCount(Tree.Node<E> node) {
            SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
            int num = 0;
            while (tmp != null) {
                tmp = tmp.sibling;
                num++;
            }
            return num;
        }

        public int countLeavesR(SLLNode<E> node) {
            if (node == null) {
                return 0; // ako e null direkt vrakjaj 0
            }

            if (childCount(node) == 0) {
                return 1; // ako nema deca znaci samiot node e list, taka da vrakjame 1
            }

            int count = 0; // pravime counter
            SLLNode<E> child = node.firstChild; // go zemame prvoto dete

            while (child != null) { // vrtime u loop
                count += countLeavesR(child); // broime kolku deca ima
                child = child.sibling; // isto kako .succ, odime na sledno dete
            }

            return count; // go vrakjame brojacot
        }

        public void makeRoot(E elem) {
            root = new SLLNode<E>(elem);
        }

        public Node<E> addChild(Node<E> node, E elem) {
            SLLNode<E> tmp = new SLLNode<E>(elem);
            SLLNode<E> curr = (SLLNode<E>) node;
            tmp.sibling = curr.firstChild;
            curr.firstChild = tmp;
            tmp.parent = curr;
            return tmp;
        }

        public void remove(Tree.Node<E> node) {
            SLLNode<E> curr = (SLLNode<E>) node;
            if (curr.parent != null) {
                if (curr.parent.firstChild == curr) {
                    // The node is the first child of its parent
                    // Reconnect the parent to the next sibling
                    curr.parent.firstChild = curr.sibling;
                } else {
                    // The node is not the first child of its parent
                    // Start from the first and search the node in the sibling list
                    // and remove it
                    SLLNode<E> tmp = curr.parent.firstChild;
                    while (tmp.sibling != curr) {
                        tmp = tmp.sibling;
                    }
                    tmp.sibling = curr.sibling;
                }
            } else {
                root = null;
            }
        }

        class SLLTreeIterator<T> implements Iterator<T> {

            SLLNode<T> start, current;

            public SLLTreeIterator(SLLNode<T> node) {
                start = node;
                current = node;
            }

            public boolean hasNext() {
                return (current != null);
            }

            public T next() throws NoSuchElementException {
                if (current != null) {
                    SLLNode<T> tmp = current;
                    current = current.sibling;
                    return tmp.getElement();
                } else {
                    throw new NoSuchElementException();
                }
            }

            public void remove() {
                if (current != null) {
                    current = current.sibling;
                }
            }
        }

        public Iterator<E> children(Tree.Node<E> node) {
            return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
        }

        void printTreeRecursive(Node<E> node, int level) {
            if (node == null)
                return;
            int i;
            SLLNode<E> tmp;

            for (i = 0; i < level; i++)
                System.out.print("  ");
            System.out.println(node.getElement().toString());
            tmp = ((SLLNode<E>) node).firstChild;
            while (tmp != null) {
                printTreeRecursive(tmp, level + 1);
                tmp = tmp.sibling;
            }
        }

        public void printTree() {
            printTreeRecursive(root, 0);
        }

        public int countMaxChildren() {
            return countMaxChildrenRecursive(root);
        }

        int countMaxChildrenRecursive(SLLNode<E> node) {
            int t = childCount(node);
            SLLNode<E> tmp = node.firstChild;

            while (tmp != null) {
                t = Math.max(t, countMaxChildrenRecursive(tmp));
                tmp = tmp.sibling;
            }
            return t;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int q = input.nextInt();
        SLLTree<Integer> tree = new SLLTree<Integer>();
        HashMap<Integer, SLLTree.SLLNode<Integer>> hash = new HashMap<Integer, SLLTree.SLLNode<Integer>>();
        // Hash mapa koristime tuka so kluc Integer i vrednost Node-ot
        // sluzi za da ne pravam find funkcija za sekoj jazol
        for (int i = 0; i < n + q; i++) {
            String command = input.next();
            if (command.equals("root")) {
                int val = input.nextInt();
                tree.makeRoot(val);
                hash.put(val, tree.root); // go dodavam vo hash-ot za da znam kade e
            } else if (command.equals("add")) {
                int parentIndex = input.nextInt();
                int childIndex = input.nextInt();
                SLLTree.SLLNode<Integer> parent = hash.get(parentIndex); // za da go najdam roditelot, koristam mapa direktno so .get()
                // potoa dodavam dete so tree.addChild() komandata, kade gi prakjam parent node i index-ot na deteto
                SLLTree.SLLNode<Integer> child = (SLLTree.SLLNode<Integer>) tree.addChild(parent, childIndex);  // Kreiram child koj ke ja ima vrednosta na node-ot, bidejki addChild() vrakja node
                // toj child node potoa go stavam vo hash mapata vo slucaj da ni pritreba ponatamu
                hash.put(childIndex, child);
            } else if (command.equals("ask")) {
                int askingIndex = input.nextInt();
                SLLTree.SLLNode<Integer> askingNode = hash.get(askingIndex); // tuka isto so .get() go naogjam node-ot koj mi treba
                if (askingNode != null) { // ako ne e null, gi brojam negovite deca
                    System.out.println(tree.countLeavesR(askingNode));
                }
            }
        }

        input.close();
    }
}
