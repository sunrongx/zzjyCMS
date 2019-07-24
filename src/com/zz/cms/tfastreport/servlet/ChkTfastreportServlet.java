package com.zz.cms.tfastreport.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.tarticle.service.TarticleService;
import com.zz.cms.tfastreport.service.TfastreportService;

public class ChkTfastreportServlet extends HttpServlet {
	/**
	 * 串行ID
	 */
	private static final long serialVersionUID = 284403613382212155L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//逻辑层对象
		TfastreportService tfs = new TfastreportService();
		
		//获取loginname参数
		String title = req.getParameter("title");
		//通过loginname查询并赋值结果
		int i = tfs.queryByTitle(title);
		//将信息响应给ajax
		if(i==0) {
			//没查到时，回应页面结果
			PrintWriter pw = resp.getWriter();
			pw.write("true");
			pw.flush();
			pw.close();
		}else {
			//查到即重复，回应页面结果
			PrintWriter pw = resp.getWriter();
			pw.write("false");
			pw.flush();
			pw.close();
		}
	}
}