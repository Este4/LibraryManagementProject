package model;

import java.time.LocalDate;

public class EditBook {
    private String editId;
    private int availabeQuantity;
    private LocalDate publishYear;

    public EditBook(String editId, int availabeQuantity, LocalDate publishYear) {
        this.editId = editId;
        this.availabeQuantity = availabeQuantity;
        this.publishYear = publishYear;

    }

    @Override
    public String toString() {
        return "";
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