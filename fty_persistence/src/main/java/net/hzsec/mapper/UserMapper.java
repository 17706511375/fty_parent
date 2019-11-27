package net.hzsec.mapper;

import net.hzsec.entity.system.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xxh123
 * @since 2019-10-22
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息，包含role信息
     */
    User selectUserInfo(@Param("username") String username);
}
