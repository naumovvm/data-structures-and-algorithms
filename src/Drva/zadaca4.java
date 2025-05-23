package Drva;

import java.util.HashMap;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Stack;

public class zadaca4 { // Drva treta
    static class ArrayStack<E> extends Stack<E> {
        private E[] elems;
        private int depth;

        @SuppressWarnings("unchecked")
        public ArrayStack(int maxDepth) {
            // Konstrukcija na nov, prazen stek.
            elems = (E[]) new Object[maxDepth];
            depth = 0;
        }


        public boolean isEmpty() {
            // Vrakja true ako i samo ako stekot e prazen.
            return (depth == 0);
        }


        public E peek() {
            // Go vrakja elementot na vrvot od stekot.
            if (depth == 0)
                throw new NoSuchElementException();
            return elems[depth - 1];
        }


        public void clear() {
            // Go prazni stekot.
            for (int i = 0; i < depth; i++) elems[i] = null;
            depth = 0;
        }


        public void pushAS(E x) {
            // Go dodava x na vrvot na stekot.
            elems[depth++] = x;
        }


        public E pop() {
            // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
            if (depth == 0)
                throw new NoSuchElementException();
            E topmost = elems[--depth];
            elems[depth] = null;
            return topmost;
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

    public static class BTree<E> {

        public BNode<E> root;

        public BTree() {
            root = null;
        }

        public BTree(E info) {
            root = new BNode<E>(info);
        }

        public void makeRoot(E elem) {
            root = new BNode<E>(elem);
        }

        public BNode<E> addChild(BNode<E> node, int where, E elem) {

            BNode<E> tmp = new BNode<E>(elem, node);

            if (where == BNode.LEFT) {
                if (node.left != null)  // veke postoi element
                    return null;
                node.left = tmp;
            } else {
                if (node.right != null) // veke postoi element
                    return null;
                node.right = tmp;
            }

            return tmp;
        }

        public void inorder() {
            System.out.print("INORDER: ");
            inorderR(root);
            System.out.println();
        }

        public void inorderR(BNode<E> n) {
            if (n != null) {
                inorderR(n.left);
                System.out.print(n.info.toString() + " ");
                inorderR(n.right);
            }
        }

        public void preorder() {
            System.out.print("PREORDER: ");
            preorderR(root);
            System.out.println();
        }

        public void preorderR(BNode<E> n) {
            if (n != null) {
                System.out.print(n.info.toString() + " ");
                preorderR(n.left);
                preorderR(n.right);
            }
        }

        public void postorder() {
            System.out.print("POSTORDER: ");
            postorderR(root);
            System.out.println();
        }

        public void postorderR(BNode<E> n) {
            if (n != null) {
                postorderR(n.left);
                postorderR(n.right);
                System.out.print(n.info.toString() + " ");
            }
        }

        public void inorderNonRecursive() {
            ArrayStack<BNode<E>> s = new ArrayStack<BNode<E>>(100);
            BNode<E> p = root;
            System.out.print("INORDER (nonrecursive): ");

            while (true) {
                // pridvizuvanje do kraj vo leva nasoka pri sto site koreni
                // na potstebla se dodavaat vo magacin za podocnezna obrabotka
                while (p != null) {
                    s.push(p);
                    p = p.left;
                }

                // ako magacinot e prazen znaci deka stebloto e celosno izminato
                if (s.isEmpty())
                    break;

                p = s.peek();
                // pecatenje (obrabotka) na jazelot na vrvot od magacinot
                System.out.print(p.info.toString() + " ");
                // brisenje na obraboteniot jazel od magacinot
                s.pop();
                // pridvizuvanje vo desno od obraboteniot jazel i povtoruvanje na
                // postapkata za desnoto potsteblo na jazelot
                p = p.right;

            }
            System.out.println();

        }

        int insideNodesR(BNode<E> node) {
            if (node == null)
                return 0;

            if ((node.left == null) && (node.right == null))
                return 0;

            return insideNodesR(node.left) + insideNodesR(node.right) + 1;
        }

        public int insideNodes() {
            return insideNodesR(root);
        }

        int leavesR(BNode<E> node) {
            if (node != null) {
                if ((node.left == null) && (node.right == null))
                    return 1;
                else
                    return (leavesR(node.left) + leavesR(node.right));
            } else {
                return 0;
            }
        }

        public int leaves() {
            return leavesR(root);
        }

        int depthR(BNode<E> node) {
            if (node == null)
                return 0;
            if ((node.left == null) && (node.right == null))
                return 0;
            return (1 + Math.max(depthR(node.left), depthR(node.right)));
        }

        public int depth() {
            return depthR(root);
        }

        void mirrorR(BNode<E> node) {
            BNode<E> tmp;

            if (node == null)
                return;

            // simetricno preslikuvanje na levoto i desnoto potsteblo
            mirrorR(node.left);
            mirrorR(node.right);

            // smena na ulogite na pokazuvacite na momentalniot jazel
            tmp = node.left;
            node.left = node.right;
            node.right = tmp;

        }

        public void mirror() {
            mirrorR(root);
        }

        int twoChildren(BNode<E> node) {
            if (node == null) { // ako nema jazol, null
                return 0;
            }

            int count = 0;
            if(node.left!=null && node.right!=null){ // ako ima dete i levo i denso
                count+=1;
            }

            count+=twoChildren(node.left)+twoChildren(node.right); // rekurzivno levo i desno poddrvo gi proveruva
            return count;
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int q = input.nextInt();
        BTree<String> tree = new BTree<>();
        HashMap<String, BNode<String>> hash = new HashMap<>();

        for (int i = 0; i < (n + q); i++) {
            String command = input.next();
            if (command.equals("root")) {
                String root = input.next();
                tree.makeRoot(root);
                hash.put(root, tree.root);
            } else if (command.equals("add")) {
                String parent = input.next();
                String child = input.next();
                String where = input.next();
                int whereINT = 0;

                if (where.equals("LEFT")) {
                    whereINT = 1;
                } else {
                    whereINT = 2;
                }

                BNode<String> parentNode = hash.get(parent);
                BNode<String> childNode = tree.addChild(parentNode, whereINT, child);
                hash.put(child, childNode);
            } else if (command.equals("ask")) {
                String asking = input.next();
                BNode<String> askingNode = hash.get(asking);
                if (askingNode != null) {
                    System.out.println(tree.twoChildren(askingNode));
                }
            }
        }

        input.close();
    }
}
