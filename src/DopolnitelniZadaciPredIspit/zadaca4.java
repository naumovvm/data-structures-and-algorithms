package DopolnitelniZadaciPredIspit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;


public class zadaca4 {
    public static class BNode<E> {

        public E info;
        public BNode<E> left;
        public BNode<E> right;

        static int LEFT = 1;
        static int RIGHT = 2;

        public BNode(E info) {
            this.info = info;
            left = null;
            right = null;
        }

        public BNode() {
            this.info = null;
            left = null;
            right = null;
        }

        public BNode(E info, BNode<E> left, BNode<E> right) {
            this.info = info;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "" + info;
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
            root = new BNode(elem);
        }

        public void makeRootNode(BNode<E> node) {
            root = node;
        }

        public BNode<E> addChild(BNode<E> node, int where, E elem) {

            BNode<E> tmp = new BNode<E>(elem);

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

        public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {

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

    }

    interface Stack<E> {

        // Elementi na stekot se objekti od proizvolen tip.

        // Metodi za pristap:

        public boolean isEmpty();
        // Vrakja true ako i samo ako stekot e prazen.

        public E peek();
        // Go vrakja elementot na vrvot od stekot.

        // Metodi za transformacija:

        public void clear();
        // Go prazni stekot.

        public void push(E x);
        // Go dodava x na vrvot na stekot.

        public E pop();
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
    }

    public static class ArrayStack<E> implements Stack<E> {
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


        public void push(E x) {
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

    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode<String> nodes[] = new BNode[N];
        BTree<String> tree = new BTree<String>();

        for (i = 0; i < N; i++)
            nodes[i] = new BNode<String>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = st.nextToken();
            action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        int cases = Integer.parseInt(br.readLine());
        for (int l = 0; l < cases; l++) {
            String split[] = br.readLine().split(" +");
            String from = split[0];
            String to = split[1];

            // Vasiot kod ovde
            BNode<String> lowestCommonAncestor = findLCA(tree.root, from, to);
            if(lowestCommonAncestor == null){
                System.out.println("ERROR");
                continue;
            }

            int distFrom = findDistance(lowestCommonAncestor, from, 0);
            int distTo = findDistance(lowestCommonAncestor, to, 0);

            if(distFrom==-1 || distTo==-1){
                System.out.println("ERROR");
            }else{
                System.out.println(distFrom+distTo);
            }
        }
        br.close();
    }

    private static BNode<String> findLCA(BNode<String> root, String from, String to) {
        if (root == null) {
            return null;
        }

        if (root.info.equals(from) || root.info.equals(to)) {
            return root;
        }

        BNode<String> leftLCA = findLCA(root.left, from, to);
        BNode<String> rightLCA = findLCA(root.right, from, to);

        if (leftLCA != null) {
            return leftLCA;
        } else {
            return rightLCA;
        }
    }

    private static int findDistance(BNode<String> root, String target, int distance) {
        if (root == null) {
            return -1;
        }

        if(root.info.equals(target)){
            return distance;
        }

        int leftDistance = findDistance(root.left, target, distance+2);
        if(leftDistance != -1){
            return leftDistance;
        }

        return findDistance(root.right, target, distance+2);
    }
}