package model;

import java.time.LocalDate;

public class EditBook {
    private String editId;
    private String masterId;
    private int availabeQuantity;
    private LocalDate publishYear;

    public EditBook(String editId, String masterId, int availabeQuantity, LocalDate publishYear) {
        this.editId = editId;
        this.masterId = masterId;
        this.availabeQuantity = availabeQuantity;
        this.publishYear = publishYear;

    }

    @Override
    public String toString() {
       String str = String.format("%s|%s|%d|%s", editId, masterId, availabeQuantity, publishYear);
       return str;
    }
    public void showInfor() {
        System.out.println(String.format("Mã ấn bản: %s | Số lượng còn: %d | Năm XB: %s",
                editId, availabeQuantity, publishYear));
    }

    public String getMasterId(){
        return masterId;
    }
    public String getEditId() {
        return editId; }
    public int getAvailabeQuantity() {
        return availabeQuantity; }
    public void setAvailabeQuantity(int availabeQuantity) {
        this.availabeQuantity = availabeQuantity; }
    public LocalDate getPublishYear() {
        return publishYear; }
}