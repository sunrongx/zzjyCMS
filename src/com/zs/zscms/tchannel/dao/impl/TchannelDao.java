package com.zs.zscms.tchannel.dao.impl;

import java.util.List;

import com.zs.zscms.exception.SysException;
import com.zs.zscms.tarticle.bean.TarticleBean;
import com.zs.zscms.tchannel.bean.TchannelBean;

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
	public List<TchannelBean> queryByCname(String cname);
	//根据文章ID查询文章信息
	public List<TchannelBean> queryTchaById(int id) throws SysException;
	//修改文章的方法
	public int updateTcha(TchannelBean tcha) throws SysException;
	//删除文章的方法
	public int deleteTcha(int id) throws SysException;
	
}
