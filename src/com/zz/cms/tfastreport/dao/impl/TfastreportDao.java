package com.zz.cms.tfastreport.dao.impl;

import java.util.List;

import com.zz.cms.exception.SysException;
import com.zz.cms.tfastreport.bean.TfastreportBean;
import com.zz.cms.user.bean.UserBean;

public interface TfastreportDao {
	//一个map集合对应一个Tfastre[portbean
	public List<TfastreportBean> title(TfastreportBean tfas) throws SysException ;
	//根据条件查询，参数为查询条件，返回的是所有广告的集合
	public List<TfastreportBean> queryByTiaoJian(String TiaoJian,Object [] obj);
	//查询所有广告
	public List<TfastreportBean> queryAll();
	//广告新增的判断方法
	public int insertTfas(TfastreportBean tadv);
	//根据广告名查询广告信息
	public TfastreportBean queryByTitle(String title);
	//修改广告的方法
	public int updateTfas(TfastreportBean tadv) throws SysException;
	//删除广告的方法
	public int deleteTfas(int id) throws SysException;
	//根据ID查询信息
	public List<TfastreportBean> queryTfasById(int id) throws SysException;
	//分页查询
	public List<TfastreportBean> queryByPage(String name, int start, int size);
	//查询总页数
	public int queryPageCounts();
	
}
