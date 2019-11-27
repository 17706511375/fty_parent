package net.hzsec.web.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.hzsec.entity.system.UserRole;
import net.hzsec.service.IUserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author xxh
 * @since 2019/10/29
 */
@RestController
@Api(value = "用户角色管理")
@RequestMapping("/userRole")
public class UserRoleController {
    private final IUserRoleService userRoleService;

    public UserRoleController(IUserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/insert")
    @ApiOperation(value = "添加用户角色")
    public ResponseEntity<String> insert(@Valid @RequestBody UserRole userRole){

        if (userRoleService.insert(userRole)){
            return ResponseEntity.ok().body("添加成功");
        }
        return ResponseEntity.badRequest().body("添加失败");
    }

}
