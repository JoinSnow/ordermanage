package dao;

import domain.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "product", column = "productId", one = @One(select = "dao.ProductDao.findById"))
    })
    List<Orders> findAll();

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "product", column = "productId", one = @One(select = "dao.ProductDao.findById")),
            @Result(property = "travellers", column = "id", many = @Many(select = "dao.TravellerDao.findByOrderId")),
            @Result(property = "member", column = "memberId", one = @One(select = "dao.MemberDao.findById"))
    })
    Orders findById(String id);

    @Insert("insert into orders values(#{orders.id},#{orders.orderNum},#{orders.orderTime},#{orders.peopleCount},#{orders.orderDesc},0,#{orders.orderStatus},#{productId},#{memberId})")
    void add(@Param("orders") Orders orders, @Param("productId") String productId, @Param("memberId") String memberId);

    @Select("SELECT MAX(orderNum) FROM orders")
    String findMaxOrderNum();

    @Update("update orders set orderStatus=1 where id=#{id}")
    void pay(String id);

    @Delete("delete from orders where id=#{id}")
    void del(String id);
}
