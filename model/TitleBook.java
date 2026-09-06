package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import dataStructure.MyPriorityQueues;
import interfaces.Subject;

public class TitleBook implements Subject {
    private String masterId;
    private String title;
    private String author;
    private String genre;
    private List<EditBook> editions;
    private MyPriorityQueues<Member> waitingQueue;
    private int waitingCounter;

    public TitleBook(String masterId, String title, String author, String genre) {
        this.masterId = masterId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.editions = new ArrayList<>();
        this.waitingCounter = 0;

        Comparator<Member> memberPriority = new Comparator<Member>() {
            @Override
            public int compare(Member m1, Member m2) {
                int p1 = m1.getStatusMember().getPriorityLevel();
                int p2 = m2.getStatusMember().getPriorityLevel();
                if(p1 != p2){
                    return p1 - p2;
                }
                return Integer.compare(m1.getWaitingOrder(), m2.getWaitingOrder());
            }
        };
        this.waitingQueue = new MyPriorityQueues<>(memberPriority);
    }

    public String getMasterId() {
        return masterId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author){
        this.author = author;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    @Override
    public void attach(Member member) {
          waitingCounter++;
          member.setWaitingOrder(waitingCounter);
          waitingQueue.enqueue(member);
    }

        
    @Override
    public void detach(Member member) {
        ArrayList<Member> temp = new ArrayList<>();
        while(!waitingQueue.isEmpty()){
            Member m = waitingQueue.dequeue();
            if(!m.getId().equals(member.getId())){
                temp.add(m);
            }
        }
        for(Member m : temp){
            waitingQueue.enqueue(m);
        }
    }
    
    public void addEdition(EditBook edition) {
        editions.add(edition);
    }

    public List<EditBook> getEditions() {
        return editions;
    }
     public int getTotalAvailableQuantity() {
        int total = 0;
        for (EditBook e : editions) {      
            total += e.getAvailabeQuantity();  
        }
        return total;
    }
    
    @Override
    public void notifyObserver() {
     if (waitingQueue.isEmpty()) {
        return;
    }
    Member nextMember = waitingQueue.dequeue();
    nextMember.update("Sách \"" + title + "\" đã có lại, mời bạn đến mượn trong 24h!");
    }
}
