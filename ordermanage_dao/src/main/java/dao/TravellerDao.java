package dao;

import domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellerDao {
    // 通过订单id查到关联的旅客信息
    @Select("SELECT * FROM traveller WHERE id IN(SELECT travellerId FROM orders_traveller WHERE ordersId=#{orderId})")
    List<Traveller> findByOrderId(String orderId);
    @Select("SELECT * FROM traveller WHERE id IN(SELECT travellerId FROM member_traveller WHERE memberId=#{memberId})")
    List<Traveller> findByMemberId(String memberId);
}
