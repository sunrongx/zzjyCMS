package com.zs.zscms.user.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zs.zscms.user.bean.DeptBean;
import com.zs.zscms.util.DBUtil;

public class DeptDaoImpl implements DeptDao {
	DBUtil db = new DBUtil();
	@Override
	public List<DeptBean> queryAllDepts() {
		//sql语句查询部门全表
		String sql = "select * from dept";
		//使用listmap集合接收从数据库的查询结果
		List<Map<String,String>> list = db.execQuery(sql,null);
		//创建集合存储部门信息
		List<DeptBean> depts = new ArrayList<DeptBean>();
		//遍历depts集合
		for(Map<String,String> map : list ){
			//创建DeptBean对象
			DeptBean dept = new DeptBean();
			//将数据赋值给dept
			dept.setId(Integer.parseInt(map.get("id")));
			dept.setDeptname(map.get("deptname"));
			//封装到集合中
			depts.add(dept);
			
		}
		
		
		return depts;
	}
	
}
