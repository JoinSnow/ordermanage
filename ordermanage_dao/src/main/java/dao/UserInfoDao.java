package dao;


import domain.UserInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;



@Repository
public interface UserInfoDao {
    @Select("select * from user_info where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roles",column = "id",many = @Many(select = "dao.RoleDao.findRolesByUserId"))
    })
    UserInfo findByUsername(String username);
}
