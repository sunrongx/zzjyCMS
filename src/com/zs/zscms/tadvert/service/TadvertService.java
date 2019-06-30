package com.zs.zscms.tadvert.service;

import java.util.List;

import com.zs.zscms.exception.BusinessException;
import com.zs.zscms.exception.SysException;
import com.zs.zscms.tadvert.bean.TadvertBean;
import com.zs.zscms.tadvert.dao.impl.TadvertDao;
import com.zs.zscms.tadvert.dao.impl.TadvertDaoImpl;
import com.zs.zscms.user.bean.UserBean;

public class TadvertService {
	//采用多态形式创建TadvertDao对象
		TadvertDao td = new TadvertDaoImpl();
		//判断是否有对应的广告信息，有返回信息，没有返回null
		public TadvertBean tadvtitle(TadvertBean tadv) throws SysException, BusinessException {
			//将TadvertDaoImpl中的集合赋值给tadvs集合
			List<TadvertBean> tadvs = td.title(tadv);
			//判空
			if(tadvs==null || tadvs.size()==0){
				//输入商业异常信息
				throw new BusinessException("该广告信息有误，请重新输入！", 400);
			}
			//返回广告信息集合中的第一个值
			return tadvs.get(0);
		}
		
		//创建查询所有广告信息的方法，否则从servlet直接调用TadvertDaoImpl的方法的话，不符合MVC思想
		public List<TadvertBean> queryAll(){
			return td.queryAll();
		}
		
		//新增广告
		public int insertTadvert(TadvertBean tadv) throws BusinessException{
			//从新增的广告信息集合中查询广告并将查询结果赋值给集合
			List<TadvertBean> tadvs1 = td.queryByTitle(tadv.getTitle());
			//判断广告名是否重复，如果查询结果不为空且有值，说明广告名重复，抛出商业异常
			if(tadvs1!=null&&tadvs1.size()!=0){
				//将异常信息放到页面上，不重复执行结果，1成功，0失败
				throw new BusinessException("该广告名已存在，请重新输入！", 201);
			}
			//通过时说明不重复，可以新增
			return td.insertTadv(tadv);
		}
		
		//根据ID查询广告信息
		public TadvertBean queryTadvById(int id) throws SysException{
			//将以id为条件的查询结果赋值给集合
			List<TadvertBean> tadvs = td.queryTadvById(id);
			//返回第一条（唯一一条）信息
			return tadvs.get(0);
		}
		
		//修改广告信息
		public int updateTadv(TadvertBean tadv) throws SysException{
			//将修改信息的结果赋值给tadv1
			int tadv1 = td.updateTadv(tadv);
			//返回tadv1
			return tadv1;
			
		}
		
		//删除广告信息
		public int deleteTadv(int id) throws SysException{
			int tadv= td.deleteTadv(id);
			return tadv;
		}
}
