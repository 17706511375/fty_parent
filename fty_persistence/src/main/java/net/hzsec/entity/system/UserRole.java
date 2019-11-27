package net.hzsec.entity.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xxh123
 * @since 2019-10-29
 */
@TableName("fty_user_role")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_role_id", type = IdType.AUTO)
    private Long userRoleId;
    @TableField("user_id")
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    @TableField("role_id")
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @Override
    protected Serializable pkVal() {
        return this.userRoleId;
    }


}
