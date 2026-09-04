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
import enums.BorrowResult;
import enums.BorrowReturnResult;

public class BorrowedRecord {
    private MyStack<BorrowAction> undoStack;
    private MyStack<BorrowAction> redoStack;
    private List<BorrowAction> history;

    public BorrowedRecord() {
        this.undoStack = new MyStack<>();
        this.redoStack = new MyStack<>();
        this.history = new ArrayList<>();
    }

    public BorrowResult borrowBook(Member member, TitleBook titleBook, EditBook book) {
        // đã mượn sách này chưa
        if(member.getBorrowedBook().contains(book.getEditId())){
            return BorrowResult.ALREADY_BORROWED;
        }
        // có thể mượn thêm đươcj không
        if(!member.canBorrowedMore()){
            return BorrowResult.LIMIT_REACHED;
        }
        // còn hàng không
        if(book.getAvailabeQuantity() <= 0){
            titleBook.attach(member);
            return BorrowResult.OUT_OF_STOCK_QUEUED;
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
        return BorrowResult.SUCCESS;
    }

    public BorrowReturnResult returnBook(Member member, TitleBook titleBook, EditBook book) {
        if(!member.getBorrowedBook().contains(book.getEditId())){
            return BorrowReturnResult.NOT_BORROWED;
        }
        int currentQty = book.getAvailabeQuantity();
        book.setAvailabeQuantity(currentQty + 1);
        member.removeBorrowedBook(book.getEditId());
        BorrowAction action = new BorrowAction(
                member,
                book,
                ActionTypeEnum.RETURN,
                LocalDateTime.now().toString());
        undoStack.push(action);
        history.add(action);
        redoStack = new MyStack<>();
        if(currentQty == 0){
         titleBook.notifyObserver();
        }
        return BorrowReturnResult.SUCCESS;
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
