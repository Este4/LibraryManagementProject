/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import enums.MemberStatusEnum;
import java.time.LocalDate;
import model.EditBook;
import model.Member;
import model.TitleBook;
import service.BorrowedRecord;

/**
 *
 * @author ledan
 */
public class ReturnBookTest {
    public static void main(String[] args) {
        // ===== Test 2 (quan trọng nhất) =====
        System.out.println("--- Test 2: Trả sách khi hết hàng, có người chờ ---");
        BorrowedRecord record = new BorrowedRecord();
        
        Member nam = new Member("M001", "Nam", "123", MemberStatusEnum.REGULAR);
        Member m3 = new Member("M003", "VipUser", "123", MemberStatusEnum.VIP);
        Member m1 = new Member("M004", "NormalUser", "123", MemberStatusEnum.REGULAR);
        
        TitleBook tb = new TitleBook("T001", "Harry Potter", "Rowling", "Fantasy");
        EditBook book = new EditBook("E001", "T001", 0, LocalDate.of(2001, 6, 26));
        tb.addEdition(book);
        
        nam.addBorrowedBook("E001");   // giả lập Nam đang giữ sách
        tb.attach(m1);                  // M1 xếp hàng trước
        tb.attach(m3);                  // M3 (VIP) xếp sau nhưng ưu tiên cao hơn
        
        record.returnBook(nam, tb, book);
        
        System.out.println("qty (mong đợi 1): " + book.getAvailabeQuantity());
        System.out.println("M3 inbox (mong đợi có nội dung): " + m3.getInbox());
        System.out.println("M1 inbox (mong đợi RỖNG): " + m1.getInbox());
    }
}