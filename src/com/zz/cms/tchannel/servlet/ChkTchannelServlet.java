package com.zz.cms.tchannel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.tchannel.bean.TchannelBean;
import com.zz.cms.tchannel.service.TchannelService;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.user.service.UserService;

public class ChkTchannelServlet extends HttpServlet {
	/**
	 * 串行ID
	 */
	private static final long serialVersionUID = -1985620777601205940L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//逻辑层对象
		TchannelService ts = new TchannelService();
		
		//获取loginname参数
		String cname = req.getParameter("cname");
		//通过loginname查询并赋值结果
		int i = ts.queryByCname(cname);
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
