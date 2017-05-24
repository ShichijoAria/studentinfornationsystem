package service;

import dao.UserDao;
import entity.UserEntity;

import java.util.List;

/**
 * Created by Ace on 2017/5/19.
 */
public class UserService {
    private UserDao userDao=new UserDao();

    public UserEntity login(UserEntity u){
        return userDao.login(u);
    }

    public List<UserEntity> view(UserEntity search){
        return userDao.getList(search);
    }
}
