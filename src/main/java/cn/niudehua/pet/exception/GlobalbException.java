package cn.niudehua.pet.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

public class GlobalbException implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
		String message = null;

		// 1.判断抛出的异常是否是系统自定义异常
		if (ex instanceof CustomException) {
			CustomException c = (CustomException) ex;
			message = c.getMessage();
		} else {
			// 2.如果不是系统自定义的异常
			// 3.打印异常信息
			ex.printStackTrace();

			// 把异常信息输出
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			message = sw.toString();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
