/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.ActionTypeEnum;

public class BorrowAction {
     private Member member;
    private EditBook book;
    private ActionTypeEnum type;
    private String timestamp;

    public BorrowAction(Member member, EditBook book, ActionTypeEnum type, String timestamp) {
        this.member = member;
        this.book = book;
        this.type = type;
        this.timestamp = timestamp;
    }
    public Member getMember() { return member; }
    public EditBook getBook() { return book; }
    public ActionTypeEnum getType() { return type; }
    public String getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s",
                timestamp, member.getId(), book.getEditId(), type);
    }
    public void showInfor(){
        String str = (type == ActionTypeEnum.BORROW) ? "đã mượn" : "đã trả";
        String str1 = String.format("[%s] %s %s sách %s",
                    timestamp, member.getUserName(), str, book.getEditId());
        System.out.println(str1);
    }
    
}
