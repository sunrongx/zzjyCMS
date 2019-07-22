package com.zz.cms.tfastreport.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zz.cms.exception.BusinessException;
import com.zz.cms.tfastreport.bean.TfastreportBean;
import com.zz.cms.tfastreport.service.TfastreportService;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.user.service.UserService;

public class TfastreportAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//新增广告，获取新增广告页面中表单的数据，将数据封装到bean包里
		//创建tfasBean对象
		TfastreportBean tfas = new TfastreportBean();
		//获取页面信息给tfas赋值
		tfas.setTitle(req.getParameter("title"));
		//获取页面信息给tfas赋值
		tfas.setContent(req.getParameter("content"));
		//获取页面信息给tfas赋值
//		tfas.setCtime(req.getParameter("ctime"));
/*		//创建session
		HttpSession session = req.getSession();
		
		//从session中获取广告信息
		TadvertBean tfass = (TfastreportBean) session.getAttribute("tfasbean");*/
		//创建service层对象
		TfastreportService ts = new TfastreportService();
		try {
			//添加广告后转发到广告查询页面
			if(ts.insertTfastreport(tfas)>0){
				req.getRequestDispatcher("tfas.do").forward(req, resp);
			}
		} catch (BusinessException e) {
			//提示错误信息
			req.setAttribute("msg", e.getErrMsg());
			//转发至原页面
			req.getRequestDispatcher("tfas.do").forward(req,resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
