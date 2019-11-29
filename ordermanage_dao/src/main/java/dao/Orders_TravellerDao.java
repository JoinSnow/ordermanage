package dao;

import domain.Orders_Traveller;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface Orders_TravellerDao {
    @Insert("insert into orders_traveller values(#{ordersId},#{travellerId})")
    void add(Orders_Traveller orders_traveller);

    @Delete("delete from orders_traveller where ordersId=#{ordersId}")
    void delByOrdersId(String ordersId);
}
