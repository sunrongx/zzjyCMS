package com.zz.cms.user.dao.impl;

import java.util.List;

import com.zz.cms.user.bean.DeptBean;
//部门Dao层接口
public interface DeptDao {
	//查询全部部门信息的方法
	public List<DeptBean> queryAllDepts();
}
