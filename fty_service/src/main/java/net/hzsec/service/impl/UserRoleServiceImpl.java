package net.hzsec.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import net.hzsec.entity.system.UserRole;
import net.hzsec.mapper.UserRoleMapper;
import net.hzsec.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xxh123
 * @since 2019-10-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
