package com.greatestsasha.training.leetcode;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class NaiveStack<T> {

    private Node<T> head;
    @Getter
    private int size;

    public NaiveStack() {
        this.size = (0);
    }

    public void push(T value) throws InterruptedException {
        size++;
        Node<T> newHead = new Node<>(value);
        newHead.next = head;
        head = newHead;
    }

    public T pop() throws InterruptedException {
        size--;
        if (head == null) {
            return null;
        }
        T val = head.value;
        head = head.next;
        return val;
    }

    public Optional<T> peek() {
        if (head == null) {
            return Optional.empty();
        }
        return Optional.of(head.value);
    }
    //sergey.nazaryev@raiffesen.ru

    public static class Node<T> {
        @Getter
        @Setter
        Node<T> next;
        @Getter
        final T value;

        public Node(T value) {
            this.value = value;
        }
    }
}