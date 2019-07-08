package com.zs.zscms.tadvert.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.zscms.tadvert.bean.TadvertBean;
import com.zs.zscms.tadvert.service.TadvertService;
import com.zz.cms.exception.SysException;

public class TadvertGetByIdServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7060929237049768098L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//获取要修改的用户的ID
		int id = Integer.parseInt(req.getParameter("id"));
		//创建逻辑层对象
		TadvertService tas = new TadvertService();
		try {
			//使用ID查询并将结果赋值给user
			TadvertBean tadv = tas.queryTadvById(id);
			//获取全部部门
			List<TadvertBean> tadvs = tas.queryAll();
			//将对象存储到作用域
			req.setAttribute("tadv", tadv);
			req.setAttribute("tadvs", tadvs);
			//转发
			req.getRequestDispatcher("tadv/update.jsp").forward(req, resp);
			
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
