package cn.jasonhu.impl.service;

import cn.jasonhu.commons.entity.User;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface UserService  extends IService<User> {

    List<User> getUserList();
}
