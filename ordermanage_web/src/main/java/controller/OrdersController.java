package controller;

import com.github.pagehelper.PageInfo;
import domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.OrdersService;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Orders> all = ordersService.findAll(pageNum, pageSize);
        modelAndView.getModelMap().addAttribute("allOrders", all);
        modelAndView.setViewName("orders_list");
        return modelAndView;
    }

    @RequestMapping("/info")
    public ModelAndView findInfo(String id) {
        ModelAndView modelAndView = new ModelAndView();
        Orders orders = ordersService.findInfo(id);
        modelAndView.getModelMap().addAttribute("orders", orders);
        modelAndView.setViewName("orders_show");
        return modelAndView;
    }
}
