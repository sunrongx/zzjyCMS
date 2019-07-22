package com.zz.cms.tchannel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.SysException;
import com.zz.cms.tchannel.bean.TchannelBean;
import com.zz.cms.tchannel.service.TchannelService;

public class TchannelUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建逻辑层对象
		TchannelService tcs = new TchannelService();
		//创建Tchannelbean对象
		TchannelBean tcha = new TchannelBean();
		//从页面获取id并赋值给int类型的id
		int id = Integer.parseInt(req.getParameter("id"));
		//赋值给tcha
		tcha.setId(id);
		//赋值给tcha
		tcha.setCname(req.getParameter("cname"));
		//赋值给tcha
//		String s1 = req.getParameter("pid").trim();
//		System.out.println(s1);
//		tcha.setPid(Integer.parseInt(s1.trim()));
		String pid = req.getParameter("pid").trim();
		if(pid.equals("0")) {
			tcha.setPid(0);
		}else {
/*			for (TchannelBean tch : tcs.queryAll()) {
				if(tch.getCname().equals(pid)) {
					tcha.setPid(tch.getId());
				}
			}
*/
			tcha.setPid(Integer.parseInt(pid.trim()));
		}
		//赋值给tcha
		String s2 = req.getParameter("lev");
		//System.out.println(s2);
		tcha.setLev(Integer.parseInt(s2.trim()));
		//赋值给tcha
		String s3 = req.getParameter("isleaf");
		//System.out.println(s3);
		tcha.setIsleaf(Integer.parseInt(s3.trim()));
		//赋值给tcha
		String s4 = req.getParameter("sort");
		//System.out.println(s4);
		tcha.setSort(Integer.parseInt(s4.trim()));
		
		
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
