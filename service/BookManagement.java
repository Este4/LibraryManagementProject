/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.Book;
import util.Inputter;
import util.Menu;
public class BookManagement{
    private ArrayList<Book> bookList = new ArrayList<>();
    
    private Book searchById(String id){
         for(Book b : bookList){
             if(b.getBookID().equals(id)){
                 return b;
             }
         }
           return null;
    }
    public void displayBook(){
        for(Book b : bookList){
            b.showInfor();
        }
    }
    public void deleteBook(){
        System.out.println("-----------------DELETING BOOK ---------------------");
        if(bookList.isEmpty()){
            System.out.println("List is empty now");
            System.out.println("Quiting...");
            return;
        }
        String id = Inputter.getAString("Book to delete: ", "This fields cannot be empty!!!");
        Book b = searchById(id);
        if(b == null){
            System.out.println("This Book wasn't existed");
        }
        else{
            System.out.println("Book details:  ");
            b.showInfor();
            System.out.println("Are you sure?");
            Menu choiceMenu = new Menu("", "Input number: ", "Out of the range!!!");
            choiceMenu.addOption("(1) Yes");
            choiceMenu.addOption("(2) No");
            choiceMenu.print();
            int choice = choiceMenu.getChoice();
            switch(choice){
                case 1:{
                    bookList.remove(b);
                    System.out.println("Deleting succesfully...");
                    break;
                }
                case 2:{
                    System.out.println("Canceling....");
                    break;
                }
            }
        }   
    }
    public void updateBook(){
        System.out.println("----------------------Updating book---------------------------");
          if(bookList.isEmpty()){
            System.out.println("List is empty now");
            System.out.println("Quiting...");
            return;
        }
          String id = Inputter.getAString("Book to update: ", "Out of the choice");
          Book b = searchById(id);
          if(b == null){
              System.out.println("This Book wasn't existed");
          }else{
              System.out.println("Book details: ");
              b.showInfor();
            
                 String newtitle = Inputter.getAString("Title book to update: ");
                 if(!newtitle.isEmpty()) b.setTitle(newtitle);
                 
                 String newAuthor = Inputter.getAString("Author book to update: ");
                 if(!newAuthor.isEmpty()) b.setAuthor(newAuthor);
                 
                // int newYearPublication = Inputter.getAnInteger("New year Publication to update", "Out of the choice", 0, Integer.MAX_VALUE);
                 String newGenre = Inputter.getAString("New genre to update: ");
                 if(!newGenre.isEmpty()) b.setGenre(newGenre);
                 
               //  int newTotalQuantity = Inputter.getAnInteger("New total Quantity to update: ", "Out of the choice", 1, Integer.MAX_VALUE);
               //  int NewavailableQuantity = Inputter.getAnInteger("New available Quantity to update: ", "Out of the choice", 1, Integer.MAX_VALUE);
                 
             }
              
    }    
}
