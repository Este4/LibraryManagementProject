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
import enums.BorrowUndoResult;
import enums.BorrowRedoResult;

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

    public BorrowUndoResult undo() {
        if (undoStack.isEmpty()){
            return BorrowUndoResult.NOTHING_TO_UNDO;
        }
        BorrowAction action = undoStack.pop();
        Member m = action.getMember();
        EditBook b = action.getBook();
       
        if(action.getType() == ActionTypeEnum.BORROW){
             int currentQty = b.getAvailabeQuantity();
            b.setAvailabeQuantity(currentQty + 1);
            m.removeBorrowedBook(b.getEditId());
        } else{
            int currentQty = b.getAvailabeQuantity();
             if (currentQty <= 0) {
                undoStack.push(action);  
                return BorrowUndoResult.CAN_NOT_UNDO;
            }
            b.setAvailabeQuantity(currentQty - 1);
            m.addBorrowedBook(b.getEditId());
        }
       redoStack.push(action);
       return BorrowUndoResult.SUCCESS;
    }

    public BorrowRedoResult redo() {
        if(redoStack.isEmpty()){
            return BorrowRedoResult.NOTHING_TO_REDO;
        }
        BorrowAction action = redoStack.pop();
        Member m = action.getMember();
        EditBook b = action.getBook();
        if(action.getType() == ActionTypeEnum.BORROW){
             int currentQty = b.getAvailabeQuantity();
            b.setAvailabeQuantity(currentQty - 1);
            m.addBorrowedBook(b.getEditId());
        }else{
            int currentQty = b.getAvailabeQuantity();
            b.setAvailabeQuantity(currentQty + 1);
            m.removeBorrowedBook(b.getEditId());
        }
        undoStack.push(action);
        return BorrowRedoResult.SUCCESS;
    }

    public List<String> readBorrowHistory() {
        List<String> result = new ArrayList<>();
            for (BorrowAction action : history) {
                result.add(action.showInfor());
            }
        return result;
    }
}
