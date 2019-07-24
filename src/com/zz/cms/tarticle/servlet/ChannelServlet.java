package com.zz.cms.tarticle.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.tarticle.service.TarticleService;
import com.zz.cms.tchannel.bean.TchannelBean;

/**
 * 所属栏目servlet
 * @author Administrator
 *
 */
public class ChannelServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//创建service对象
		TarticleService ts = new TarticleService();
		//创建TchannelBean集合
		List<TchannelBean> chas = new ArrayList<>();
		//查询栏目信息赋值给集合
		chas = ts.queryChan();
		//作为参数转发
		req.setAttribute("chas", chas);
		//转发到添加页面
		req.getRequestDispatcher("tart/add.jsp").forward(req, resp);
		
	}
}
