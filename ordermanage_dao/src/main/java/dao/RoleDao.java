package dao;

import domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
}
