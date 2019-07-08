package com.zs.zscms.tarticle.service;

import java.util.List;

import com.zs.zscms.tarticle.bean.TarticleBean;
import com.zs.zscms.tarticle.dao.impl.TarticleDao;
import com.zs.zscms.tarticle.dao.impl.TarticleDaoImpl;
import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.user.dao.impl.UserDao;
import com.zs.zscms.user.dao.impl.UserDaoImpl;
import com.zz.cms.exception.BusinessException;
import com.zz.cms.exception.SysException;

public class TarticleService {
	//采用多态形式创建TarticleDao对象
	TarticleDao tard = new TarticleDaoImpl();
	//判断是否有对应的文章信息，有返回信息，没有返回null
	public TarticleBean tartTitle(TarticleBean tart) throws SysException, BusinessException {
		//将TarticleDaoImpl中title方法的tart集合赋值给tarts集合
		List<TarticleBean> tarts = tard.title(tart);
		//判空
		if(tarts==null || tarts.size()==0){
			//输入商业异常信息
			throw new BusinessException("该文章信息有误，请重新输入！", 400);
		}
		//返回文章信息集合中的第一个值
		return tarts.get(0);
	}
	
	//创建查询所有文章信息的方法，否则从servlet直接调用	TarticleDaoImpl的方法的话，不符合MVC思想
	public List<TarticleBean> queryAll(){
		return tard.queryAll();
	}
	
	//新增文章
	public int insertTart(TarticleBean tart) throws BusinessException{
		//从新增的文章信息集合中查询文章名并将查询结果赋值给集合
		List<TarticleBean> tarts1 = tard.queryBytitle(tart.getTitle());
		//判断文章名是否重复，如果查询结果不为空且有值，说明文章名重复，抛出商业异常
		if(tarts1!=null&&tarts1.size()!=0){
			//将异常信息放到页面上，不重复执行结果，1成功，0失败
			throw new BusinessException("该文章名已存在，请重新输入！", 301);
		}
		//通过时说明不重复，可以新增
		return tard.insertTart(tart);
	}
	
	//根据ID查询文章信息
	public TarticleBean queryTartById(int id) throws SysException{
		//将以id为条件的查询结果赋值给集合
		List<TarticleBean> tarts = tard.queryTartById(id);
		//返回第一条（唯一一条）信息
		return tarts.get(0);
	}
	
	//修改文章信息
	public int updateTart(TarticleBean tart) throws SysException{
		//将修改信息的结果赋值给tart1
		int tart1 = tard.updateTart(tart);
		//返回tart1
		return tart1;
		
	}
	
	//删除广告信息
		public int deleteTart(int id) throws SysException{
		int tart= tard.deleteTart(id);
		return tart;
		
	}
}
