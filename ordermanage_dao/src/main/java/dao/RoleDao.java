package dao;

import domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    @Select("select * from role where id in(select roleId from user_role where userId =#{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permissions",column = "id",many = @Many(select = "dao.PermissionDao.findByRoleId"))
    })
    List<Role> findRolesByUserId(String id);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role values(#{id},#{roleName},#{roleDesc})")
    void add(Role role);
    //根据userid查询用户没有的角色
    @Select("SELECT * FROM role WHERE id NOT IN(SELECT roleId FROM user_role WHERE userId=#{userId})")
    List<Role> findOtherByUserId(String userId);

    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "users", column = "id", many = @Many(select = "dao.UserInfoDao.findByRoleId")),
            @Result(property = "permissions", column = "id", many = @Many(select = "dao.PermissionDao.findByRoleId"))
    })
    Role findById(String id);

    @Update("update role set roleName=#{roleName},roleDesc=#{roleDesc} where id=#{id}")
    void updateById(Role role);

    //给角色添加权限
    @Insert("insert into role_permission values(#{roleId},#{permissionId})")
    void addPermission(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
