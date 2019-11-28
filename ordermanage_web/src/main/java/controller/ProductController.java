package controller;


import com.github.pagehelper.PageInfo;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;


import java.io.UnsupportedEncodingException;


@Controller
@RequestMapping("/product")
@SessionAttributes("allProduct")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Product> allProduct = productService.findAllProduct(pageNum, pageSize);
        modelAndView.getModelMap().addAttribute("allProduct", allProduct);
        modelAndView.setViewName("product_manage");
        return modelAndView;
    }

    @RequestMapping("/findAllOrderBy")
    public ModelAndView findAllOrderBy(String orderBy, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Product> allProduct = productService.findAllProductOrderBy(pageNum, pageSize, orderBy);
        modelAndView.getModelMap().addAttribute("allProduct", allProduct);
        modelAndView.setViewName("product_manage");
        return modelAndView;
    }

    @RequestMapping("/findByProductName")
    public ModelAndView findByProductName(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, String productName) {
        try {
            productName = new String(productName.getBytes("ISO-8859-1"), "utf-8");
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Product> allProduct = productService.findByProductName(pageNum, pageSize, productName);
        modelAndView.getModelMap().addAttribute("allProduct", allProduct);
        modelAndView.setViewName("product_manage");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(Product product) {
        productService.add(product);
        return "redirect:/product/findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        ModelMap map = modelAndView.getModelMap();
        map.addAttribute("product", product);
        modelAndView.setViewName("product_update");
        return modelAndView;
    }

    @RequestMapping("/updateById")
    public String updateById(Product product) {
        productService.updateById(product);
        return "redirect:/product/findAll";
    }

    @RequestMapping("/deleteById")
    public String deleteById(String[] ids) {
        if (ids != null) {
            productService.deleteById(ids);
        }
        return "redirect:/product/findAll";
    }

    @RequestMapping("/openStatusById")
    public String openStatusById(String[] ids) {
        if (ids != null) {
            productService.openStatusById(ids);
        }
        return "redirect:/product/findAll";
    }

    @RequestMapping("/closeStatusById")
    public String closeStatusById(String[] ids) {
        if (ids != null) {
            productService.closeStatusById(ids);
        }
        return "redirect:/product/findAll";
    }
}
