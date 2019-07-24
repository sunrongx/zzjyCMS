package com.zz.cms.tarticle.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.tarticle.bean.TarticleBean;
import com.zz.cms.tarticle.service.TarticleService;
import com.zz.cms.tchannel.bean.TchannelBean;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.user.service.UserService;

/**
 * 查询servlet
 * @author Administrator
 *
 */
public class QueryTarticleServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8300273917562105621L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TarticleService tas = new TarticleService();
		//List<TarticleBean> tarts = tas.queryAll();
		List<TchannelBean> tchas = tas.queryChan();
		int size = 5;
		// 获取name的值
		String name = req.getParameter("name");
		// 赋值总页数给pageCount
		int pageCount = tas.queryPageCounts(size);
		// 获取当前的页数
		String p = req.getParameter("currentPage");
		//初始化页数为1
		int page = 1;
		try {
			//将当前页数赋值给页数
			page = Integer.parseInt(p);
			//判断输入的页数是否符合规则
			if (page > pageCount) {
				//超过总页数定义为1
				page = 1;
			}
		} catch (Exception e) {
			//输入乱七八糟的东西全定义为1
			page = 1;
		}
		
		// 模糊查询搜索没有值时，默认为空字符串，拼到sql里查询所有
		if (name == null || name.equals(" ")) {
			name = "";
		}
		//分页查询文章
		List<TarticleBean> tarts = tas.queryByPage(name, page, size);
		// req.setAttribute("page", page);
		//塞入信息作为参数
		req.setAttribute("p", p);
		req.setAttribute("currentPage", page);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("name", name);
		
		// 将文章集合存储到req作用域中
		req.setAttribute("tarts", tarts);
		req.setAttribute("tchas", tchas);
		// 转发到文章查询页面，在查询页面显示数据
		req.getRequestDispatcher("tart/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
