package controller;

import com.github.pagehelper.PageInfo;
import domain.Member;
import domain.Orders;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;
import service.MemberService;
import service.OrdersService;
import service.ProductService;


import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ProductService productService;
    @Autowired
    private MemberService memberService;

    @RequestMapping("/findAllMessage")
    public ModelAndView findAllMessage() {
        List<Product> products = productService.findAllNoPage();
        List<Member> members = memberService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("products", products);
        modelAndView.getModelMap().addAttribute("members", members);
        modelAndView.setViewName("orders_add");
        return modelAndView;
    }

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        ordersService.judge();
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

    @RequestMapping("/add")
    public String add(Orders orders, String[] travellerIds,String productId,String memberId) {
        ordersService.add(orders, travellerIds,productId,memberId);
        return "redirect:/orders/findAll";
    }

    @RequestMapping("/pay")
    public String pay(String id){
        ordersService.pay(id);
        return "redirect:/orders/findAll";
    }
}
