package net.hzsec.service.impl;

import net.hzsec.entity.system.User;
import net.hzsec.mapper.UserMapper;
import net.hzsec.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xxh123
 * @since 2019-10-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User selectUserInfo(String username) {
        return userMapper.selectUserInfo(username);
    }
}
