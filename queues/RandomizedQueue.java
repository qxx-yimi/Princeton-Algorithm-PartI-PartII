import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int CAPACITY = 10;
    private Item[] q;
    private int head, tail;
    private int sz;

    public RandomizedQueue() {
        q = (Item[]) new Object[CAPACITY];
        head = 0;
        tail = 0;
        sz = 0;
    }

    public boolean isEmpty() {
        return sz == 0;
    }

    public int size() {
        return sz;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("argument can't be null");
        }
        if (tail == q.length) resize(2 * q.length);
        q[tail++] = item;
        sz += 1;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        int rIndex = StdRandom.uniformInt(head, tail);
        Item item = q[rIndex];
        q[rIndex] = q[--tail];
        q[tail] = null;
        sz -= 1;
        if (!isEmpty() && sz == q.length / 4) resize(q.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        int rIndex = StdRandom.uniformInt(head, tail);
        return q[rIndex];
    }


    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int itemSize = sz;
        private int cur = head;
        private int[] shuffled;

        public RandomizedQueueIterator() {
            shuffled = new int[q.length];
            for (int i = 0; i < shuffled.length; i++) {
                shuffled[i] = i;
            }
            StdRandom.shuffle(shuffled, head, tail);
        }

        public boolean hasNext() {
            return itemSize > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no more items");
            }
            Item item = q[shuffled[cur++]];
            itemSize -= 1;
            return item;

        }

        public void remove() {
            throw new UnsupportedOperationException("unsupport remove method");
        }

    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int cnt = 0;
        for (int i = head; i < tail; i++) {
            copy[cnt++] = q[i];
        }
        head = 0;
        tail = cnt;
        q = copy;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        for (int i = 0; i < 15; i++) {
            q.enqueue("" + i);
        }
        for (String s : q) {
            System.out.print(s + " ");
        }
        System.out.println();

        for (int i = 0; i < 12; i++) {
            String item = q.dequeue();
            System.out.print(item + " ");
        }
        System.out.println();

        System.out.println(q.size());

        for (String s : q) {
            System.out.print(s + " ");
        }

        System.out.println();
    }
}
