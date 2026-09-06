/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

public enum BorrowUndoResult {
    SUCCESS("Undo thanh cong!"),
    NOTHING_TO_UNDO("Khong co thao tac nao de undo!"),
    CAN_NOT_UNDO("Khong the undo, sach da het hang!");

    private final String message;

    BorrowUndoResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
