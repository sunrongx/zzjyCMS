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

public class ChannelServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TarticleService ts = new TarticleService();
		List<TchannelBean> chas = new ArrayList<>();
		chas = ts.queryChan();
		req.setAttribute("chas", chas);
		req.getRequestDispatcher("tart/add.jsp").forward(req, resp);
		
	}
}
