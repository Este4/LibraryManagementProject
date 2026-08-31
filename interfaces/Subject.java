/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import model.Member;

/**
 *
 * @author ledan
 */
public interface Subject {
    void attach(Member member);
    void detach(Member member);
    void notifyObserver();
}
