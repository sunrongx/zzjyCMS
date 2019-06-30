package com.zs.zscms.tarticle.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zs.zscms.exception.SysException;
import com.zs.zscms.tarticle.bean.TarticleBean;
import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.util.DBUtil;

public class TarticleDaoImpl implements TarticleDao {
	DBUtil db = new DBUtil();

	//封装
	@Override
	public List<TarticleBean> title(TarticleBean tart) throws SysException {
		// TODO 自动生成的方法存根
		
		//创建SQL语句
		String sql="select * from tarticle  where title=? order by id";
		//给SQL语句的参数赋值
		Object [] obj = {tart.getTitle()};
		//执行查询，返回结果
		List<Map<String,String>> list = db.execQuery(sql,obj);
		//创建list集合来存储tart对象
		List <TarticleBean> tarts = new ArrayList<TarticleBean> ();
		//遍历集合，将map集合中的数据取出，然后直接封装到Tarticlebean中
		for(Map<String,String> map:list){
			//创建TarticleBean对象
			TarticleBean tartbean = new TarticleBean();
			//根据key来取map集合的值
			tartbean.setId(Integer.parseInt(map.get("id")));
			tartbean.setTitle(map.get("title"));
			tartbean.setContent(map.get("content"));
			tartbean.setAuther(map.get("auther"));
			tartbean.setCrtime(map.get("crtime"));
			tartbean.setChannel(Integer.parseInt(map.get("channel")));
			tartbean.setIsremod(Integer.parseInt(map.get("isremod")));
			if(tartbean.getIsremod()==1){
				tartbean.setStrIsremod("是");
			}else{
				tartbean.setStrIsremod("不是");
			}
			tartbean.setIshot(Integer.parseInt(map.get("ishot")));
			if(tartbean.getIshot()==1){
				tartbean.setStrIshot("是");
			}else{
				tartbean.setStrIshot("不是");
			}
			
			// 将userbean对象加入集合中
			tarts.add(tartbean);
		}
		return tarts;
	}

	//根据条件查询所有文章信息
	@Override
	public List<TarticleBean> queryByTiaoJian(String sql, Object[] obj) {
		// TODO 自动生成的方法存根
		
		//用三元运算符与sql语句拼串，结果相当于：select * from tarticle where 1=1 and id=?
		//String sql="select * from tarticle where 1=1"+(TiaoJian==null?"":TiaoJian);
		//创建listmap集合存储信息
		List<Map<String,String>> list =db.execQuery(sql,obj);
		//创建集合来存储对象
		List <TarticleBean> tarts = new ArrayList<TarticleBean>();
		//遍历集合，将数据从map集合中取出然后直接封装到Tarticlebean中
		for(Map<String,String> map:list){
			//创建TarticleBean对象
			TarticleBean tartbean = new TarticleBean();
			//根据key来取map集合的值
			tartbean.setId(Integer.parseInt(map.get("id")));
			tartbean.setTitle(map.get("title"));
			tartbean.setContent(map.get("content"));
			tartbean.setAuther(map.get("auther"));
			tartbean.setCrtime(map.get("crtime"));
			tartbean.setChannel(Integer.parseInt(map.get("channel")));
			tartbean.setIsremod(Integer.parseInt(map.get("isremod")));
			if(tartbean.getIsremod()==1){
				tartbean.setStrIsremod("是");
			}else{
				tartbean.setStrIsremod("不是");
			}
			tartbean.setIshot(Integer.parseInt(map.get("ishot")));
			if(tartbean.getIshot()==1){
				tartbean.setStrIshot("是");
			}else{
				tartbean.setStrIshot("不是");
			}
			// 将Tarticlebean对象加入集合中
			tarts.add(tartbean);
		}
		return tarts;
	}
	
	//查询全部文章信息
	@Override
	public List<TarticleBean> queryAll() {
		// TODO 自动生成的方法存根
		
		//查询所有信息，不需要条件和参数，所以直接给null
		String sql="select * from tarticle";
		//返回根据sql语句查询的结果
		return queryByTiaoJian(sql,null);
	}
	
	//新增文章
	@Override
	public int insertTart(TarticleBean tart) {
		// TODO 自动生成的方法存根
		
		//添加文章信息的sql语句
		String sql="insert into tarticle values(null,?,?,?,?,?,?,?)";
		//创建获得文章信息的Object数组
		Object [ ] obj = {tart.getTitle(),tart.getContent(),tart.getAuther(),tart.getCrtime(),tart.getChannel(),tart.getIsremod(), tart.getIshot() };
		//使用新增方法新增文章信息，将新增的结果赋值给int类型的result
		int result = db.execUpdate(sql,obj);
		//返回result
		return result;
	}

	@Override
	//根据标题查询文章
	public List<TarticleBean> queryBytitle(String title) {
		// TODO 自动生成的方法存根
		String sql="select * from tarticle where title=? ";
		//将文章名赋值给数组
		Object [ ] obj = {title};
		//返回根据数组信息为条件查询到的结果
		return this.queryByTiaoJian(sql, obj);
	}

	@Override
	//根据文章作者查询文章
	public List<TarticleBean> queryByAuther(String auther) throws SysException {
		// TODO 自动生成的方法存根
		String sql="select * from tarticle where auther=? ";
		//将用户名赋值给数组
		Object [ ] obj = {auther};
		//返回根据数组信息为条件查询到的结果
		return this.queryByTiaoJian(sql, obj);
	}

	@Override
	//根据ID查询文章
	public List<TarticleBean> queryTartById(int id) throws SysException {
		// TODO 自动生成的方法存根
		String sql= "select * from tarticle where id=? ";
		//创建数组
		Object  [ ] obj = {id};
		//返回查询结果
		return this.queryByTiaoJian(sql, obj);
	}

	@Override
	//修改文章
	public int updateTart(TarticleBean tart) throws SysException {
		// TODO 自动生成的方法存根
		String sql = "update tarticle set title=?,content=?,auther=?,crtime=?,channel=?,isremod=?,ishot=?  where id=?";
		//创建数组
		Object [ ]  obj = { tart.getTitle(),tart.getContent(),tart.getAuther(),tart.getCrtime(),tart.getChannel(),tart.getIsremod(), tart.getIshot(),tart.getId() };
		//将修改结果赋值给int类型的值
		int result = db.execUpdate(sql,obj);
		//返回该结果值
		return result;
	}

	@Override
	public int deleteTart(int id) throws SysException {
		// TODO 自动生成的方法存根
		String sql="delete from tarticle where id=?";
		Object []  obj = {id};
		int result = db.execUpdate(sql, obj);
		return result;
	}

}
