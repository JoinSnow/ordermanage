package service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.OrdersDao;
import domain.Orders;
import domain.Orders_Traveller;
import domain.Traveller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private TravellerService travellerService;
    @Autowired
    private Orders_TravellerService orders_travellerService;

    public PageInfo<Orders> findAll(int pageNum, int pageSize) {
        // 查询之前说明页数和每页条数
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> all = ordersDao.findAll();
        // 查询后将结果封装到PageInfo中
        return new PageInfo<>(all);
    }


    public Orders findInfo(String id) {
        return ordersDao.findById(id);
    }

    public void add(Orders orders, String[] travellerIds, String productId, String memberId) {
        int maxOrdersNum = (Integer.parseInt(ordersDao.findMaxOrderNum())) + 1;
        String ordersNum = String.valueOf(maxOrdersNum);
        orders.setOrderNum(ordersNum);
        orders.setId(UUID.randomUUID().toString());
        orders.setOrderTime(new Date());
        orders.setOrderStatus(0);
        orders.setPeopleCount(travellerIds.length);
        ordersDao.add(orders, productId, memberId);
        Orders_Traveller orders_traveller = new Orders_Traveller();
        for (String travellerId : travellerIds) {
            Traveller traveller = travellerService.findById(travellerId);
            orders_traveller.setOrdersId(orders.getId());
            orders_traveller.setTravellerId(travellerId);
            orders_travellerService.add(orders_traveller);
        }
    }

    public void pay(String id){
        ordersDao.pay(id);
    }

    public void judge(){
        List<Orders> allOrders = ordersDao.findAll();
        for (Orders orders:allOrders) {
            if (orders.getOrderStatus()==0&& (new Date().getTime()-orders.getOrderTime().getTime())>20*1000){
                ordersDao.del(orders.getId());
            }
        }
    }
}
