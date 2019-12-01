package controller;

import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.RoleService;

import java.util.List;


@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Role> roles = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("roles",roles);
        modelAndView.setViewName("role_list");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(Role role){
        roleService.add(role);
        return "redirect:/role/findAll";
    }

    @RequestMapping("/findOtherByUserId")
    public ModelAndView findOtherByUserId(String userId){
        List<Role> otherRoles = roleService.findOtherByUserId(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("otherRoles",otherRoles);
        modelAndView.getModelMap().addAttribute("userId",userId);
        modelAndView.setViewName("user_role_add");
        return modelAndView;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) {
        Role role = roleService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("role", role);
        modelAndView.setViewName("role_show");
        return modelAndView;
    }
    @RequestMapping("/updateById")
    public String updateById(Role role){
        roleService.updateById(role);
        return "redirect:/role/findAll";
    }

    //给角色添加权限
    @RequestMapping("/addPermission")
    public String addPermission(String roleId,String[] permissionIds){
        for (String permissionId:permissionIds) {
            roleService.addPermission(roleId,permissionId);
        }
        return "redirect:/role/findAll";
    }
}

