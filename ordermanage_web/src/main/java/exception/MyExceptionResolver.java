package exception;


import domain.MyException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import util.MailUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

public class MyExceptionResolver implements HandlerExceptionResolver {
    private String email = "825961235@qq.com";

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        // 如果异常类型不是我们自定义的异常，我们转为自定义异常。
        if (!(e instanceof MyException)) {
            e = new MyException("系统抛出的异常", "1000");
        }
        modelAndView.setViewName("error");
        e.printStackTrace();
        // 获取异常栈信息
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        e.printStackTrace(new java.io.PrintWriter(buf, true));
        String expmessage = buf.toString();
        // 给程序员发送邮件：异常信息邮件
        String text = "出异常了！错误码：" + ((MyException) e).getCode() +
                "<br>异常信息：" + e.getMessage() +
                "<br>异常栈信息：" + expmessage;
        // 使用新线程给程序员发送邮件
        MailUtils mailUtils = new MailUtils(email, text, "order_manage系统出异常了！");
        Thread thread = new Thread(mailUtils);
        thread.start();
        return modelAndView;
    }
}
