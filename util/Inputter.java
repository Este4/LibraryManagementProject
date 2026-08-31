package util;
//Inputter: dùng như 1 cái hòm chứa các method hỗ trợ nhập giá trị
//  nên là các method trong này phải static

import java.util.Scanner;

// static: tĩnh => thuộc về Class => xài method k thông qua tạo object
public class Inputter {
    private static Scanner sc = new Scanner(System.in);
    //method giúp nhập số nguyên bất kỳ
    
    public static int getAnInteger(String inputMsg, String errMsg){
        System.out.println(inputMsg);
        int number;
        while(true){
            try{
                number = Integer.parseInt(sc.nextLine().trim());
                return number;
            }catch(NumberFormatException e){
                System.out.println(errMsg);
            }
        }
    }
    //nhap so nguyen cho phep enter
    public static int getAnIntegerUpdate(String inputMsg, String errMsg, int lowerBound, int upperBound){
       while(true){
            System.out.println(inputMsg);
            String input = sc.nextLine().trim();
            if(input.isEmpty()) return -1;


                 int number;
                try{
                    number = Integer.parseInt(input);
                    if(number < lowerBound || number > upperBound){
                        System.out.println(errMsg);
                        continue;
                    }
                    return number;
                }catch(NumberFormatException e){
                    System.out.println(errMsg);
                }
        }
    }
    //nhập số nguyên từ lowerBound -> upperBound
    //overload
    public static int getAnInteger(String inputMsg, String errMsg,
                                    int lowerBound, int upperBound){
        System.out.println(inputMsg);
        
        while(true){
            int number;
            try{
                //nhập số
                number = Integer.parseInt(sc.nextLine().trim());
                // điều kiện này chặn số nhỏ hơn lowerBound với lớn hơn upperBound
                
                if(number < lowerBound || number > upperBound){
                    // true thì sai
                    System.out.println(errMsg);
                    continue;
                }
                //false thì đúng và trả về số đó
                return number;
            }catch(NumberFormatException e){
                System.out.println(errMsg);
            }
        }
    }
    
    //nhập double
    public static double getADouble(String inputMsg, String errMsg){
        System.out.println(inputMsg);
        double number;
        while(true){
            try{
                number = Double.parseDouble(sc.nextLine());
                return number;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    //nhập số nguyên từ lowerBound -> upperBound
    //overload
    public static double getADouble(String inputMsg, String errMsg,
                                    double lowerBound, double upperBound){
        System.out.println(inputMsg);
        double number;
        while(true){
            try{
                number = Double.parseDouble(sc.nextLine());
                if(number < lowerBound || number > upperBound){
                    throw new Exception();
                }
                return number;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    //nhập chuỗi: không được bỏ trống
    public static String getAString(String inputMsg, String errMsg){
        System.out.println(inputMsg);
        String str;
        while(true){
            try{
                str = sc.nextLine().trim();
                if(str.isEmpty() || str.equals("") || str.length() == 0){
                    throw new Exception();
                }
                return str;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    
    //nhập chuỗi: không được bỏ trống và ;hải theo regex
    public static String getAString(String inputMsg, String errMsg, String regex){
        System.out.println(inputMsg);
        String str;
        while(true){
            try{
                str = sc.nextLine().trim();
                if(str.isEmpty() || str.equals("") || 
                   str.length() == 0 || !str.matches(regex)){
                    throw new Exception();
                }
                return str;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    // nhập chuỗi: được phép bỏ trống 
    public static String getAString(String inputMsg){
        System.out.println(inputMsg);
        String str = sc.nextLine().trim();
        if(str.isEmpty()){
            return "";
        }
        return str;
    }   
    //
}

//implement thêm: read file
//          thêm: nhập date theo format