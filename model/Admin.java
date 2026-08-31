/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Admin extends User{

    public Admin(String id, String userName, String password) {
        super(id, userName, password);
    }
    
    @Override
    public void showInfor() {
       String str = String.format("%s|%s|%s", id, userName, password);
    }
    
}
