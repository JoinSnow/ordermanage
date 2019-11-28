package controller;

import domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.MemberService;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Member> members = memberService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("members",members);
        modelAndView.setViewName("member_list");
        return modelAndView;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        Member member = memberService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("member",member);
        modelAndView.setViewName("member_show");
        return modelAndView;
    }

}
