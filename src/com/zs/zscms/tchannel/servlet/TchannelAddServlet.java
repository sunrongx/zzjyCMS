package com.zs.zscms.tchannel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.zscms.tchannel.bean.TchannelBean;
import com.zs.zscms.tchannel.service.TchannelService;
import com.zz.cms.exception.BusinessException;

public class TchannelAddServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7932430465323301201L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//新增栏目，获取新增栏目页面中表单的数据，将数据封装到bean包里
		//创建Bean对象
		TchannelBean tcha = new TchannelBean();
		//获取页面信息给tcha赋值
		tcha.setCname(req.getParameter("cname"));
		//获取页面信息给tcha赋值
		tcha.setPid(Integer.parseInt(req.getParameter("pid")));
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
//		tcha.setSort(Integer.parseInt(req.getParameter("sort")));
		
		//创建service层对象
		TchannelService tcs = new TchannelService();
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
