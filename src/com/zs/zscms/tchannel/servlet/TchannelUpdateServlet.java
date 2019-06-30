package com.zs.zscms.tchannel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.zscms.exception.SysException;
import com.zs.zscms.tchannel.bean.TchannelBean;
import com.zs.zscms.tchannel.service.TchannelService;

public class TchannelUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//创建Tchannelbean对象
		TchannelBean tcha = new TchannelBean();
		//从页面获取id并赋值给int类型的id
		int id = Integer.parseInt(req.getParameter("id"));
		//赋值给tcha
		tcha.setId(id);
		//赋值给tcha
		tcha.setCname(req.getParameter("cname"));
		//赋值给tcha
		tcha.setPid(Integer.parseInt(req.getParameter("pid")));
		//赋值给tcha
		tcha.setLev(Integer.parseInt(req.getParameter("lev")));
		//赋值给tcha
		tcha.setIsleaf(Integer.parseInt(req.getParameter("isleaf")));
/*		//赋值给tcha
		tcha.setSort(Integer.parseInt(req.getParameter("sort")));
*/
		//创建逻辑层对象
		TchannelService tcs = new TchannelService();
		try {
			//修改信息
			tcs.updateTcha(tcha);
			//转发
			req.getRequestDispatcher("tcha.do").forward(req, resp);
			
		} catch (SysException e) {
			// 修改失败时
			req.setAttribute("id", id);
			req.setAttribute("msg", e.getErrMsg());
			//转发至原页面
			req.getRequestDispatcher("tchaupdate.do").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
