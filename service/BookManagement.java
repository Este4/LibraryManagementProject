/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import model.TitleBook;
import model.EditBook;
import util.Inputter;
import util.Menu;
import util.FileHelper;
import enums.BookManagementResult;
public class BookManagement{
   public ArrayList<TitleBook> tList = new ArrayList<>();
   public ArrayList<EditBook> eList = new ArrayList<>();
   private FileHelper<EditBook> FileEditBook = new FileHelper<EditBook>(eList) {
       @Override
       public EditBook handleLine(String line) {
           try{
           StringTokenizer st = new StringTokenizer(line, "|");
           String eId = st.nextToken().trim();
           String tId = st.nextToken().trim();
           int eQty = Integer.parseInt(st.nextToken().trim());
           LocalDate publishYear = LocalDate.parse(st.nextToken().trim());
           return new EditBook(eId, tId, eQty, publishYear);
           }catch(Exception e){
               System.out.println("  [!] Skip invalid book line: " + line);
                return null;
           }
       }
   };
   private FileHelper<TitleBook> FileTitleBook = new FileHelper<TitleBook>(tList) {
       @Override
       public TitleBook handleLine(String line) {
           try{
           StringTokenizer st = new StringTokenizer(line, "|");
           String tId = st.nextToken().trim();
           String tName = st.nextToken().trim();
           String tAuthor = st.nextToken().trim();
           String tGenre = st.nextToken().trim();
           return new TitleBook(tId, tName, tAuthor, tGenre);
           }catch(Exception e){
               System.out.println("  [!] Skip invalid book line: " + line);
                return null;
           }
       }
   };
   public EditBook searchBookByid(String eId){
       for(EditBook e : eList){
           if(e.getEditId().contains(eId)){
               return e;
           }
       }
       return null;
   }
   public TitleBook searchTitleByid(String tId){
       for(TitleBook t : tList){
           if(t.getMasterId().equals(tId)){
               return t;
           }
       }
       return null;
   }
    public List<TitleBook> searchByKeyword(String keyword) {
        List<TitleBook> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (TitleBook tb : tList) {
            if (tb.getTitle().toLowerCase().contains(lowerKeyword)
                || tb.getAuthor().toLowerCase().contains(lowerKeyword)) {
                result.add(tb);
            }
        }
        return result;
    }
    public BookManagementResult createTitle(String masterId, String title, String author, String genre) {
        if (searchTitleByid(masterId) != null) {
           return BookManagementResult.DUPLICATE_TITLE_ID;
        }
        tList.add(new TitleBook(masterId, title, author, genre));
        saveAll();
        return BookManagementResult.SUCCESS;
    }
    public void loadAll(){
           String url = "EditBook.txt";
           String url1 = "TitleBook.txt";
           FileEditBook.loadFromFile(url);
           FileTitleBook.loadFromFile(url1);
            for(EditBook e : eList){
               TitleBook tb = searchTitleByid(e.getMasterId());
               if (tb != null) {
               tb.addEdition(e);
               }
            }
    }
    public void saveAll(){
        FileEditBook.saveToFile("EditBook.txt");
        FileTitleBook.saveToFile("TitleBook.txt");
    }
    public BookManagement(){
        loadAll();
    }
    public BookManagementResult addEdition(String masterId, String editId, int qty, LocalDate publishYear) {
    TitleBook tb = searchTitleByid(masterId);
    if (tb == null) return BookManagementResult.TITLE_NOT_FOUND;
    if (searchBookByid(editId) != null) return BookManagementResult.DUPLICATE_EDIT_ID;
    if (qty < 0) return BookManagementResult.INVALID_QUANTITY;

    int before = tb.getTotalAvailableQuantity();
    EditBook newEdition = new EditBook(editId, masterId, qty, publishYear);
    tb.addEdition(newEdition);
    eList.add(newEdition);

    if (before == 0 && qty > 0) {
        tb.notifyObserver();
    }
    saveAll();
    return BookManagementResult.SUCCESS;
    }
    public BookManagementResult updateQuantity(String editId, int newQty) {
    EditBook e = searchBookByid(editId);
    if (e == null) return BookManagementResult.EDIT_NOT_FOUND;
    if (newQty < 0) return BookManagementResult.INVALID_QUANTITY;
    TitleBook tb = searchTitleByid(e.getMasterId());
        int before = (tb != null) ? tb.getTotalAvailableQuantity() : 0;

        e.setAvailabeQuantity(newQty);

        if (tb != null && before == 0 && tb.getTotalAvailableQuantity() > 0) {
            tb.notifyObserver();
        }
        saveAll();
    return BookManagementResult.SUCCESS;
    }
    public BookManagementResult deleteEdition(String editId) {
    EditBook e = searchBookByid(editId);
    if (e == null) return BookManagementResult.EDIT_NOT_FOUND;
    TitleBook tb = searchTitleByid(e.getMasterId());
    if(tb != null){
        tb.getEditions().remove(e);
    }
    eList.remove(e);
    saveAll();
    return BookManagementResult.SUCCESS;
}  
    public List<TitleBook> readAllTitles() {
        return tList;
    }

    public List<EditBook> readAllEditions() {
        return eList;
    }

    public List<EditBook> getEditionsOfTitle(String masterId) {
        TitleBook tb = searchTitleByid(masterId);
        if (tb == null) return new ArrayList<>();
        return tb.getEditions();
    }

    public BookManagementResult updateTitle(String masterId, String newTitle,
                                            String newAuthor, String newGenre) {
        TitleBook tb = searchTitleByid(masterId);
        if (tb == null) return BookManagementResult.TITLE_NOT_FOUND;

        if (newTitle != null && !newTitle.isEmpty()) {
            tb.setTitle(newTitle);
        }
        if (newAuthor != null && !newAuthor.isEmpty()) {
            tb.setAuthor(newAuthor);
        }
        if (newGenre != null && !newGenre.isEmpty()) {
            tb.setGenre(newGenre);
        }
        saveAll();
        return BookManagementResult.SUCCESS;
    }

    public BookManagementResult deleteTitle(String masterId) {
        TitleBook tb = searchTitleByid(masterId);
        if (tb == null) return BookManagementResult.TITLE_NOT_FOUND;
        tList.remove(tb);
        saveAll();
        return BookManagementResult.SUCCESS;
    }

    public List<TitleBook> searchByGenre(String genre) {
        List<TitleBook> result = new ArrayList<>();
        for (TitleBook tb : tList) {
            if (tb.getGenre().equalsIgnoreCase(genre)) {
                result.add(tb);
            }
        }
        return result;
    }

    public int getTotalBooks() {
        int total = 0;
        for (EditBook e : eList) {
            total += e.getAvailabeQuantity();
        }
        return total;
    }

    public List<TitleBook> getOutOfStockTitles() {
        List<TitleBook> result = new ArrayList<>();
        for (TitleBook tb : tList) {
            if (tb.getTotalAvailableQuantity() == 0) {
                result.add(tb);
            }
        }
        return result;
    }

    public List<TitleBook> getAvailableTitles() {
        List<TitleBook> result = new ArrayList<>();
        for (TitleBook tb : tList) {
            if (tb.getTotalAvailableQuantity() > 0) {
                result.add(tb);
            }
        }
        return result;
    }
    public List<TitleBook> sortByTitle() {
        List<TitleBook> sorted = new ArrayList<>(tList);
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - 1 - i; j++) {
                if (sorted.get(j).getTitle().compareToIgnoreCase(sorted.get(j + 1).getTitle()) > 0) {
                    TitleBook temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        return sorted;
    }
    public List<TitleBook> sortByAuthor() {
        List<TitleBook> sorted = new ArrayList<>(tList);
        for (int i = 0; i < sorted.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < sorted.size(); j++) {
                if (sorted.get(j).getAuthor().compareToIgnoreCase(sorted.get(minIdx).getAuthor()) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                TitleBook temp = sorted.get(i);
                sorted.set(i, sorted.get(minIdx));
                sorted.set(minIdx, temp);
            }
        }
        return sorted;
    }
    public List<EditBook> sortByPublicationYear() {
        List<EditBook> sorted = new ArrayList<>(eList);
        for (int i = 1; i < sorted.size(); i++) {
            EditBook key = sorted.get(i);
            int j = i - 1;
            while (j >= 0 && sorted.get(j).getPublishYear().isAfter(key.getPublishYear())) {
                sorted.set(j + 1, sorted.get(j));
                j--;
            }
            sorted.set(j + 1, key);
        }
        return sorted;
    }
}
