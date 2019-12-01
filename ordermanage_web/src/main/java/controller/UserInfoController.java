package controller;


import domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.UserInfoService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/findAll")
    @Secured({"ROLE_USER"})
    public ModelAndView findAll() {
        int i=1/0;
        List<UserInfo> users = userInfoService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("users", users);
        modelAndView.setViewName("user_list");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(UserInfo userInfo) {
        userInfoService.add(userInfo);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/findById")
    @RolesAllowed({"ROLE_USER"})
    public ModelAndView findById(String id) {
        UserInfo user = userInfoService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("user", user);
        modelAndView.setViewName("user_show");
        return modelAndView;
    }


    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String userId, String[] roleIds) {
        for (String roleId:roleIds) {
            userInfoService.addRoleToUser(userId,roleId);
        }
        return "redirect:/user/findAll";
    }
}
