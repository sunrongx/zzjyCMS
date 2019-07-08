package com.zs.zscms.tchannel.service;

import java.util.List;

import com.zs.zscms.tchannel.bean.TchannelBean;
import com.zs.zscms.tchannel.dao.impl.TchannelDao;
import com.zs.zscms.tchannel.dao.impl.TchannelDaoImpl;
import com.zz.cms.exception.BusinessException;
import com.zz.cms.exception.SysException;

public class TchannelService {
	//采用多态形式创建TchannelDao对象
	TchannelDao tcd = new TchannelDaoImpl();
	//判断是否有对应的用户信息，有返回信息，没有返回null
	public TchannelBean tchan(TchannelBean tcha) throws SysException, BusinessException {
		//将UserDaoImpl中login方法的user集合赋值给users集合
		List<TchannelBean> tchas = tcd.cname(tcha);
		//判空
		if(tchas==null || tchas.size()==0){
			//输入商业异常信息
			throw new BusinessException("该栏目信息有误，请重新输入！", 400);
		}
		//返回栏目信息集合中的第一个值
		return tchas.get(0);
	}
	
	//创建查询所有栏目信息的方法，否则从servlet直接调用TchannelDaoImpl的方法的话，不符合MVC思想
	public List<TchannelBean> queryAll(){
		return tcd.queryAll();
	}
	
	//新增栏目
	public int insertTcha(TchannelBean tcha) throws BusinessException{
		//从新增的栏目信息集合中查询栏目名并将查询结果赋值给集合
		List<TchannelBean> tchas1 = tcd.queryByCname(tcha.getCname());
		//判断栏目名是否重复，如果查询结果不为空且有值，说明栏目名重复，抛出商业异常
		if(tchas1!=null&&tchas1.size()!=0){
			//将异常信息放到页面上，不重复执行结果，1成功，0失败
			throw new BusinessException("该栏目名已存在，请重新输入！", 401);
		}
		
		//通过时说明不重复，可以新增
		return tcd.insertTcha(tcha);
	}
	
	//根据ID查询栏目信息
	public TchannelBean queryTchaById(int id) throws SysException{
		//将以id为条件的查询结果赋值给集合
		List<TchannelBean>tchas = tcd.queryTchaById(id);
		//返回第一条（唯一一条）信息
		return tchas.get(0);
	}
	
	//修改栏目信息
	public int updateTcha(TchannelBean tcha) throws SysException{
		//将修改信息的结果赋值给tcha1
		int tcha1 = tcd.updateTcha(tcha);
		//返回tcha1
		return tcha1;	
	}
	
	//删除用户信息
	public int deleteTcha(int id) throws SysException{
		int tcha = tcd.deleteTcha(id);
		return tcha;
		
	}
		
}
