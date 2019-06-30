package com.zs.zscms.tchannel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.zscms.tchannel.bean.TchannelBean;
import com.zs.zscms.tchannel.service.TchannelService;
import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.user.service.UserService;

public class TchannelServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建逻辑层对象
		TchannelService tcs = new TchannelService();
		//创建查询结果集合
		List<TchannelBean> tchas = tcs.queryAll();
		//将栏目集合存储到req作用域中
		req.setAttribute("tchas", tchas);
		//转发到栏目查询页面，在查询页面显示数据
		req.getRequestDispatcher("tcha/list.jsp").forward(req,resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
