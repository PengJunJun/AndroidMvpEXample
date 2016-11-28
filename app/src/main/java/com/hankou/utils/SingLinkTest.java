package com.hankou.utils;

/**
 * Created by bykj003 on 2016/11/17.
 */

public class SingLinkTest<T> {

    public Node<T> head;

    private int count;

    public static class Node<T> {
        public T t;
        public Node<T> next;

        public Node(T t, Node<T> next) {
            this.t = t;
            this.next = next;
        }
    }

    public void addFirst(T t) {
        Node<T> old = head;
        Node<T> newNode = new Node<>(t, old);
        if (head == null) {
            head = newNode;
        } else {
            head.next = null;
        }
        count++;
    }

    public void add(T t) {
        Node<T> old = head;
        Node<T> newNode = new Node<>(t, null);
        if (old == null) {
            head = newNode;
        } else {
            head.next = newNode;
        }
        count++;
    }
}
