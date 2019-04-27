package dao.impl;

import dao.UserDao;
import domin.User;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUser(User user) {
        String hql = "from User where username = ? and password = ?";
        List userList = this.sessionFactory.getCurrentSession().createQuery(hql)
                .setString(0,user.getUsername())
                .setString(1,user.getPassword())
                .list();
        log.info("返回用户列表："+userList.toString());
        System.out.println("username:::"+user.getUsername());
        if (userList.size()>0){
            return (User) userList.get(0);
        }
        return null;
    }
}
