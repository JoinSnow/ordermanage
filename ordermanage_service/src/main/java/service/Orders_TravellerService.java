package service;

import dao.Orders_TravellerDao;
import domain.Orders_Traveller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Orders_TravellerService {
    @Autowired
    private Orders_TravellerDao orders_travellerDao;

    public void add(Orders_Traveller orders_traveller){
        orders_travellerDao.add(orders_traveller);
    }

    public void delByOrdersId(String ordersId){
        orders_travellerDao.delByOrdersId(ordersId);
    }
}
