package com.zz.cms.tfastreport.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.SysException;
import com.zz.cms.tfastreport.bean.TfastreportBean;
import com.zz.cms.tfastreport.service.TfastreportService;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.user.service.UserService;

public class TfastreportUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建tfasbean对象
		TfastreportBean tfas = new TfastreportBean();
		//从页面获取id并赋值给int类型的id
		int id = Integer.parseInt(req.getParameter("id"));
		//赋值给tfas
		tfas.setId(id);
		//赋值给tfas
		tfas.setTitle(req.getParameter("title"));
		//赋值给tfas
		tfas.setContent(req.getParameter("content"));
		//赋值给tfas
		tfas.setCtime(req.getParameter("ctime"));
		
		//创建逻辑层对象
		TfastreportService ts = new TfastreportService();
		try {
			//修改信息
			ts.updateTfas(tfas);
			//转发
			req.getRequestDispatcher("tfas.do").forward(req, resp);
			
		} catch (SysException e) {
			// 修改失败时
			req.setAttribute("id", id);
			
			req.setAttribute("msg", e.getErrMsg());
			//转发至原页面
			req.getRequestDispatcher("tfasget.do").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
