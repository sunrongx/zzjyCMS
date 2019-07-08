package com.zs.zscms.tchannel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.zscms.tchannel.bean.TchannelBean;
import com.zs.zscms.tchannel.service.TchannelService;
import com.zz.cms.exception.SysException;

public class TchannelGetByIdServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2251031148815234591L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//获取要修改的栏目的ID
		int id = Integer.parseInt(req.getParameter("id"));
		//创建逻辑层对象
		TchannelService tcs = new TchannelService();
		try {
			//使用ID查询并将结果赋值给user
			TchannelBean tcha = tcs.queryTchaById(id);
			//获取全部部门
			List<TchannelBean> tchas = tcs.queryAll();
			//将对象存储到作用域
			req.setAttribute("tcha", tcha);
			req.setAttribute("tchas", tchas);
			//转发
			req.getRequestDispatcher("tcha/update.jsp").forward(req, resp);
			
		} catch (SysException e) {
			e.getErrMsg();
			resp.sendRedirect("error.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
