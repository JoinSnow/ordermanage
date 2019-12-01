package controller;

import com.github.pagehelper.PageInfo;
import domain.ErrorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.ErrorLogService;

import java.util.List;

@Controller
@RequestMapping("/errorLog")
public class ErrorLogController {
    @Autowired
    private ErrorLogService errorLogService;

    @RequestMapping("/all")
    @PreAuthorize("hasAnyRole({'ROLE_ROOT'})")
    public ModelAndView all(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        PageInfo<ErrorLog> errorLogs = errorLogService.all(pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("errorLogs", errorLogs);
        modelAndView.setViewName("error_log_list");
        return modelAndView;
    }
}
