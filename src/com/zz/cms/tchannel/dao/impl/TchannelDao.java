package com.zz.cms.tchannel.dao.impl;

import java.util.List;

import com.zz.cms.exception.SysException;
import com.zz.cms.tarticle.bean.TarticleBean;
import com.zz.cms.tchannel.bean.TchannelBean;

public interface TchannelDao {
	//一个map集合对应一个Tchannelbean
	public List<TchannelBean> cname(TchannelBean tcha) throws SysException ;
	//根据条件查询，参数为查询条件，返回的是所有栏目的集合
	public List<TchannelBean> queryByTiaoJian(String TiaoJian,Object [ ] obj);
	//查询所有栏目信息的方法
	public List<TchannelBean> queryAll();
	//栏目新增的判断方法
	public int insertTcha(TchannelBean tcha);
	//通过栏目名查询栏目
	public TchannelBean queryByCname(String cname);
	//根据栏目ID查询文章信息
	public List<TchannelBean> queryTchaById(int id) throws SysException;
	//修改栏目的方法
	public int updateTcha(TchannelBean tcha) throws SysException;
	//删除栏目的方法
	public int deleteTcha(int id) throws SysException;
	//根据上级栏目查询该栏目是否有下级栏目
	public int queryTchaByPidInt(int id) throws SysException;
	//根据上级栏目查询该栏目的上级栏目名称
	public String queryTchaByPidString(int pid) throws SysException;
	//分页查询
	public List<TchannelBean> queryByPage(String name, int start, int size);
	//查询总页数
	public int queryPageCounts();
	
}
