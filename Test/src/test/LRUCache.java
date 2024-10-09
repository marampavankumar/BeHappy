package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache {

  //HashMap as a cache store as it has O(1) for lookup.
  //Doubly linked list to support insertion/deletion at any place.
  Map<Integer,Node> intStore;
  Node head;
  Node tail;
  int cacheSize;
  //read/write locks for thread safety
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  private final Lock readLock = lock.readLock();
  private final Lock writeLock = lock.writeLock();


  class Node {
    Node prev;
    Node next;
    Integer key;
    Integer value;

    Node(Integer key, Integer value) {
      this.key = key;
      this.value = value;
    }

    void setValue(int value) {
      this.value = value;
    }
  }

  public LRUCache(int cacheSize) {
    intStore = new HashMap<>();
    head = new Node(0, 0);
    tail = new Node(0, 0);
    head.next = tail;
    tail.prev = head;
    this.cacheSize = cacheSize;
  }

  //If node is present, remove it and add to head
  public int get(int key) {
    Node reqNode = null;
    readLock.lock();
    try {
      reqNode = intStore.get(key);
    } finally {
      readLock.unlock();
    }
    if (reqNode != null) {
      writeLock.lock();
      try {
        remove(reqNode);
        addToHead(reqNode);
        return reqNode.value;
      } finally {
          writeLock.unlock();
      }
    }
    return -1;

  }

  //if node is present, update value, remove and add to head.
  //else remove tail node if capacity is full and add new node to head and put into map
  public void put (int key, int value) {
    writeLock.lock();
    try {
      Node reqNode = intStore.get(key);
      if (reqNode != null) {
        reqNode.setValue(value);
        remove(reqNode);
        addToHead(reqNode);
      } else {
        if (intStore.size() >= cacheSize) {
          intStore.remove(tail.prev.key);
          remove(tail.prev);
        }
        reqNode = new Node(key, value);
        addToHead(reqNode);
        intStore.put(key, reqNode);
      }
    } finally {
        writeLock.unlock();
    }
  }


  private void addToHead(Node reqNode) {
    reqNode.next = head.next;
    reqNode.prev = head;
    head.next.prev = reqNode;
    head.next = reqNode;
  }

  private void remove(Node reqNode) {
    reqNode.prev.next = reqNode.next;
    reqNode.next.prev = reqNode.prev;
  }

  public static void main(String[] args) {
    LRUCache lruCache = new LRUCache(3);
    lruCache.put(1, 1);
    lruCache.put(2, 2);
    lruCache.put(3, 3);
    System.out.println(lruCache.get(1));
    lruCache.put(4, 4);
    System.out.println(lruCache.get(2));
    System.out.println(lruCache.get(3));
    lruCache.put(5, 5);
    System.out.println(lruCache.get(1));
    System.out.println(lruCache.get(3));
    System.out.println(lruCache.get(4));
    lruCache.put(6, 6);
    System.out.println(lruCache.get(5));

  }
}