package com.zz.cms.tchannel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.SysException;
import com.zz.cms.tarticle.bean.TarticleBean;
import com.zz.cms.tchannel.bean.TchannelBean;
import com.zz.cms.tchannel.service.TchannelService;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.user.service.UserService;

/**
 * 栏目servlet
 * @author Administrator
 *
 */
public class TchannelServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建逻辑层对象
		TchannelService tcs = new TchannelService();
		int size = 5;
		// 获取name的值
		String name = req.getParameter("name");
		// 赋值总页数给pageCount
		int pageCount = tcs.queryPageCounts(size);
		// 获取当前的页数
		String p = req.getParameter("currentPage");
		//初始化页数
		int page = 1;
		try {
			//将获取的页数赋值
			page = Integer.parseInt(p);
			//大于总页数重新赋值1
			if (page > pageCount) {
				page = 1;
			}
		} catch (Exception e) {
			//输入乱七八糟的东西重赋值1
			page = 1;
		}
		
		// 模糊查询搜索没有值时，默认为空字符串
		if (name == null || name.equals(" ")) {
			name = "";
		}
		//将查询到的数据赋值给TchannelBean集合
		List<TchannelBean> tchas = tcs.queryByPage(name, page, size);
		// req.setAttribute("page", page);
		//塞入参数
		req.setAttribute("p", p);
		req.setAttribute("currentPage", page);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("name", name);
		
		
		
		//创建查询结果集合
		//List<TchannelBean> tchas = tcs.queryAll();
/*		for (TchannelBean tch : tchas) {
			for (TchannelBean tc : tchas) {
				if(tch.getPid()==tc.getId()) {
					tch.setStrPid(tc.getCname());
					break;
				}else if(tch.getPid()==0) {
					tch.setStrPid("无");
					break;
				}
			}
		}
*/		
/*		try {
			for (TchannelBean tch : tchas) {
				String strp = tcs.queryTchaByPidString(tch.getPid());
				tch.setStrPid(strp);
			}	
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/		
		//将栏目集合存储到req作用域中
		req.setAttribute("tchas", tchas);
		//转发到栏目查询页面，在查询页面显示数据
		req.getRequestDispatcher("tcha/list.jsp").forward(req,resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
