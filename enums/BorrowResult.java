/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

public enum BorrowResult {
    SUCCESS("Muon sach thanh cong!"),
    ALREADY_BORROWED("Ban da muon cuon nay roi!"),
    LIMIT_REACHED("Da dat gioi han muon sach!"),
    OUT_OF_STOCK_QUEUED("Het sach, ban da duoc them vao hang cho!"),
    TRY_ANOTHER_EDITION("An ban nay het, con an ban khac cua dau sach nay!");

    private final String message;

    BorrowResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
