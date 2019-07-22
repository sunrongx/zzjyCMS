package com.zz.cms.tchannel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.SysException;
import com.zz.cms.tchannel.service.TchannelService;
import com.zz.cms.user.service.UserService;

public class TchannelDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取页面的ID
		String id=req.getParameter("id");
		//创建逻辑层对象
		TchannelService tcs = new TchannelService();
		try {
			//调用删除的方法
			tcs.deleteTcha(Integer.parseInt(id));
			//重定向
			resp.sendRedirect("tcha.do");
			
			
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SysException e) {
			// TODO 自动生成的 catch 块
			e.getErrMsg();
			//重定向
			resp.sendRedirect("tcha.do");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
