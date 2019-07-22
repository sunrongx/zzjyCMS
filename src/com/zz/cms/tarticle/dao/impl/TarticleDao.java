package com.zz.cms.tarticle.dao.impl;

import java.util.List;

import com.zz.cms.exception.SysException;
import com.zz.cms.tarticle.bean.TarticleBean;
import com.zz.cms.tchannel.bean.TchannelBean;
import com.zz.cms.user.bean.UserBean;

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
	//获取所属栏目的栏目信息
	public List<TchannelBean> queryChan();
	//分页查询所有文章的方法
	public List<TarticleBean> queryByPage(String name, int start, int size);
	//查询总页数
	public int queryPageCounts();
}
