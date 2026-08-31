/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public abstract class User {
    protected String id;
    protected String userName;
    protected String password;

    public User(String id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
    public abstract void showInfor();
    public String getId() { return id; }
    public String getUserName() { return userName; }
    
    public boolean login(String inputUser, String inputpassword){
        return inputUser.equals(userName) && inputpassword.equals(password);
    }
    public void logout(){
        System.out.println("Logout successfully!");
    }
}
