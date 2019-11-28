package controller;


import domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.UserInfoService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() {
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
}