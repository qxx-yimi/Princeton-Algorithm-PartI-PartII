import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node pre;
        Node next;
    }

    private Node head, tail;
    private int sz;

    public Deque() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        sz = 0;
    }

    public boolean isEmpty() {
        return sz == 0;
    }

    public int size() {
        return sz;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("argument can not be null");
        }
        Node newNode = new Node();
        newNode.item = item;

        newNode.next = head.next;
        head.next.pre = newNode;
        head.next = newNode;
        newNode.pre = head;

        sz += 1;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("argument can not be null");
        }
        Node newNode = new Node();
        newNode.item = item;

        tail.pre.next = newNode;
        newNode.pre = tail.pre;
        newNode.next = tail;
        tail.pre = newNode;

        sz += 1;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("deque is empty can't remove item");
        }
        Item item = head.next.item;
        head.next = head.next.next;
        head.next.pre = head;
        sz -= 1;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("deque is empty can't remove item");
        }
        Item item = tail.pre.item;
        tail.pre = tail.pre.pre;
        tail.pre.next = tail;
        sz -= 1;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node cur = head.next;

        public boolean hasNext() {
            return cur != tail;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no more items");
            }
            Item item = cur.item;
            cur = cur.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("unsupport remove method");
        }
    }

    public static void main(String[] args) {
        Deque<String> q = new Deque<String>();
        System.out.println("is empty" + q.isEmpty());

        q.addFirst("1");
        q.addFirst("2");
        q.addLast("3");
        q.addLast("4");
        for (String s : q) {
            StdOut.print(s + " ");
        }
        System.out.println();

        String first = q.removeFirst();
        String last = q.removeLast();
        StdOut.print("f:" + first + "l:" + last);
        System.out.println();

        for (String s : q) {
            StdOut.print(s + " ");
        }
        System.out.println();

        System.out.println("size: " + q.size());

        System.out.println("is empty" + q.isEmpty());
    }
}
