package com.zz.cms.tchannel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.BusinessException;
import com.zz.cms.tchannel.bean.TchannelBean;
import com.zz.cms.tchannel.service.TchannelService;

public class TchannelAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建service层对象
		TchannelService tcs = new TchannelService();
		//新增栏目，获取新增栏目页面中表单的数据，将数据封装到bean包里
		//创建Bean对象
		TchannelBean tcha = new TchannelBean();
		//获取页面信息给tcha赋值
		tcha.setCname(req.getParameter("cname"));
		//获取页面信息给tcha赋值
		//String strpid = req.getParameter("strPid");
		tcha.setPid(Integer.parseInt(req.getParameter("pid").trim()));
/*		if(strpid.equals("0")) {
			tcha.setPid(0);
			tcha.setStrPid("无上级栏目");
		}else {
			for (TchannelBean tch : tcs.queryAll()) {
				if(tch.getCname().equals(strpid)) {
					tcha.setPid(tch.getId());
					tcha.setStrPid(tch.getCname());
				}
			}
		}
*/
		//获取页面信息给tcha赋值
		tcha.setLev(Integer.parseInt(req.getParameter("lev")));
		if (req.getParameter("lev").equals("1")) {
			tcha.setStrlev("一级");
		}else{
			tcha.setStrlev("二级");
		}
		tcha.setLev(Integer.parseInt(req.getParameter("isleaf")));
		//获取页面信息给tcha赋值
		if (req.getParameter("isleaf").equals("1")) {
			tcha.setStrIsleaf("是");
		}else{
			tcha.setStrIsleaf("不是");
		}
		//获取页面信息给tcha赋值
		tcha.setSort(Integer.parseInt(req.getParameter("sort")));
		
		
		try {
			//添加栏目后转发到栏目查询页面
			if(tcs.insertTcha(tcha)>0){
				req.getRequestDispatcher("tcha.do").forward(req, resp);
			}
		} catch (BusinessException e) {
			req.setAttribute("msg", e.getErrMsg());
			req.getRequestDispatcher("tchaadd.do").forward(req,resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
