package dao;

import domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDao {
    @Select("select * from member where id=#{id}")
    Member findById(String id);
    @Select("select * from member")
    List<Member> findAll();
}
