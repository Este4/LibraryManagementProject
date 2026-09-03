package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import dataStructure.MyStack;
import model.BorrowAction;
import model.EditBook;
import model.Member;
import model.TitleBook;
import enums.ActionTypeEnum;

public class BorrowedRecord {
    private MyStack<BorrowAction> undoStack;
    private MyStack<BorrowAction> redoStack;
    private List<BorrowAction> history;

    public BorrowedRecord() {
        this.undoStack = new MyStack<>();
        this.redoStack = new MyStack<>();
        this.history = new ArrayList<>();
    }

    public boolean borrowBook(Member member, TitleBook titleBook, EditBook book) {
        // đã mượn sách này chưa
        if(member.getBorrowedBook().contains(book.getEditId())){
            System.out.println("Bạn đã mượn cuốn này ròi!");
            return false;
        }
        // có thể mượn thêm đươcj không
        if(!member.canBorrowedMore()){
            System.out.println(">>Đã đạt đến giới hạn mượn, không thể mượn thêm được!");
            return false;
        }
        // còn hàng không
        if(book.getAvailabeQuantity() <= 0){
            titleBook.attach(member);
            System.out.println("bạn đã vào hành chờ");
            return false;
        }
        //neu muon duoc
        int currentQty = book.getAvailabeQuantity();
        book.setAvailabeQuantity(currentQty - 1);
        member.addBorrowedBook(book.getEditId());
        BorrowAction action =  new BorrowAction
        (       member,                     // ai
                book,                       // sách gì
                ActionTypeEnum.BORROW,      // hành động
                LocalDateTime.now().toString());// thời gian 
        undoStack.push(action);
        history.add(action);
        redoStack = new MyStack<>();
        System.out.println("Mượn sách thành công!!");
        return true;
    }

    public boolean returnBook(Member member, TitleBook titleBook, EditBook book) {
        // TODO: ngược lại với borrowBook:
        //       - book.setAvailabeQuantity(số lượng + 1)
        //       - member.removeBorrowedBook(book.getEditId())
        //       - tạo BorrowAction với type = RETURN, push vào undoStack, add vào history
        //       - xóa redoStack
        //       - GỌI titleBook.notifyObservers() nếu số lượng vừa tăng từ 0 lên 1
        //         (đây chính là chỗ Observer pattern kích hoạt!)
        return false;
    }

    public boolean undo() {
        // TODO: 1. nếu undoStack.isEmpty() → in "Không có gì để undo", return false
        //       2. BorrowAction action = undoStack.pop()
        //       3. LÀM NGƯỢC LẠI hành động đó (xem giải thích bên dưới)
        //       4. redoStack.push(action)
        //       5. return true
        return false;
    }

    public boolean redo() {
        // TODO: 1. nếu redoStack.isEmpty() → return false
        //       2. BorrowAction action = redoStack.pop()
        //       3. LÀM LẠI hành động đó (giống như lúc đầu)
        //       4. undoStack.push(action)
        //       5. return true
        return false;
    }

    public void readBorrowHistory() {
        // TODO: duyệt qua `history`, in ra action.toDisplayString() cho từng cái
    }
}
