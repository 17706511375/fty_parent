package net.hzsec.mapper;

import net.hzsec.entity.system.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author xxh123
 * @since 2019-10-25
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户ID查询对应的角色
     * @param userId 用户ID
     * @return  角色集合
     */
    List<Role> selectRoleByUserId(Long userId);
}
