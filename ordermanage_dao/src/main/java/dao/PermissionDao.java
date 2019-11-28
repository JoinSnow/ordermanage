package dao;

import domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {
    @Select("SELECT * FROM permission WHERE id IN(SELECT permissionId FROM role_permission WHERE roleId =#{roleId})")
    List<Permission> findByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission values(#{id},#{permissionName},#{url})")
    void add(Permission permission);

    @Update("update permission set permissionName=#{permissionName},url=#{url} where id=#{id}")
    void updateById(Permission permission);

    @Select("select * from permission where id=#{id}")
    Permission findById(String id);
}
