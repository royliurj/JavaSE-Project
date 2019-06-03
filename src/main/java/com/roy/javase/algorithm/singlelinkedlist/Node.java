package com.roy.javase.algorithm.singlelinkedlist;

/**
 * @Author: Roy
 * @Date: 2019/6/3 10:36
 */
public class Node {
    Node next = null;
    int data;
    public Node(int data){
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
