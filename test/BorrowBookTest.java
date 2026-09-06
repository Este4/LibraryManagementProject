///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package test;
//
//import java.time.LocalDate;
//import model.EditBook;
//import model.Member;
//import model.TitleBook;
//import service.BorrowedRecord;
//import enums.MemberStatusEnum;
//public class BorrowBookTest {
//    public static void main(String[] args) {
//        
//    }
//    
//  public TitleBook createTitleBook(int quantity){
//      TitleBook tb = new TitleBook("T001", "Harry Potter", "J.k. Rowling", "fantasty");
//      tb.addEdition(new EditBook("E001", "T001", quantity, LocalDate.of(2021,6,23)));
//      return tb;
//  }
//  public void check(String testname, boolean codition){
//      System.out.println((codition ? "Pass" : "Fail") + testname);
//  }
//  public void testBorrowSuccess(){
//      System.out.println("test1 : muon sach");
//      BorrowedRecord test = new BorrowedRecord();
//      Member m = new Member("M001", "DANG", "123", MemberStatusEnum.REGULAR);
//      TitleBook t1 = createTitleBook(5);
//      EditBook eb = TitleBook.
//  }
//}
