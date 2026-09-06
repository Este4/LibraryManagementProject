/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

public enum BorrowReturnResult {
    SUCCESS("Tra sach thanh cong!"),
    NOT_BORROWED("Ban khong muon cuon sach nay!");

    private final String message;

    BorrowReturnResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
