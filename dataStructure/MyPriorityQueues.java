package dataStructure;

import java.util.Comparator;

public class MyPriorityQueues<T> {
    private Node<T> head; 
    private Comparator<T> comparator;

    public MyPriorityQueues(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue đang rỗng, không thể dequeue()");
        }
        T result = head.data;
        head = head.next;
        return result;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue đang rỗng, không thể peek()");
        }
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }
    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty() || comparator.compare(item, head.data) < 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node<T> current = head;
        while (current.next != null && comparator.compare(item, current.next.data) >= 0) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }
}