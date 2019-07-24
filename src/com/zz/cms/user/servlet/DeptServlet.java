package com.zz.cms.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.user.bean.DeptBean;
import com.zz.cms.user.service.DeptService;

public class DeptServlet extends HttpServlet {
	/**
	 * 串行ID
	 */
	private static final long serialVersionUID = -8441435923121241726L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建逻辑层对象
		DeptService ds = new DeptService();
		//将查询结果赋值给部门信息集合
		List<DeptBean> depts = ds.queryAll();
		//将信息放入req对象中，转发到add.jsp中
		//测试二级联动
		/*int deplength1 = 0;
		int deplength2 = 0;
		for (int i = 0;i<depts.size();i++) {
			if(depts.get(i).getId()<=9) {
				deplength1++;
			}else if(depts.get(i).getId()>((i+1)*10)&&depts.get(i).getId()<((i+2)*10)) {
				deplength2++;
			}
		}
		req.setAttribute("deplength1", deplength1);
		req.setAttribute("deplength2", deplength2);
		*/
		req.setAttribute("depts",depts);
		//转发至网页
		req.getRequestDispatcher("user/add.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
