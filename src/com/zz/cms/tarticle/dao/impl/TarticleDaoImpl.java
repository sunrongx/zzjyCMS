package com.zz.cms.tarticle.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zz.cms.exception.SysException;
import com.zz.cms.tarticle.bean.TarticleBean;
import com.zz.cms.tchannel.bean.TchannelBean;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.util.DBUtil;

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
		List<Map<String, Object>> list = db.execQuery(sql,obj);
		//创建list集合来存储tart对象
		List <TarticleBean> tarts = new ArrayList<TarticleBean> ();
		//遍历集合，将map集合中的数据取出，然后直接封装到Tarticlebean中
		for(Map<String, Object> map:list){
			//创建TarticleBean对象
			TarticleBean tartbean = new TarticleBean();
			//根据key来取map集合的值
			tartbean.setId(Integer.parseInt((String) map.get("id")));
			tartbean.setTitle((String) map.get("title"));
			tartbean.setContent((String) map.get("content"));
			tartbean.setAuther((String) map.get("auther"));
			tartbean.setCtime((String) map.get("ctime"));
			tartbean.setChannel(Integer.parseInt((String) map.get("channel")));
			tartbean.setIsremod(Integer.parseInt((String) map.get("isremod")));
			if(tartbean.getIsremod()==1){
				tartbean.setStrIsremod("是");
			}else{
				tartbean.setStrIsremod("不是");
			}
			tartbean.setIshot(Integer.parseInt((String) map.get("ishot")));
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
		List<Map<String, Object>> list =db.execQuery(sql,obj);
		//创建集合来存储对象
		List <TarticleBean> tarts = new ArrayList<TarticleBean>();
		//遍历集合，将数据从map集合中取出然后直接封装到Tarticlebean中
		for(Map<String, Object> map:list){
			//创建TarticleBean对象
			TarticleBean tartbean = new TarticleBean();
			//根据key来取map集合的值
			tartbean.setId(Integer.parseInt((String) map.get("id")));
			tartbean.setTitle((String) map.get("title"));
			tartbean.setContent((String) map.get("content"));
			tartbean.setAuther((String) map.get("author"));
			tartbean.setCtime((String) map.get("ctime"));
			int tch = Integer.parseInt((String) map.get("channel"));
			tartbean.setChannel(tch);
/*			for(int i = 0;i<queryChan().size();i++) {
				if(tch==queryChan().get(i).getId()) {
					tartbean.setStrChannel(queryChan().get(i).getCname());
					break;
				}else {
					tartbean.setStrChannel("无所属栏目");
				}
			}
*/			
			String strCha = String.valueOf(map.get("sname")).trim();
			if(strCha==null||strCha.equals("null")) {
				tartbean.setStrChannel("无所属栏目");
			}else {
				tartbean.setStrChannel(strCha);
			}
			
			tartbean.setIsremod(Integer.parseInt((String) map.get("isremod")));
			if(tartbean.getIsremod()==1){
				tartbean.setStrIsremod("是");
			}else{
				tartbean.setStrIsremod("不是");
			}
			tartbean.setIshot(Integer.parseInt((String) map.get("ishot")));
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
		//String sql="select * from tarticle";
		String sql = "select t.*,c.cname sname from tarticle t left join tchannel c on t.channel=c.id;";
		//返回根据sql语句查询的结果
		return queryByTiaoJian(sql,null);
	}
	
	//新增文章
	@Override
	public int insertTart(TarticleBean tart) {
		// TODO 自动生成的方法存根
		
		//添加文章信息的sql语句
		String sql="insert into tarticle values(null,?,?,?,now(),?,?,?)";
		//创建获得文章信息的Object数组
		Object [ ] obj = {tart.getTitle(),tart.getContent(),tart.getAuther(),tart.getChannel(),tart.getIsremod(), tart.getIshot() };
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
		String sql = "update tarticle set title=?,content=?,author=?,ctime=now(),channel=?,isremod=?,ishot=?  where id=?";
		//创建数组
		Object [ ]  obj = { tart.getTitle(),tart.getContent(),tart.getAuther(),tart.getChannel(),tart.getIsremod(), tart.getIshot(),tart.getId() };
		//将修改结果赋值给int类型的值
		int result = db.execUpdate(sql,obj);
		//返回该结果值
		return result;
	}

	/**
	 * 删除文章
	 */
	@Override
	public int deleteTart(int id) throws SysException {
		// TODO 自动生成的方法存根
		String sql="delete from tarticle where id=?";
		Object []  obj = {id};
		int result = db.execUpdate(sql, obj);
		return result;
	}

	/**
	 * 查询栏目
	 */
	@Override
	public List<TchannelBean> queryChan() {
		// TODO Auto-generated method stub
		String sql = "select * from tchannel;";
		Object [] objs= {};
		List<Map<String , Object>> list = db.execQuery(sql, objs);
		List<TchannelBean> tchas = new ArrayList<>();
		for (Map<String, Object> map : list) {
			TchannelBean tch = new TchannelBean();
			tch.setId(Integer.parseInt(String.valueOf(map.get("id"))));
			tch.setCname(String.valueOf(map.get("cname")));
			tchas.add(tch);
			
		}
		return tchas;
		
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public List<TarticleBean> queryByPage(String name, int start, int size) {
		// TODO Auto-generated method stub
		//查询所有，不需要条件和参数，那么直接给null
		//左外连接
		String sql="select t.*,c.cname sname from tarticle t left join tchannel c on t.channel=c.id where title like ? order by t.id desc limit ?,?";
		Object[] obj={"%"+name+"%",start,size};
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
		String sql = "select count(id) count from tarticle";
		List<Map<String, Object>> list = db.execQuery(sql,null);
		//获取count的值
		int tartCounts = Integer.parseInt((String) list.get(0).get("count"));
		
		return tartCounts;
	}

}
