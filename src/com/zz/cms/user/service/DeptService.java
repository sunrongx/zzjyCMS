package com.zz.cms.user.service;

import java.util.List;

import com.zz.cms.user.bean.DeptBean;
import com.zz.cms.user.dao.impl.DeptDao;
import com.zz.cms.user.dao.impl.DeptDaoImpl;
//部门service层
public class DeptService {
	//使用多态的特性创建DeptDao对象
	DeptDao dt = new DeptDaoImpl();
	//查询全部部门信息的方法
	public List<DeptBean> queryAll(){
		//返回查询结果
		return dt.queryAllDepts();
		
	}
	
}
