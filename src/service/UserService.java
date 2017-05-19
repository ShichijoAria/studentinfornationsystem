package service;

import dao.UserDao;
import entity.UserEntity;

/**
 * Created by Ace on 2017/5/19.
 */
public class UserService {
    private UserDao userDao=new UserDao();

    public UserEntity login(UserEntity u){
        return userDao.login(u);
    }
}
