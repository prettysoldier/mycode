package algorithm.wenhan_homework.course.model;

import java.io.Serializable;

/**
 * @author phenix
 * @date 2020/2/20 11:47
 */
public class Course implements Serializable {
    private String name;

    private String address;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

}
