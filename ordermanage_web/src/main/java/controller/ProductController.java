package controller;


import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        List<Product> allProduct = productService.findAllProduct();
        ModelAndView modelAndView = new ModelAndView();
        ModelMap map = modelAndView.getModelMap();
        map.addAttribute("allProduct", allProduct);
        modelAndView.setViewName("product_manage");
        return modelAndView;
    }
    @RequestMapping("/add")
    public String add(Product product){
        productService.add(product);
        return "redirect:/product/findAll";
    }
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        ModelMap map = modelAndView.getModelMap();
        map.addAttribute("product",product);
        modelAndView.setViewName("product_update");
        return modelAndView;
    }
    @RequestMapping("/updateById")
    public String updateById(Product product){
        productService.updateById(product);
        return "redirect:/product/findAll";
    }
    @RequestMapping("/deleteById")
    public String deleteById(String[] ids){
        productService.deleteById(ids);
        return "redirect:/product/findAll";
    }
}
