package net.hzsec.web.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.hzsec.config.exception.BusinessException;
import net.hzsec.entity.system.Role;
import net.hzsec.service.IRoleService;
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
@RequestMapping("/role")
@Api(value = "角色管理")
public class RoleController {

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/insert")
    @ApiOperation(value = "添加角色")
    public ResponseEntity<String> insert(@Valid @RequestBody Role role) throws BusinessException {
        if (roleService.insert(role)){
            return ResponseEntity.ok().body("添加成功");
        }
        return ResponseEntity.badRequest().body("添加失败");
    }
}
