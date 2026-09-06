/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.Date;
import enums.MemberStatusEnum;
import interfaces.Observer;
import java.util.ArrayList;
import java.util.List;

public class Member extends User implements Observer {
    private String inbox;
    private int waitingOrder;
    private int maxBooksAllowed;
    private MemberStatusEnum statusMem;
    private List<String> borrowedBook;

    public Member(String id, String userName, String password, MemberStatusEnum statusMem) {
        super(id, userName, password);
        this.statusMem = statusMem;
        this.inbox = "";
        this.maxBooksAllowed = statusMem.getMaxBookAllowed();
        this.borrowedBook = new ArrayList<>();
    }
    //getter

    public String getInbox() {
        return inbox;
    }

    public MemberStatusEnum getStatusMember() {
        return statusMem;
    }

    public int getWaitingOrder() {
        return waitingOrder;
    }

    public int getMaxBooksAllowed() {
        return maxBooksAllowed;
    }

    public List<String> getBorrowedBook() {
        return borrowedBook;
    }

    public MemberStatusEnum getStatusMem() {
        return statusMem;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
    public void setStatusMem(MemberStatusEnum statusMem) {
        this.statusMem = statusMem;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //setter
    public void setPassword(String password) {
        this.password = password;
    }

    public void setInbox(String inbox) {
        this.inbox = inbox;
    }

    public void setStatusMember(MemberStatusEnum statusMember) {
        this.statusMem = statusMember;
    }

    public void setWaitingOrder(int waitingOrder) {
        this.waitingOrder = waitingOrder;
    }

    public void setMaxBookBorrowed(int maxBookBorrowed) {
        this.maxBooksAllowed = maxBookBorrowed;
    }

    public void setBorrowedBook(List<String> borrowedBook) {
        this.borrowedBook = borrowedBook;
    }
    
    public boolean canBorrowedMore(){
        return borrowedBook.size() < maxBooksAllowed;
    }
    public void addBorrowedBook(String bookId) {
       if(!borrowedBook.contains(bookId)){
           borrowedBook.add(bookId);
       }
    }

    public void removeBorrowedBook(String bookId) {
        borrowedBook.remove(bookId);
    }
    
    @Override
    public void showInfor() {
        String str = String.format("ID: %s | Tên: %s | Hạng: %s | Đang mượn: %d/%d cuốn",
            id, userName, statusMem, borrowedBook.size(), maxBooksAllowed);
        System.out.println(str);
    }

    @Override
    public void update(String mess) {
        this.inbox = mess;
    }
    @Override
        public String toString() {
            return String.format("%s|%s|%s|%s|%s",
                    id, userName, password, statusMem, String.join(",", borrowedBook));
        }
 
}
