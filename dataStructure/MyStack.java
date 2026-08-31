/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructure;



public class MyStack<T> {
    public Node<T> top;
    // node là gì ?
    // là một hộp chứa 2 thứ 
        //-1 dữ liệu thật sự 
        //-2 địa chỉ trỏ tới hộp tiếp theo
    //Tại sao không dùng mảng (array) cho gọn, phải tách Node riêng làm gì?
    // phải đoán trước có bao nhiêu phần tử - Mem[] arr = new Member[5]
    // xóa và chèn ở giữa vẫn được nhưng phức tạp
    // giải pháp ? ---> dùng node(linked list)
   // đặt thêm 1 phần tử lên trên cùng
    public void push(T item){
        // tao mới 1 node
        // ví dụ: ta đã có 2 đĩa đĩa 2 -> đĩa 1 -> null
        //( top đang trỏ vào đĩa 2)
       Node<T> newNode = new Node<>(item);
       // ta có newNode --> (dia 3)
       newNode.next = top;
       //
       //newNode.next đang mang giá trị null và cho nó lên đầu
       //
       top = newNode;
       // giá trị 
    }
    // lấy phần tử trên cùng ra
    public T pop (){
        if(isEmpty()){
            throw new RuntimeException("Stack đang trông, không thể pop");
        }
        T result = top.data;
        top = top.next;
        return result;
    }
    public T peek(){
        if(isEmpty()){
            throw new RuntimeException();
        }
        return top.data;
    }
    public boolean isEmpty(){
        return top == null;
    }
}
