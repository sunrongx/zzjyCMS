package com.zs.zscms.tarticle.dao.impl;

import java.util.List;

import com.zs.zscms.tarticle.bean.TarticleBean;
import com.zs.zscms.user.bean.UserBean;
import com.zz.cms.exception.SysException;

public interface TarticleDao {
	//一个map集合对应一个Tarticlebean
	public List<TarticleBean> title(TarticleBean user) throws SysException ;
	//根据条件查询，参数为查询条件，返回的是所有文章的集合
	public List<TarticleBean> queryByTiaoJian(String TiaoJian,Object [ ] obj);
	//查询所有文章信息的方法
	public List<TarticleBean> queryAll();
	//文章新增的判断方法
	public int insertTart(TarticleBean tart);
	//根据文章名查询文章信息
	public List<TarticleBean> queryBytitle(String title);
	//根据作者查询文章信息
	public List<TarticleBean> queryByAuther(String auther ) throws SysException;
	//根据文章ID查询文章信息
	public List<TarticleBean> queryTartById(int id) throws SysException;
	//修改文章的方法
	public int updateTart(TarticleBean tart) throws SysException;
	//删除文章的方法
	public int deleteTart(int id) throws SysException;
	
}
