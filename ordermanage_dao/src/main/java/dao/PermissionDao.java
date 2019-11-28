package dao;

import domain.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {
    @Select("SELECT * FROM permission WHERE id IN(SELECT permissionId FROM role_permission WHERE roleId =#{roleId})")
    List<Permission> findByRoleId(String roleId);
}
