
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public abstract class FileHelper<T>{
    private ArrayList<T> dataList;
    //constructor
    public FileHelper(ArrayList<T> dataList) {
        this.dataList = dataList;
    }
    //loadFromFile
    public boolean loadFromFile(String url){
        File f = new File(url);
        if(!f.exists()){
            System.out.println("File chưa tồn tại, bắt đầu với dữ liệu rỗng: " + url);
                 return true;   
        }
        if(!f.canRead()){
            System.out.println("File không đọc được : " + url);
            return false;  
        }
        //try with resource
        try(FileReader fr = new FileReader(f);
            BufferedReader reader = new BufferedReader(fr);){
            dataList.clear();
            String line = reader.readLine();
            while(line != null){
                //biến line thành object cần lưu
                T t = handleLine(line);
                dataList.add(t);
                line = reader.readLine();
            }
            return true;
        }catch(Exception e){
            System.out.println("LoadFromFile error: " + e);
            return false;
        }
    }
    //nhận vào 1 line và biến thành object để lưu
    public abstract T handleLine(String line);
    //saveToFile
    public boolean saveToFile(String url){
        File f = new File(url);
        //try with Resource
        try( FileOutputStream fos = new FileOutputStream(f);
            OutputStreamWriter writer = new OutputStreamWriter(fos);){     
            for (T t : dataList) {
                writer.write(t.toString());
                writer.write("\n");
            }
            //writer.close();//đóng file lại
            System.out.println("Save finish");
            return true;//return
        }catch(Exception e){
            System.out.println("SaveToFile error: " + e);
            return false;
        }
    }
}
