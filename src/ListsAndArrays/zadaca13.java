package ListsAndArrays;

import java.util.Scanner;

public class zadaca13 {
    public static class DLLNode<E> {
        protected E element;
        protected DLLNode<E> pred, succ;

        public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
            this.element = elem;
            this.pred = pred;
            this.succ = succ;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    public static class DLL<E> {
        private DLLNode<E> first, last;

        public DLL() {
            // Construct an empty SLL
            this.first = null;
            this.last = null;
        }

        public void insertFirst(E o) {
            DLLNode<E> ins = new DLLNode<E>(o, null, first);
            if (first == null)
                last = ins;
            else
                first.pred = ins;
            first = ins;
        }

        public void insertLast(E o) {
            if (first == null)
                insertFirst(o);
            else {
                DLLNode<E> ins = new DLLNode<E>(o, last, null);
                last.succ = ins;
                last = ins;
            }
        }

        public void insertAfter(E o, DLLNode<E> after) {
            if (after == last) {
                insertLast(o);
                return;
            }
            DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
            after.succ.pred = ins;
            after.succ = ins;
        }

        public void insertBefore(E o, DLLNode<E> before) {
            if (before == first) {
                insertFirst(o);
                return;
            }
            DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
            before.pred.succ = ins;
            before.pred = ins;
        }

        public E deleteFirst() {
            if (first != null) {
                DLLNode<E> tmp = first;
                first = first.succ;
                if (first != null) first.pred = null;
                if (first == null)
                    last = null;
                return tmp.element;
            } else
                return null;
        }

        public E deleteLast() {
            if (first != null) {
                if (first.succ == null)
                    return deleteFirst();
                else {
                    DLLNode<E> tmp = last;
                    last = last.pred;
                    last.succ = null;
                    return tmp.element;
                }
            } else
                return null;
        }

        public E delete(DLLNode<E> node) {
            if (node == first) {
                return deleteFirst();
            }
            if (node == last) {
                return deleteLast();
            }
            node.pred.succ = node.succ;
            node.succ.pred = node.pred;
            return node.element;

        }

        public DLLNode<E> find(E o) {
            if (first != null) {
                DLLNode<E> tmp = first;
                while (!tmp.element.equals(o) && tmp.succ != null)
                    tmp = tmp.succ;
                if (tmp.element.equals(o)) {
                    return tmp;
                } else {
                    System.out.println("Elementot ne postoi vo listata");
                }
            } else {
                System.out.println("Listata e prazna");
            }
            return null;
        }

        public void deleteList() {
            first = null;
            last = null;
        }

        public int getSize() {
            int listSize = 0;
            DLLNode<E> tmp = first;
            while (tmp != null) {
                listSize++;
                tmp = tmp.succ;
            }
            return listSize;
        }

        @Override
        public String toString() {
            String ret = new String();
            if (first != null) {
                DLLNode<E> tmp = first;
                ret += tmp.toString();
                while (tmp.succ != null) {
                    tmp = tmp.succ;
                    ret += "<->" + tmp.toString();
                }
            } else
                ret = "Prazna lista!!!";
            return ret;
        }

        public String toStringR() {
            String ret = new String();
            if (last != null) {
                DLLNode<E> tmp = last;
                ret += tmp.toString();
                while (tmp.pred != null) {
                    tmp = tmp.pred;
                    ret += "<->" + tmp.toString();
                }
            } else
                ret = "Prazna lista!!!";
            return ret;
        }

        public DLLNode<E> getFirst() {
            return first;
        }

        public DLLNode<E> getLast() {

            return last;
        }

        public void mirror() {

            DLLNode<E> tmp = null;
            DLLNode<E> current = first;
            last = first;
            while (current != null) {
                tmp = current.pred;
                current.pred = current.succ;
                current.succ = tmp;
                current = current.pred;
            }

            if (tmp != null && tmp.pred != null) {
                first = tmp.pred;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        DLL<Integer> list = new DLL<>();

        for (int i = 0; i < n; i++) {
            int val = input.nextInt();
            list.insertLast(val);
        }

        DLLNode<Integer> node = list.getFirst();
        int count = 0;
        for (int i = 0; i < n; i++) {
            double leftSum = 0, rightSum = 0;
            int leftCount = 0, rightCount = 0;

            DLLNode<Integer> temp = list.getFirst();
            for (int j = 0; j < i; j++) { // se sobiraat tuka site elementi levo od i indeksot
                leftSum += temp.element;
                leftCount++; // i plus broime kolku se
                temp = temp.succ;
            }

            temp = node.succ;
            while (temp != null) { // so via loop se izminuvat desno od i indeksot elementite
                rightSum += temp.element;
                rightCount++; // isto i tuka broime kolku se
                temp = temp.succ;
            }

            double leftAvg = 0;
            if (leftCount > 0) { // ako left count ni e pogolem od 0, togash presmetuvame lev prosek
                leftAvg = leftSum / leftCount;
            } else {
                leftAvg = 0; // ako ne prosekot ni e nula, odnosno ako nemame elementi od levo
            }

            double rightAvg = 0;
            if (rightCount > 0) { // ako imam elementi od desno presmetuvame desen prosek
                rightAvg = rightSum / rightCount;
            } else {
                rightAvg = 0; // ako ne i nego go pravime 0
            }

            if (leftAvg > rightAvg && leftCount > 0 && rightCount > 0) { // bidejki se bara leviot prosek od i da e pogolem od desniot
                count++; // count se zgolemuva samo ako leviot e pogolem od desniot prosek i leviot count e pogolem od desniot
            }

            node = node.succ;
        }

        System.out.println(count);
        input.close();
    }
}
