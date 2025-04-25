package Drva;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class vleznaDlabocina {

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


//        public void push (E x) {
//            // Go dodava x na vrvot na stekot.
//            elems[depth++] = x;
//        }


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

//            if ((node.left == null) && (node.right == null))
//                return 0;

            int count = insideNodesR(node.left) + insideNodesR(node.right); // broe kolku deca ima levo i desno
            return count + 1;
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

            int count = (depthR(node.left) + depthR(node.right));
            return count + 1;
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

        public int countDepth(BNode<Integer> node, int depth) {
            if (node == null) { // ako ne postoe jazolot, dlabocinata e 0
                return 0;
            }
            // rekurzivno se povikuva funkcijata za levoto i desnoto dete na jazolot, so sekoj povik depth se zgolemuva +1
            int count = countDepth(node.left, depth + 1) + countDepth(node.right, depth + 1);
            return count + depth; // sobirame count-ot od levoto i desnoto poddrvo, plus toa od samiot node sho go prakjame
        }

        public static int countNodes(BNode<Integer> node) {
            if (node == null) { // ako e null, nemame jazol i zatoa e 0
                return 0;
            }

            int count = countNodes(node.left) + countNodes(node.right); // gi broime levite i desnite jazli
            return count + 1; // dodavame +1 bidejki se broi i samiot jazol
        }
    }

    public static void main(String[] args) {
        // koja e prosecnata dlabocina na jazlite na vo poddrvoto na izbran jazol
        // korenite na poddrvoto se smetaat za dlabocina 1
        // suma od dlabocinite deleno so brojot na jazli vo poddrvoto
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int q = input.nextInt();
        BTree<Integer> tree = new BTree<>();
        HashMap<Integer, BNode<Integer>> hash = new HashMap<>();

        for (int i = 0; i < (n + q); i++) {
            String command = input.next();
            if (command.equals("root")) {
                int val = input.nextInt();
                tree.makeRoot(val);
                hash.put(val, tree.root);
            } else if (command.equals("add")) {
                int parent = input.nextInt();
                int child = input.nextInt();
                String where = input.next();
                int whereINT = 0;

                if (where.equals("LEFT")) {
                    whereINT = 1;
                } else {
                    whereINT = 2;
                }

                BNode<Integer> parentNode = hash.get(parent);
                BNode<Integer> childNode = tree.addChild(parentNode, whereINT, child);
                hash.put(child, childNode);
            } else if (command.equals("ask")) {
                int asking = input.nextInt();
                BNode<Integer> askingNode = hash.get(asking);

                int suma = tree.countDepth(askingNode, 1);
                int count = tree.countNodes(askingNode);
                float rezultat = (float) suma / count;

                System.out.println("REZULTAT: " + rezultat);
                System.out.println();
            }
        }

        input.close();
    }
}
//        11 5
//        root 1
//        add 1 2 LEFT
//        add 2 3 RIGHT
//        ask 1
//        add 1 4 RIGHT
//        add 2 5 LEFT
//        add 3 6 LEFT
//        ask 2
//        add 3 7 RIGHT
//        ask 1
//        add 4 8 LEFT
//        add 8 9 RIGHT
//        add 5 10 RIGHT
//        add 4 11 RIGHT
//        ask 2
//        ask 1
//
//        Result:
//        2.0
//        2.0
//        2.7142856
//        2.3333333
//        3.0