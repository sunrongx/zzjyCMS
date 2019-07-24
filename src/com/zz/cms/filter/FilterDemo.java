package com.zz.cms.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 过滤器
 * @author Administrator
 *
 */
public class FilterDemo implements Filter {

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//强转原因：这里的req是ServletRequest类型的变量
		//我们使用的是HttpServletRequest，这个类型比较大，需要强转
		//将req对象强转为HttpServletRequest类型的对象request
		HttpServletRequest request = (HttpServletRequest) req;
		//将resp对象强转为response
		HttpServletResponse response = (HttpServletResponse) resp;
		
		/**
		 * 过滤规则
		 * */
		//设置请求和响应的编码格式
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//继续向后执行过滤链，又回到这个方法，那么编码格式改为utf-8
		chain.doFilter(request,response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根
		
	}


	

	
}
