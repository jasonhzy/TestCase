package cn.jasonhu.impl.dao;

import cn.jasonhu.commons.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> getUserList();
}
