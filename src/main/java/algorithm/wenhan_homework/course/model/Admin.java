package algorithm.wenhan_homework.course.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author phenix
 * @date 2020/2/20 12:22
 */
public class Admin extends User {

    /**
     * Display information for a given course (by course ID)
     * @param courseId
     * @return
     */
    public Course getCourse(String courseId){
        try{
            //FileInputSystem recieves bytes from a file
            FileInputStream fis = new FileInputStream("Course.ser");
            //ObjectInputStream does the deserialization-- it reconstructs the data into an object
            ObjectInputStream ois = new ObjectInputStream(fis);
            //Cast as Employee. readObject will take the object from ObjectInputStream
            Course de = (Course)ois.readObject();

            ois.close();
            fis.close();
            return de;
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return null;
    }
}
