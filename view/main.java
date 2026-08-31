/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dataStructure.MyStack;
import java.util.Queue;
import java.util.Stack;
import util.Menu;

public class main {
    public static void main(String[] args) {
        MyStack<String> test1 = new MyStack<>();
        //chưa có gì nên đang null
        System.out.println(test1.isEmpty());
        // đã có đĩa nên null
        test1.push("dia 1");
        System.out.println(test1.isEmpty());
        //pop là lấy ra rồi, nên sẽ true
        test1.pop();
        System.out.println(test1.isEmpty());
    
    }
}
