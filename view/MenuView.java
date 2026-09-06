package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.*;
import enums.*;
import service.*;
import util.Inputter;
import util.Menu;
import util.Regex;

public class MenuView {
    private BookManagement bookMgr = new BookManagement();
    private MemberManagement memberMgr = new MemberManagement();
    private AdminManagement adminMgr = new AdminManagement();
    private BorrowedRecord borrowRecord = new BorrowedRecord();

    public void start() {
        Menu<String> main = new Menu<>("THU VIEN", "Chon chuc nang: ", "Vui long chon ");
        main.addOption("Dang nhap Member");
        main.addOption("Dang nhap Admin");
        main.addOption("Thoat");

        while (true) {
            main.print();
            switch (main.getChoice()) {
                case 1:{
                    loginMember();
                    break;
                }
                case 2:{
                    loginAdmin();
                    break;
                }
                case 3:{
                    System.out.println("Tam biet!");
                    return;
                }
            }
        }
    }

    // ==================== MEMBER ====================
    private void loginMember() {
        String u = Inputter.getAString("Username: ", "Khong duoc trong!");
        String p = Inputter.getAString("Password: ", "Khong duoc trong!");
        Member m = memberMgr.login(u, p);
        if (m == null) { System.out.println(">> Sai thong tin dang nhap!"); return; }

        System.out.println(">> Xin chao " + m.getUserName());
        if (m.getInbox() != null && !m.getInbox().isEmpty()) {
            System.out.println("[THONG BAO] " + m.getInbox());
        }
        memberMenu(m);
    }

    private void memberMenu(Member m) {
        Menu<String> menu = new Menu<>("MENU MEMBER", "Chon: ", "Vui long chon ");
        menu.addOption("Xem tat ca sach");
        menu.addOption("Tim sach");
        menu.addOption("Muon sach");
        menu.addOption("Tra sach");
        menu.addOption("Xem sach dang muon");
        menu.addOption("Undo");
        menu.addOption("Redo");
        menu.addOption("Lich su muon/tra");
        menu.addOption("Thong tin ca nhan");
        menu.addOption("Dang xuat");

         while (true) {
            menu.print();
            switch (menu.getChoice()) {
                case 1: {
                    showAllBooks();
                    break;
                }
                case 2: {
                    searchBook();
                    break;
                }
                case 3: {
                    doBorrow(m);
                    break;
                }
                case 4: {
                    doReturn(m);
                    break;
                }
                case 5: {
                    System.out.println("Dang muon: " + m.getBorrowedBook());
                    break;
                }
                case 6: {
                    System.out.println(">> " + borrowRecord.undo().getMessage());
                    break;
                }
                case 7: {
                    System.out.println(">> " + borrowRecord.redo().getMessage());
                    break;
                }
                case 8: {
                    showHistory();
                    break;
                }
                case 9: {
                    m.showInfor();
                    break;
                }
                case 10: {
                    m.logout();
                    return;
                }
            }
        }
    }

   private void doBorrow(Member m) {
    if (bookMgr.eList.isEmpty()) {
        System.out.println(">> Chua co sach nao!");
        return;
    }

    System.out.println("--- CHON AN BAN MUON ---");
    int seq = 1;
    for (EditBook e : bookMgr.eList) {
        TitleBook t = bookMgr.searchTitleByid(e.getMasterId());
        String tenSach = (t != null) ? t.getTitle() : "???";
        String tacGia = (t != null) ? t.getAuthor() : "???";
        System.out.println(seq++ + ". " + tenSach + " - " + tacGia
                + " | Ma an ban: " + e.getEditId()
                + " | Nam XB: " + e.getPublishYear()
                + " | Con lai: " + e.getAvailabeQuantity());
    }

    int choice = Inputter.getAnInteger("Chon sach: ", "Vui long chon tu 1 den "
            + bookMgr.eList.size(), 1, bookMgr.eList.size());
    EditBook book = bookMgr.eList.get(choice - 1);

    TitleBook tb = bookMgr.searchTitleByid(book.getMasterId());
    System.out.println(">> " + borrowRecord.borrowBook(m, tb, book).getMessage());

    bookMgr.saveAll();
    memberMgr.saveAll();
}

   private void doReturn(Member m) {
    if (m.getBorrowedBook().isEmpty()) {
        System.out.println(">> Ban khong muon sach nao!");
        return;
    }
    ArrayList<EditBook> borrowed = new ArrayList<>();
    for (String eid : m.getBorrowedBook()) {
        EditBook e = bookMgr.searchBookByid(eid);
        if (e != null) {
            borrowed.add(e);
        }
    }
    if (borrowed.isEmpty()) {
        System.out.println(">> Khong tim thay sach dang muon!");
        return;
    }

    System.out.println("--- CHON SACH MUON TRA ---");
    int seq = 1;
    for (EditBook e : borrowed) {
        TitleBook t = bookMgr.searchTitleByid(e.getMasterId());
        String tenSach = (t != null) ? t.getTitle() : "???";
        System.out.println(seq++ + ". " + tenSach + " | Ma an ban: " + e.getEditId());
    }

    int choice = Inputter.getAnInteger("Chon: ", "Vui long chon tu 1 den "
            + borrowed.size(), 1, borrowed.size());
    EditBook book = borrowed.get(choice - 1);

    TitleBook tb = bookMgr.searchTitleByid(book.getMasterId());
    System.out.println(">> " + borrowRecord.returnBook(m, tb, book).getMessage());

    bookMgr.saveAll();
    memberMgr.saveAll();
}

    // ==================== ADMIN ====================
    private void loginAdmin() {
        String u = Inputter.getAString("Username: ", "Khong trong!");
        String p = Inputter.getAString("Password: ", "Khong trong!");
        Admin a = adminMgr.login(u, p);
        if (a == null) { System.out.println(">> Sai thong tin!"); return; }
        System.out.println(">> Xin chao Admin " + a.getUserName());
        adminMenu();
    }

    private void adminMenu() {
        Menu<String> menu = new Menu<>("MENU ADMIN", "Chon: ", "Vui long chon ");
        menu.addOption("Xem tat ca sach");
        menu.addOption("Them dau sach");
        menu.addOption("Them an ban (tiep te)");
        menu.addOption("Cap nhat so luong");
        menu.addOption("Xoa an ban");
        menu.addOption("Sap xep theo ten sach");
        menu.addOption("Sap xep theo tac gia");
        menu.addOption("Sap xep theo nam XB");
        menu.addOption("Thong ke");
        menu.addOption("Quan ly Member");
        menu.addOption("Dang xuat");

        while (true) {
            menu.print();
            switch (menu.getChoice()) {
                case 1: {
                    showAllBooks();
                    break;
                }
                case 2: {
                    addTitle();
                    break;
                }
                case 3: {
                    addEdition();
                    break;
                }
                case 4: {
                    updateQty();
                    break;
                }
                case 5: {
                    deleteEdition();
                    break;
                }
                case 6: {
                    printTitles(bookMgr.sortByTitle());
                    break;
                }
                case 7: {
                    printTitles(bookMgr.sortByAuthor());
                    break;
                }
                case 8: {
                    for (EditBook e : bookMgr.sortByPublicationYear()) {
                        e.showInfor();
                    }
                    break;
                }
                case 9: {
                    showStats();
                    break;
                }
                case 10: {
                    memberManageMenu();
                    break;
                }
                case 11: {
                    return;
                }
            }
        }
    }

    private void addTitle() {
        String id;
        while (true) {
            id = Inputter.getAString("Ma dau sach (VD: T001): ",
                    "Sai dinh dang! Phai la chu T + 3 so.", Regex.TITLE_ID_REGEX);
            if (bookMgr.searchTitleByid(id) == null) {
                break;
            }
            System.out.println(">> Ma dau sach nay da ton tai, vui long nhap ma khac!");
        }
        String t = Inputter.getAString("Ten sach: ", "Khong duoc trong!");
        String au = Inputter.getAString("Tac gia: ",
                "Ten khong hop le!", Regex.NAME_REGEX);
        String g = Inputter.getAString("The loai: ", "Khong duoc trong!");
        System.out.println(">> " + bookMgr.createTitle(id, t, au, g).getMessage());
    }

        private void addEdition() {
        if (bookMgr.tList.isEmpty()) {
            System.out.println(">> Chua co dau sach nao!");
            return;
        }
        Menu<TitleBook> tMenu = new Menu<>("CHON DAU SACH", "Chon: ", "Vui long chon ");
        TitleBook tb = tMenu.ref_getChoice(bookMgr.tList);
        String eId;
        while (true) {
            eId = Inputter.getAString("Ma an ban (VD: E001): ",
                    "Sai dinh dang! Phai la chu E + 3 so.", Regex.EDIT_ID_REGEX);
            if (bookMgr.searchBookByid(eId) == null) {
                break;
            }
            System.out.println(">> Ma an ban nay da ton tai, vui long nhap ma khac!");
        }
        int qty = Inputter.getAnInteger("So luong: ", "Sai!", 0, 10000);

        LocalDate publishYear = null;
        while (publishYear == null) {
            int y = Inputter.getAnInteger("Nam XB: ", "Sai!", 1000, 2100);
            int mo = Inputter.getAnInteger("Thang: ", "Sai!", 1, 12);
            int d = Inputter.getAnInteger("Ngay: ", "Sai!", 1, 31);
            try {
                publishYear = LocalDate.of(y, mo, d);
                if (publishYear.isAfter(LocalDate.now())) {
                    System.out.println(">> Ngay xuat ban khong the o tuong lai!");
                    publishYear = null;
                }
            } catch (Exception e) {
                System.out.println(">> Ngay khong ton tai! Vui long nhap lai.");
            }
        }
        System.out.println(">> " + bookMgr.addEdition(tb.getMasterId(), eId, qty, publishYear).getMessage());
    }

    private void updateQty() {
        if (bookMgr.eList.isEmpty()) { System.out.println(">> Chua co an ban nao!"); return; }
        Menu<EditBook> menu = new Menu<>("CHON AN BAN CAP NHAT", "Chon: ", "Vui long chon ");
        EditBook e = menu.ref_getChoice(bookMgr.eList);
        int q = Inputter.getAnInteger("So luong moi: ", "Sai!", 0, 10000);
        System.out.println(">> " + bookMgr.updateQuantity(e.getEditId(), q).getMessage());
    }

    private void deleteEdition() {
        if (bookMgr.eList.isEmpty()) { System.out.println(">> Chua co an ban nao!"); return; }
        Menu<EditBook> menu = new Menu<>("CHON AN BAN XOA", "Chon: ", "Vui long chon ");
        EditBook e = menu.ref_getChoice(bookMgr.eList);

        if (memberMgr.isBookBorrowedByAnyone(e.getEditId())) {
            System.out.println(">> Khong xoa duoc, sach dang co nguoi muon!");
            return;
        }
        System.out.println(">> " + bookMgr.deleteEdition(e.getEditId()).getMessage());
    }

     private void memberManageMenu() {
        Menu<String> menu = new Menu<>("QUAN LY MEMBER", "Chon: ", "Vui long chon ");
        menu.addOption("Xem tat ca");
        menu.addOption("Them member");
        menu.addOption("Xoa member");
        menu.addOption("Tim member");
        menu.addOption("Quay lai");

        while (true) {
            menu.print();
            switch (menu.getChoice()) {
                case 1: {
                    for (Member m : memberMgr.readAll()) {
                        m.showInfor();
                    }
                    break;
                }
                case 2: {
                    addMember();
                    break;
                }
                case 3: {
                    deleteMember();
                    break;
                }
                case 4: {
                    String k = Inputter.getAString("Tu khoa: ", "Khong trong!");
                    for (Member m : memberMgr.searchByKeyword(k)) {
                        m.showInfor();
                    }
                    break;
                }
                case 5: {
                    return;
                }
            }
        }
    }

    private void addMember() {
        String id;
        while (true) {
            id = Inputter.getAString("ID (VD: M001): ",
                    "Sai dinh dang! Phai la chu M + 3 so.", Regex.MEMBER_ID_REGEX);
            if (memberMgr.searchById(id) == null) {
                break;
            }
            System.out.println(">> ID nay da ton tai, vui long nhap ID khac!");
        }
        String u;
        while (true) {
            u = Inputter.getAString("Username (3-20 ky tu): ",
                    "Username khong hop le!", Regex.USERNAME_REGEX);
            if (memberMgr.searchByUserName(u) == null) {
                break;
            }
            System.out.println(">> Username nay da ton tai, vui long nhap ten khac!");
        }
        String p = Inputter.getAString("Password (toi thieu 6 ky tu): ",
                "Password qua ngan!", Regex.PASSWORD_REGEX);

        Menu<String> sMenu = new Menu<>("CHON HANG", "Chon: ", "Vui long chon ");
        sMenu.addOption("VIP");
        sMenu.addOption("REGULAR");
        sMenu.print();

        MemberStatusEnum st;
        if (sMenu.getChoice() == 1) {
            st = MemberStatusEnum.VIP;
        } else {
            st = MemberStatusEnum.REGULAR;
        }
        System.out.println(">> " + memberMgr.createMember(id, u, p, st).getMessage());
    }

    private void deleteMember() {
        if (memberMgr.mList.isEmpty()) { System.out.println(">> Chua co member nao!"); return; }
        Menu<Member> menu = new Menu<>("CHON MEMBER XOA", "Chon: ", "Vui long chon ");
        Member m = menu.ref_getChoice(memberMgr.mList);
        System.out.println(">> " + memberMgr.deleteMember(m.getId()).getMessage());
    }

    // ==================== HIEN THI ====================
    private void showAllBooks() {
        for (TitleBook tb : bookMgr.readAllTitles()) {
            System.out.println("\n[" + tb.getMasterId() + "] " + tb.getTitle()
                    + " - " + tb.getAuthor() + " (" + tb.getGenre() + ")"
                    + " | Tong con: " + tb.getTotalAvailableQuantity());
            for (EditBook e : tb.getEditions()) {
                System.out.println("    + " + e.getEditId() + " | SL: "
                        + e.getAvailabeQuantity() + " | " + e.getPublishYear());
            }
        }
    }

    private void printTitles(List<TitleBook> list) {
        for (TitleBook tb : list) {
            System.out.println(tb.getMasterId() + " | " + tb.getTitle() + " | " + tb.getAuthor());
        }
    }

    private void searchBook() {
    String k = Inputter.getAString("Tu khoa: ", "Khong trong!");
    List<TitleBook> r = bookMgr.searchByKeyword(k);
    if (r.isEmpty()) {
        System.out.println(">> Khong tim thay!");
        return;
    }
    for (TitleBook tb : r) {
        System.out.println("\n[" + tb.getMasterId() + "] " + tb.getTitle()
                + " - " + tb.getAuthor() + " (" + tb.getGenre() + ")"
                + " | Tong con: " + tb.getTotalAvailableQuantity());
        for (EditBook e : tb.getEditions()) {
            System.out.println("    + Ma an ban: " + e.getEditId()
                    + " | SL: " + e.getAvailabeQuantity()
                    + " | Nam XB: " + e.getPublishYear());
        }
    }
}

    private void showHistory() {
        List<String> h = borrowRecord.readBorrowHistory();
        if (h.isEmpty()) { System.out.println(">> Chua co lich su!"); return; }
        for (String s : h) System.out.println(s);
    }

    private void showStats() {
        System.out.println("Tong so sach trong kho: " + bookMgr.getTotalBooks());
        System.out.println("Tong so dau sach: " + bookMgr.readAllTitles().size());
        System.out.println("--- Dau sach het hang ---");
        for (TitleBook tb : bookMgr.getOutOfStockTitles()) {
            System.out.println("  " + tb.getMasterId() + " - " + tb.getTitle());
        }
    }
}
