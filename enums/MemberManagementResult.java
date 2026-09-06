/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author ledan
 */

public enum MemberManagementResult {
    SUCCESS("Thao tac thanh cong!"),
    DUPLICATE_ID("ID da ton tai!"),
    DUPLICATE_USERNAME("Username da ton tai!"),
    NOT_FOUND("Khong tim thay member!"),
    HAS_BORROWED_BOOKS("Khong the xoa, member dang muon sach!");

    private final String message;

    MemberManagementResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
