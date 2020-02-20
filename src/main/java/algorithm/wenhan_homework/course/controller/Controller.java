package algorithm.wenhan_homework.course.controller;

import algorithm.wenhan_homework.course.model.Course;
import algorithm.wenhan_homework.course.model.User;
import algorithm.wenhan_homework.course.service.LoginService;

/**
 * @author phenix
 * @date 2020/2/20 11:44
 */
public class Controller {



    private LoginService loginService = new LoginService();

    /**
     *
     * @return
     */
    public boolean login(String name, String pass){
        // 判断用户名，密码是否匹配
        return loginService.login(name, pass);
    }

    /**
     * 选课
     * @param user
     * @param course
     */
    public void choose(User user, Course course){

    }


}
