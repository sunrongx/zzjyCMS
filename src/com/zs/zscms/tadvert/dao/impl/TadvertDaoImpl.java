package com.zs.zscms.tadvert.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zs.zscms.tadvert.bean.TadvertBean;
import com.zs.zscms.util.DBUtil;
import com.zz.cms.exception.SysException;

public class TadvertDaoImpl implements TadvertDao {
	
	DBUtil db = new DBUtil();

	@Override
	public List<TadvertBean> title(TadvertBean tadv) throws SysException {
		// TODO 自动生成的方法存根
		
		//创建sql语句
		String sql = "select * from tadvert where title=? order by id ";
		//给sql语句的条件赋值
		Object [ ] obj = { tadv.getTitle() };
		//执行查询并返回结果
		List<Map<String, Object>> list =  db.execQuery(sql,obj);
		//创建list集合来存储user对象
		List<TadvertBean> tadvs = new ArrayList<TadvertBean>() ;
		//遍历集合，将map集合中的数据取出，然后直接封装到tadvertbean中
		for(Map<String, Object> map:list ){
			//创建tadvertbean对象
			TadvertBean tadvbean = new TadvertBean();
			//根据key来取map集合的值
			tadvbean.setId(Integer.parseInt((String) map.get("id")));
			tadvbean.setTitle((String) map.get("title"));
			tadvbean.setContent((String) map.get("content"));
			tadvbean.setCrtime((String) map.get("crtime"));
			tadvbean.setCrman((String) map.get("crman"));
			
			// 将tadvertbean对象加入集合中
			tadvs.add(tadvbean);
		}
		
		
		return tadvs;
	}

	@Override
	public List<TadvertBean> queryByTiaoJian(String sql, Object[] obj) {
		// TODO 自动生成的方法存根
		//用三元运算符与sql语句拼串，结果相当于：select * from Tadvert where 1=1 and id=?
		//String sql="select * from tadvert where 1=1"+(TiaoJian==null?"":TiaoJian);
		//创建listmap集合存储信息
		List<Map<String, Object>> list =db.execQuery(sql,obj);
		//创建集合来存储对象
		List <TadvertBean> tadvs = new ArrayList<TadvertBean>();
		
		//遍历集合，将map集合中的数据取出，然后直接封装到tadvertbean中
		for(Map<String, Object> map:list ){
			//创建tadvertbean对象
			TadvertBean tadvbean = new TadvertBean();
			//根据key来取map集合的值
			tadvbean.setId(Integer.parseInt((String) map.get("id")));
			tadvbean.setTitle((String) map.get("title"));
			tadvbean.setContent((String) map.get("content"));
			tadvbean.setCrtime((String) map.get("crtime"));
			tadvbean.setCrman((String) map.get("crman"));
			
			// 将tadvertbean对象加入集合中
			tadvs.add(tadvbean);
		}
		return tadvs;
		
	}

	@Override
	public List<TadvertBean> queryAll() {
		// TODO 自动生成的方法存根
		//查询所有信息，不需要条件和参数，所以直接给null
		String sql="select * from Tadvert";
		//返回根据sql语句查询的结果
		return queryByTiaoJian(sql,null);
		
		
	}

	@Override
	public int insertTadv(TadvertBean tadv) {
		// TODO 自动生成的方法存根
		//添加广告信息的sql语句
		String sql="insert into tadvert values(null,?,?,?,?)";
		//创建获得广告信息的Object数组
		Object [ ] obj = {tadv.getTitle(),tadv.getContent(),tadv.getCrtime(),tadv.getCrman() };
		//使用新增方法新增广告信息，将新增的结果赋值给int类型的result
		int result = db.execUpdate(sql,obj);
		//返回result
		return result;
	}

	//根据广告名查询广告的方法
	@Override
	public List<TadvertBean> queryByTitle(String title) {
		// TODO 自动生成的方法存根
		//sql语句
		String sql="select * from tadvert where title=? ";
		//将广告名赋值给数组
		Object [ ] obj = {title};
		//返回根据数组信息为条件查询到的结果
		return this.queryByTiaoJian(sql, obj);
		
		
	}
	
	//修改广告的方法
	@Override
	public int updateTadv(TadvertBean tadv) throws SysException {
		// TODO 自动生成的方法存根
		String sql = "update tadvert set title=?,content=?,crtime=?,crman=?  where id=?";
		Object [ ]  obj = { tadv.getTitle(),tadv.getContent(),tadv.getCrtime(),tadv.getCrman(),tadv.getId() };
		int result = db.execUpdate(sql,obj);
		
		return result;
		
		
	}

	@Override
	public int deleteTadv(int id) throws SysException {
		// TODO 自动生成的方法存根
		String sql = "delete from tadvert where id=?";
		Object []  obj={id};
		//创建int类型变量接收删除结果
		int result = db.execUpdate(sql,obj);
		//返回变量
		return result;
	}

	@Override
	public List<TadvertBean> queryTadvById(int id) throws SysException {
		// TODO 自动生成的方法存根
		String sql= "select * from tadvert where id=? ";
		//创建数组
		Object  [ ] obj = {id};
		//返回查询结果
		return this.queryByTiaoJian(sql, obj);

	}
	
}
