package com.zs.zscms.tadvert.dao.impl;

import java.util.List;

import com.zs.zscms.exception.SysException;
import com.zs.zscms.tadvert.bean.TadvertBean;
import com.zs.zscms.user.bean.UserBean;

public interface TadvertDao {
	//一个map集合对应一个Tadvertbean
	public List<TadvertBean> title(TadvertBean tadv) throws SysException ;
	//根据条件查询，参数为查询条件，返回的是所有广告的集合
	public List<TadvertBean> queryByTiaoJian(String TiaoJian,Object [] obj);
	//查询所有广告
	public List<TadvertBean> queryAll();
	//广告新增的判断方法
	public int insertTadv(TadvertBean tadv);
	//根据广告名查询广告信息
	public List<TadvertBean> queryByTitle(String title);
	//修改广告的方法
	public int updateTadv(TadvertBean tadv) throws SysException;
	//删除广告的方法
	public int deleteTadv(int id) throws SysException;
	//根据ID查询信息
	public List<TadvertBean> queryTadvById(int id) throws SysException;
	
}
