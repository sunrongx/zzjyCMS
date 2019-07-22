package com.zz.cms.tfastreport.service;

import java.util.List;

import com.zz.cms.exception.BusinessException;
import com.zz.cms.exception.SysException;
import com.zz.cms.tarticle.bean.TarticleBean;
import com.zz.cms.tfastreport.bean.TfastreportBean;
import com.zz.cms.tfastreport.dao.impl.TfastreportDao;
import com.zz.cms.tfastreport.dao.impl.TfastreportDaoImpl;
import com.zz.cms.user.bean.UserBean;

public class TfastreportService {
	//采用多态形式创建TfastreportDao对象
	TfastreportDao td = new TfastreportDaoImpl();
	//判断是否有对应的广告信息，有返回信息，没有返回null
	public TfastreportBean tfastitle(TfastreportBean tfas) throws SysException, BusinessException {
		//将TfastreportDaoImpl中的集合赋值给tfass集合
		List<TfastreportBean> tfass = td.title(tfas);
		//判空
		if(tfass==null || tfass.size()==0){
			//输入商业异常信息
			throw new BusinessException("该广告信息有误，请重新输入！", 400);
		}
		//返回广告信息集合中的第一个值
		return tfass.get(0);
	}
	
	//创建查询所有广告信息的方法，否则从servlet直接调用TfastreportDaoImpl的方法的话，不符合MVC思想
	public List<TfastreportBean> queryAll(){
		return td.queryAll();
	}
	
	//新增广告
	public int insertTfastreport(TfastreportBean tfas) throws BusinessException{
		//从新增的广告信息集合中查询广告并将查询结果赋值给集合
		List<TfastreportBean> tfass1 = td.queryByTitle(tfas.getTitle());
		//判断广告名是否重复，如果查询结果不为空且有值，说明广告名重复，抛出商业异常
		if(tfass1!=null&&tfass1.size()!=0){
			//将异常信息放到页面上，不重复执行结果，1成功，0失败
			throw new BusinessException("该广告名已存在，请重新输入！", 201);
		}
		//通过时说明不重复，可以新增
		return td.insertTfas(tfas);
	}
	
	//根据ID查询广告信息
	public TfastreportBean queryTfasById(int id) throws SysException{
		//将以id为条件的查询结果赋值给集合
		List<TfastreportBean> tfass = td.queryTfasById(id);
		//返回第一条（唯一一条）信息
		return tfass.get(0);
	}
	
	//修改广告信息
	public int updateTfas(TfastreportBean tfas) throws SysException{
		//将修改信息的结果赋值给tfas1
		int tfas1 = td.updateTfas(tfas);
		//返回tfas1
		return tfas1;
		
	}
	
	//删除广告信息
	public int deleteTfas(int id) throws SysException{
		int tfas= td.deleteTfas(id);
		return tfas;
	}
	
	//分页查询所有文章
	public List<TfastreportBean> queryByPage(String name,int page,int size){
		int start = (page-1)*size;
		return td.queryByPage(name, start, size);
	}
	
	//查询总页数
	public int queryPageCounts(int size) {
		//把文章记录条数转换为总页数
		int tfasCounts = td.queryPageCounts();
		if(tfasCounts%size==0){
			//返回总数能整除每页数量的页数
			return tfasCounts/size;
		}
		//返回总数无法整除每页数量的页数
		return tfasCounts/size+1;
	}
}
