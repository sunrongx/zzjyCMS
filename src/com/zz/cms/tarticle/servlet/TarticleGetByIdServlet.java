package com.zz.cms.tarticle.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.SysException;
import com.zz.cms.tarticle.bean.TarticleBean;
import com.zz.cms.tarticle.service.TarticleService;
import com.zz.cms.tchannel.bean.TchannelBean;
import com.zz.cms.tfastreport.bean.TfastreportBean;
import com.zz.cms.tfastreport.service.TfastreportService;

public class TarticleGetByIdServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6360983311083869287L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取要修改的文章的ID
		int id = Integer.parseInt(req.getParameter("id"));
		//创建逻辑层对象
		TarticleService tas = new TarticleService();
		try {
			//使用ID查询并将结果赋值给tart
			TarticleBean tart = tas.queryTartById(id);
			//获取全部部门
			List<TarticleBean>tarts = tas.queryAll();
			List<TchannelBean> chas = tas.queryChan();
			//将对象存储到作用域
			req.setAttribute("tart", tart);
			req.setAttribute("tarts", tarts);
			req.setAttribute("chas", chas);
			//转发
			req.getRequestDispatcher("tart/update.jsp").forward(req, resp);
			
		} catch (SysException e) {
			e.getErrMsg();
			resp.sendRedirect("error.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
