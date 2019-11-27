package dao;


import domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
    @Select("select * from product")
    List<Product> findAllProduct();

    @Select("select * from product where id=#{id}")
    Product findById(String id);

    @Select("select * from product where productName like #{productName}")
    List<Product> findByProductName(String productName);

    @Insert("insert into product values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void add(Product product);

    @Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productStatus=#{productStatus},productDesc=#{productDesc} where id=#{id}")
    void updateById(Product product);

    @Delete("delete from product where id=#{id}")
    void deleteById(String id);

    @Update("update product set productStatus=#{productStatus} where id =#{id}")
    void updateStatus(Product product);

}
