package algorithm.wenhan_homework.course.service;

import algorithm.wenhan_homework.course.model.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author phenix
 * @date 2020/2/20 11:59
 */
public class LoginService {

    public boolean login(String name, String pass){
        // 从IO里读取相应的数据进行判断
        File inFile = new File("用户密码文件.csv");
        // try-with-resource
        try(BufferedReader reader = new BufferedReader(new FileReader(inFile))){
            // 读取文件类容进行判断
            String inString;
            while ((inString = reader.readLine()) != null) {
                String arr[] = inString.split(",");

                for (int i = 0; i < arr.length; i++) {
                    if(name.equals(arr[0])){
                        if(pass.equals(arr[1])){
                            // login success
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }




}
