package cn.jasonhu.impl.Mapper;

import cn.jasonhu.commons.entity.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> getUserList();
}
