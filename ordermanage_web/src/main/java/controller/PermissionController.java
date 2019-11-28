package controller;

import domain.Permission;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.PermissionService;
import service.RoleService;

import java.util.List;


@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Permission> permissions = permissionService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("permissions",permissions);
        modelAndView.setViewName("permission_list");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(Permission permission){
        permissionService.add(permission);
        return "redirect:/permission/findAll";
    }
}
