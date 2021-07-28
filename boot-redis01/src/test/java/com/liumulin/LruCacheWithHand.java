package com.liumulin;

import java.util.*;

/**
 * 手写 LRU
 *
 * @author liuqiang
 * @since 2021-07-28
 */
public class LruCacheWithHand {

    // Map 负责查找，构建一个虚拟的双向链表，里面装的是一个个 Node 节点作为数据载体
    Map<Integer, Node<Integer, Integer>> map;
    DoubleLinkedList<Integer, Integer> doubleLinkedList;
    private Integer cacheSize;

    /**
     * 初始化好 LRU 雏形，HashMap + DoubleLinkedList
     */
    public LruCacheWithHand(Integer cacheSize) {
        this.cacheSize = cacheSize; // 坑位
        map = new HashMap<>(); // 用于查找 Node
        doubleLinkedList = new DoubleLinkedList<>();
    }

    public static void main(String[] args) {
        LruCacheWithHand lruCache = new LruCacheWithHand(3);
        lruCache.put(1, 11);
        int i = lruCache.get(1);

        lruCache.put(2, 22);
        lruCache.put(3, 33);
        System.out.println(lruCache.map.keySet());
        lruCache.put(4, 44);
        System.out.println(lruCache.map.keySet());
        lruCache.put(3, 33);
        System.out.println(lruCache.map.keySet());
        lruCache.put(3, 33);
        System.out.println(lruCache.map.keySet());
        lruCache.put(5, 55);
        System.out.println(lruCache.map.keySet());
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addNode(node);
        return node.value;
    }

    // saveOrUpdate
    public void put(int key, int value) {
        if (map.containsKey(key)) { // update
            // put(1,2) put(1,3)
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addNode(node);
        } else {
            if (map.size() == cacheSize) { // 坑位满了，删除最后一个 Node
                Node<Integer, Integer> lastNode = doubleLinkedList.getLast();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }
        }
        // 新增 Node 节点
        Node<Integer, Integer> newNode = new Node<>(key, value);
        map.put(key, newNode);
        doubleLinkedList.addNode(newNode);
    }

    /**
     * 1. 构造一个 Node 节点作为数据载体
     *
     * @see java.util.HashMap 数组 + 单向链表
     */
    class Node<K, V> {

        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;

        public Node() {
            this.next = this.prev = null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = this.prev = null;
        }
    }

    // 虚拟一个双向链表，里面放 Node 节点
    class DoubleLinkedList<K, V> {

        Node<K, V> head;
        Node<K, V> tail;

        // 2.1 构建双向链表
        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        // 2.2 添加节点
        public void addNode(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        // 2.3 删除节点
        public void removeNode(Node<K, V> node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }

        // 2.4 获取最后一个节点
        public Node<K, V> getLast() {
            return tail.prev;
        }
    }
}