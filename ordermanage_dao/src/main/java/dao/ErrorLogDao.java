package dao;

import domain.ErrorLog;
import domain.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorLogDao {
    @Insert("insert into error_log values(#{id},#{visitTime},#{username},#{ip},#{url},#{method},#{errorCode})")
    void add(ErrorLog errorLog);

    @Select("select * from error_log")
    List<ErrorLog> all();
}
