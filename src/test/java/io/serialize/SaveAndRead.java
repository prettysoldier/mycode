
package test.java.io.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Shuaijun He
 */
public class SaveAndRead {

    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
        MyObject myObject = MyObject.getInstance();
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(new File("D:/myobject.txt")))) {
            oos.writeObject(myObject);
            System.out.println(myObject.hashCode());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
            new File("D:/myobject.txt")))) {
            MyObject readObj = (MyObject) ois.readObject();
            System.out.println(readObj.hashCode());
        }
    }
}
