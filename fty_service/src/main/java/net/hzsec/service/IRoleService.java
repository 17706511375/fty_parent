package net.hzsec.service;

import net.hzsec.entity.system.Role;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xxh123
 * @since 2019-10-25
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据用户ID查询对应的角色
     * @param userId 用户ID
     * @return  角色集合
     */
     List<Role> selectRoleByUserId(Long userId);
}
