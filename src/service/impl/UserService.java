package service.impl;

import dao.UserDao;
import entity.UserEntity;
import service.Service;

import java.util.List;

/**
 * Created by Ace on 2017/5/19.
 */
public class UserService implements Service{
    private UserDao userDao=new UserDao();
    private UserEntity search;

    public UserService() {
    }

    public UserService(UserEntity search) {
        this.search = search;

    }

    public UserEntity login(UserEntity u){
        return userDao.login(u);
    }

    @Override
    public List<UserEntity> getList(){
        return userDao.getList(search);
    }

}
