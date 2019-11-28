package dao;

import domain.Member;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDao {
    @Select("select * from member where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "travellers", column = "id", many = @Many(select = "dao.TravellerDao.findByMemberId"))
    })
    Member findById(String id);

    @Select("select * from member")
    List<Member> findAll();
}
