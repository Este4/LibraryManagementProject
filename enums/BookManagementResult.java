/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

public enum BookManagementResult {
    SUCCESS("Thao tac thanh cong!"),
    DUPLICATE_TITLE_ID("Ma dau sach da ton tai!"),
    DUPLICATE_EDIT_ID("Ma an ban da ton tai!"),
    TITLE_NOT_FOUND("Khong tim thay dau sach!"),
    EDIT_NOT_FOUND("Khong tim thay an ban!"),
    INVALID_QUANTITY("So luong khong hop le!");

    private final String message;

    BookManagementResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
