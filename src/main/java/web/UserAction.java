package web;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.UserService;

@Controller("userAction")
public class UserAction extends ActionSupport implements ModelDriven<User> {

    private final static Logger log = LoggerFactory.getLogger(UserAction.class);

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String login(){
        log.info("登陆信息："+user.toString());
        User serviceByUser = userService.findByUser(user);
        if (serviceByUser==null){
            log.info("登录失败！！！");
            return "loginfail";
        }
        log.info("登录成功！！！");
        return "loginsucc";
    }


    @Override
    public User getModel() {
        return user;
    }
}
