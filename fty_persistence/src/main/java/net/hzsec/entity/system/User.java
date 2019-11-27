package net.hzsec.entity.system;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xxh123
 * @since 2019-10-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("fty_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id",type = IdType.AUTO)
    private Long userId;

    @NotBlank(message = "用户名不能为空")
    @Size(max = 50,message = "用户名长度不能超过50")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Size(max = 20,message = "电话号码格式不正确")
    private String mobile;

    @Email(message = "邮箱格式不正确")
    private String email;

    private int status;

    @TableField("created_time")
    private Date createdTime;

    private String avatar;

    private String introduction;

    @TableField(exist = false)
    private List<String> roles;

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
