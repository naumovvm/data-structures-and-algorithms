package FinkiAPSBook.Arrays;

import java.util.Scanner;

public class zadaca2 {
    @SuppressWarnings("unchecked")
    public static class Array<E> {
        private E data[]; // declared to be an Object since it would be too
        // complicated with generics
        private int size;

        public Array(int capacity) {
            this.data = (E[]) new Object[capacity];
            this.size = 0;
        }

        public void insertLast(E o) {
            //check if there is enough capacity, and if not - resize
            if (size + 1 > data.length)
                this.resize();
            data[size++] = o;
        }

        public void insert(int position, E o) {
            // before all we check if position is within range
            if (position >= 0 && position <= size) {
                //check if there is enough capacity, and if not - resize
                if (size + 1 > data.length)
                    this.resize();
                //copy the data, before doing the insertion
                for (int i = size; i > position; i--) {
                    data[i] = data[i - 1];
                }
                data[position] = o;
                size++;
            } else {
                System.out.println("Ne mozhe da se vmetne element na taa pozicija");
            }
        }

        public void set(int position, E o) {
            if (position >= 0 && position < size)
                data[position] = o;
            else
                System.out.println("Ne moze da se vmetne element na dadenata pozicija");
        }

        public E get(int position) {
            if (position >= 0 && position < size)
                return data[position];
            else
                System.out.println("Ne e validna dadenata pozicija");
            return null;
        }

        public int find(E o) {
            for (int i = 0; i < size; i++) {
                if (o.equals(data[i]))
                    return i;
            }
            return -1;
        }

        public int getSize() {
            return size;
        }

        public void delete(int position) {
            // before all we check if position is within range
            if (position >= 0 && position < size) {
                // first resize the storage array
                E[] newData = (E[]) new Object[size - 1];
                // copy the data prior to the delition
                for (int i = 0; i < position; i++)
                    newData[i] = data[i];
                // move the data after the deletion
                for (int i = position + 1; i < size; i++)
                    newData[i - 1] = data[i];
                // replace the storage with the new array
                data = newData;
                size--;
            }
        }

        public void resize() {
            // first resize the storage array
            E[] newData = (E[]) new Object[size * 2];
            // copy the data
            for (int i = 0; i < size; i++)
                newData[i] = data[i];
            // replace the storage with the new array
            this.data = newData;
        }

        @Override
        public String toString() {
            String ret = new String();
            if (size > 0) {
                ret = "{";
                ret += data[0];
                for (int i = 1; i < size; i++) {
                    ret += "," + data[i];
                }
                ret += "}";
                return ret;
            } else {
                ret = "Prazna niza!";
            }
            return ret;
        }

    }

    public static void main(String[] args) { // ako ima isti elementi na isti pozicii vo dve nizi, da se izbrishat
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Array<Integer> niza1 = new Array<>(n);
        Array<Integer> niza2 = new Array<>(n);

        for (int i = 0; i < n; i++) {
            niza1.insertLast(input.nextInt());
        }

        for (int i = 0; i < n; i++) {
            niza2.insertLast(input.nextInt());
        }

        int i = 0;
        while (i<n) {
            if (niza1.get(i).equals(niza2.get(i))) {
                niza1.delete(i);
                niza2.delete(i);
                n--;
            }else{
                i++;
            }
        }

        System.out.println(niza1);
        System.out.println(niza2);

        input.close();
    }
}
