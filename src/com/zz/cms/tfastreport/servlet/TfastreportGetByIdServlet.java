package com.zz.cms.tfastreport.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.SysException;
import com.zz.cms.tfastreport.bean.TfastreportBean;
import com.zz.cms.tfastreport.service.TfastreportService;

public class TfastreportGetByIdServlet extends HttpServlet {


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
		TfastreportService tfs = new TfastreportService();
		try {
			//使用ID查询并将结果赋值给tfas
			TfastreportBean tfas = tfs.queryTfasById(id);
			//获取全部部门
			List<TfastreportBean> tfass = tfs.queryAll();
			//将对象存储到作用域
			req.setAttribute("tfas", tfas);
			req.setAttribute("tfass", tfass);
			//转发
			req.getRequestDispatcher("tfas/update.jsp").forward(req, resp);
			
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
