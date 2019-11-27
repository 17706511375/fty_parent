package net.hzsec.entity.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author xxh123
 * @since 2019-10-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("fty_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;
    /**
     * 角色英文名称
     */
    @Size(max = 64,message = "长度不能超过64位")
    @NotBlank(message = "角色名不能为空")
    private String name;
    /**
     * 角色中文名称
     */
    @Size(max = 64,message = "长度不能超过64位")
    @NotBlank(message = "角色中文名不能为空")
    private String nameZh;

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }
}
