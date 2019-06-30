package com.zs.zscms.user.dao.impl;

import java.util.List;

import com.zs.zscms.user.bean.DeptBean;
//部门Dao层接口
public interface DeptDao {
	//查询全部部门信息的方法
	public List<DeptBean> queryAllDepts();
}
