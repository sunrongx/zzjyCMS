package com.zz.cms.tchannel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.tchannel.bean.TchannelBean;
import com.zz.cms.tchannel.service.TchannelService;

/**
 * 给添加页面传值
 * @author Administrator
 *
 */
public class TchannelGetAddServlet extends HttpServlet {
	
	/**
	 * 串行ID
	 */
	private static final long serialVersionUID = -4045720385274223729L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//创建逻辑层对象
		TchannelService tcs = new TchannelService();
			//获取全部部门
			List<TchannelBean> tchas = tcs.queryAll();
			
			//将对象存储到作用域
			req.setAttribute("tchas", tchas);
			//转发
			req.getRequestDispatcher("tcha/add.jsp").forward(req, resp);
	}

}
