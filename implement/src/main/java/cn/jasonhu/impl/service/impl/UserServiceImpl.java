package cn.jasonhu.impl.service.impl;

import cn.jasonhu.commons.entity.User;
import cn.jasonhu.impl.dao.UserDao;
import cn.jasonhu.impl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUserList(){
        return userDao.getUserList();
    }
}
