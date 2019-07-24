package com.zz.cms.tfastreport.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zz.cms.exception.SysException;
import com.zz.cms.tarticle.bean.TarticleBean;
import com.zz.cms.tfastreport.bean.TfastreportBean;
import com.zz.cms.util.DBUtil;

public class TfastreportDaoImpl implements TfastreportDao {
	
	DBUtil db = new DBUtil();

	/**
	 * 根据标题查询快报
	 */
	@Override
	public List<TfastreportBean> title(TfastreportBean tfas) throws SysException {
		// TODO 自动生成的方法存根
		
		//创建sql语句
		String sql = "select * from tfastreport where title=? order by id ";
		//给sql语句的条件赋值
		Object [ ] obj = { tfas.getTitle() };
		//执行查询并返回结果
		List<Map<String, Object>> list =  db.execQuery(sql,obj);
		//创建list集合来存储user对象
		List<TfastreportBean> tfass = new ArrayList<TfastreportBean>() ;
		//遍历集合，将map集合中的数据取出，然后直接封装到tfastreportbean中
		for(Map<String, Object> map:list ){
			//创建tfastreportbean对象
			TfastreportBean tfasbean = new TfastreportBean();
			//根据key来取map集合的值
			tfasbean.setId(Integer.parseInt((String) map.get("id")));
			//赋值标题
			tfasbean.setTitle((String) map.get("title"));
			//赋值内容
			tfasbean.setContent((String) map.get("content"));
			//赋值创建时间
			tfasbean.setCtime((String) map.get("ctime"));
			
			// 将tfastreportbean加入集合中
			tfass.add(tfasbean);
		}
		
		//返回集合
		return tfass;
	}

	/**
	 * 根据条件查询快报
	 */
	@Override
	public List<TfastreportBean> queryByTiaoJian(String sql, Object[] obj) {
		// TODO 自动生成的方法存根
		//用三元运算符与sql语句拼串，结果相当于：select * from tfastreport where 1=1 and id=?
		//String sql="select * from tfastreport where 1=1"+(TiaoJian==null?"":TiaoJian);
		//创建listmap集合存储信息
		List<Map<String, Object>> list =db.execQuery(sql,obj);
		//创建集合来存储对象
		List <TfastreportBean> tfass = new ArrayList<TfastreportBean>();
		
		//遍历集合，将map集合中的数据取出，然后直接封装到tfastreportbean中
		for(Map<String, Object> map:list ){
			//创建tfastreportbean对象
			TfastreportBean tfasbean = new TfastreportBean();
			//根据key来取map集合的值
			tfasbean.setId(Integer.parseInt((String) map.get("id")));
			//赋值标题
			tfasbean.setTitle((String) map.get("title"));
			//赋值内容
			tfasbean.setContent((String) map.get("content"));
			//赋值创建时间
			tfasbean.setCtime((String) map.get("ctime"));
			
			// 将tfastreportbean对象加入集合中
			tfass.add(tfasbean);
		}
		//返回集合
		return tfass;
		
	}

	/**
	 * 查询所有快报
	 */
	@Override
	public List<TfastreportBean> queryAll() {
		// TODO 自动生成的方法存根
		//查询所有信息，不需要条件和参数，所以直接给null
		String sql="select * from Tfastreport";
		//返回根据sql语句查询的结果
		return queryByTiaoJian(sql,null);
		
		
	}

	/**
	 * 添加快报
	 */
	@Override
	public int insertTfas(TfastreportBean tfas) {
		// TODO 自动生成的方法存根
		//添加广告信息的sql语句
		String sql="insert into tfastreport values(null,?,?,now())";
		//创建获得广告信息的Object数组
		Object [ ] obj = {tfas.getTitle(),tfas.getContent()};
		//使用新增方法新增广告信息，将新增的结果赋值给int类型的result
		int result = db.execUpdate(sql,obj);
		//返回result
		return result;
	}

	/**
	 * 根据广告名查询广告的方法
	 */
	@Override
	public int queryByTitle(String title) {
		// TODO 自动生成的方法存根
		//sql语句
		String sql="select * from tfastreport where title=?";
		//将广告名赋值给数组
		Object [] obj = {title};
		//返回根据数组信息为条件查询到的结果
		List<TfastreportBean> tfas = this.queryByTiaoJian(sql, obj);
		if(tfas==null||tfas.size()==0) {
			return 0;
		}else{
			return 1;
		}
		
		
	}
	
	/**
	 * 修改广告的方法
	 */
	@Override
	public int updateTfas(TfastreportBean tfas) throws SysException {
		// TODO 自动生成的方法存根
		String sql = "update tfastreport set title=?,content=?,ctime=now() where id=?";
		//参数赋值
		Object [ ]  obj = { tfas.getTitle(),tfas.getContent(),tfas.getId() };
		//查询结果获取
		int result = db.execUpdate(sql,obj);
		//返回结果数字
		return result;
		
		
	}

	/**
	 * 删除快报的方法
	 */
	@Override
	public int deleteTfas(int id) throws SysException {
		// TODO 自动生成的方法存根
		String sql = "delete from tfastreport where id=?";
		//添加参数
		Object []  obj={id};
		//创建int类型变量接收删除结果
		int result = db.execUpdate(sql,obj);
		//返回变量
		return result;
	}

	/**
	 * 根据id查询快报
	 */
	@Override
	public List<TfastreportBean> queryTfasById(int id) throws SysException {
		// TODO 自动生成的方法存根
		String sql= "select * from tfastreport where id=? ";
		//创建数组
		Object  [ ] obj = {id};
		//返回查询结果
		return this.queryByTiaoJian(sql, obj);

	}
	
	/**
	 * 分页查询
	 */
	@Override
	public List<TfastreportBean> queryByPage(String name, int start, int size) {
		// TODO Auto-generated method stub
		//查询所有，不需要条件和参数，那么直接给null
		//左外连接，将name作为参数进行模糊查询
		String sql="select * from Tfastreport where title like ? order by id desc limit ?,?";
		//添加参数
		Object[] obj={"%"+name+"%",start,size};
		//返回查询结果
		return queryByTiaoJian(sql, obj);
	}

	/**
	 * 查询总页数
	 * @return
	 */
	@Override
	public int queryPageCounts() {
		// TODO Auto-generated method stub
		//通过count方法获取总行数并命名为count
		String sql = "select count(id) count from tfastreport";
		//将查询结果赋值集合
		List<Map<String, Object>> list = db.execQuery(sql,null);
		//获取count的值
		int tfasCounts = Integer.parseInt((String) list.get(0).get("count"));
		//返回总页数
		return tfasCounts;
	}
	
}
