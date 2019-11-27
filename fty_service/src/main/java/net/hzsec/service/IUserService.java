package net.hzsec.service;

import net.hzsec.entity.system.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xxh123
 * @since 2019-10-22
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息，包含role信息
     */
    User selectUserInfo(String username);
}
