package util;
//Menu: là cái khuôn dùng để đúc ra các loại menu

import java.util.ArrayList;

public class Menu<T>{
    private ArrayList<String> otpList = new ArrayList<>();
    private String title; //tên menu
    private String inputMsg; //lời mời nhập lựa chọn
    private String errMsg; //lời thông báo lỗi
    // constructor
    public Menu(String title, String inputMsg, String errMsg) {
        this.title = title;
        this.inputMsg = inputMsg;
        this.errMsg = errMsg;
    }
    //addOption
    public void addOption(String otp){
        otpList.add(otp);
    }
    //print
    public void print(){
        System.out.println("--" + title + "--");
        int seq = 1;//sequence: tuần tự stt
        for (String otp : otpList) {
            System.out.println(seq++ + "." + otp);
        }
    }
    //getChoice
    public int getChoice(){
        return Inputter.getAnInteger(inputMsg, 
                                    errMsg + "between 1 and " + otpList.size(),
                                    1, otpList.size());
    }
    
    
    public T ref_getChoice(ArrayList<T> objList){
        //hiển thị danh sách dưới dạng menu
        System.out.println(title);//in tên menu cho đẹp trai
        int seq = 1;
        for (T item : objList) {
            System.out.println(seq++ + ". " + item.toString());
        }
        //xin lựa chọn
        int choice = Inputter.getAnInteger(inputMsg, 
                                    errMsg + "between 1 and " + objList.size(),
                                    1, objList.size());
        return objList.get(choice - 1);
    }
}
