package dao;


import domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserInfoDao {
    @Select("select * from user_info where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", many = @Many(select = "dao.RoleDao.findRolesByUserId"))
    })
    UserInfo findByUsername(String username);

    @Select("select * from user_info")
    List<UserInfo> findAll();

    @Insert("insert into user_info values(#{id},#{username},#{password},#{email},#{phoneNum},#{status})")
    void add(UserInfo userInfo0);

    @Select("select * from user_info where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", many = @Many(select = "dao.RoleDao.findRolesByUserId"))
    })
    UserInfo findById(String id);

    @Insert("insert into user_role values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId")String roleId);

    @Select("SELECT * FROM user_info WHERE id IN(SELECT userId FROM user_role WHERE roleId =#{roleId})")
    List<UserInfo> findByRoleId(String roleId);
}
