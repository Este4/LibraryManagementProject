
package dataStructure;

public class Node<T> {
    T data; // đây là thông tin người
    Node<T> next; //đây là tờ giấy 
    
    public Node(T data){
        this.data = data;
        this.next = null;
    }
    
}
