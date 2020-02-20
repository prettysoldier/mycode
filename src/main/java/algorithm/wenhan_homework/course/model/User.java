package algorithm.wenhan_homework.course.model;

import java.io.Serializable;

/**
 * @author phenix
 * @date 2020/2/20 11:42
 */
public class User implements Serializable {
    private String name;

    private String password;


    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }
}
