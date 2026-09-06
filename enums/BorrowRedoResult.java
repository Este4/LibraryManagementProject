/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

public enum BorrowRedoResult {
    SUCCESS("Redo thanh cong!"),
    NOTHING_TO_REDO("Khong co thao tac nao de redo!");

    private final String message;

    BorrowRedoResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
