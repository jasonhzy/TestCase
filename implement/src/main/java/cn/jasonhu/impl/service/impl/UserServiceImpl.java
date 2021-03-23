package cn.jasonhu.impl.service.impl;

import cn.jasonhu.commons.entity.User;
import cn.jasonhu.impl.Mapper.UserMapper;
import cn.jasonhu.impl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public List<User> getUserList() {
        return baseMapper.getUserList();
    }
}
