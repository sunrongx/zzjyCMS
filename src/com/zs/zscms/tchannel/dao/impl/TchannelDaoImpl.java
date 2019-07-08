package com.zs.zscms.tchannel.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zs.zscms.tchannel.bean.TchannelBean;
import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.util.DBUtil;
import com.zz.cms.exception.SysException;

public class TchannelDaoImpl implements TchannelDao {
	//创建数据库连接
	DBUtil db = new DBUtil();
	
	@Override
	//查询所有栏目信息
	public List<TchannelBean> cname(TchannelBean tcha) throws SysException {
		// TODO 自动生成的方法存根
		//根据ID查询的SQL语句
		String sql="select * from tchannel where cname=? order by id";
		//给SQL语句的参数赋值
		Object [] obj = {tcha.getCname()};
		//执行查询，返回结果
		List<Map<String, Object>> list = db.execQuery(sql,obj);
		//创建list集合来存储tcha对象
		List <TchannelBean> tchas = new ArrayList<TchannelBean> ();
		//遍历集合，将map集合中的数据取出，然后直接封装到Tchannelbean中
		for(Map<String, Object> map:list){
			//创建TchannelBean对象
			TchannelBean tchabean = new TchannelBean();
			//根据key来取map集合的值
			tchabean.setId(Integer.parseInt((String) map.get("id")));
			tchabean.setCname((String) map.get("cname"));
			tchabean.setPid(Integer.parseInt((String) map.get("pid")));
			tchabean.setLev(Integer.parseInt((String) map.get("lev")));
			if(tchabean.getLev()==1){
				tchabean.setStrlev("一级");
			}else{
				tchabean.setStrlev("二级");
			}
			tchabean.setIsleaf(Integer.parseInt((String) map.get("isleaf")));
			if(tchabean.getIsleaf()==1){
				tchabean.setStrIsleaf("是");
			}else{
				tchabean.setStrIsleaf("不是");
			}
//			tchabean.setSort(Integer.parseInt(map.get("sort")));
			
			// 将tchabean对象加入集合中
			tchas.add(tchabean);
		}
		return tchas;
		
	}
	
	@Override
	//根据条件查询栏目信息
	public List<TchannelBean> queryByTiaoJian(String sql, Object[] obj) {
		// TODO 自动生成的方法存根
		//用三元运算符与sql语句拼串，结果相当于：select * from tchannel where 1=1 and id=?
		//String sql="select * from tchannel where 1=1"+(TiaoJian==null?"":TiaoJian);
		//创建listmap集合存储信息
		List<Map<String, Object>> list =db.execQuery(sql,obj);
		//创建集合来存储对象
		List <TchannelBean> tchas = new ArrayList<TchannelBean>();
		//遍历集合，将数据从map集合中取出然后直接封装到Tchannelbean中
		for(Map<String, Object> map:list){
			//创建TchannelBean对象
			TchannelBean tchabean = new TchannelBean();
			//根据key来取map集合的值
			tchabean.setId(Integer.parseInt((String) map.get("id")));
			tchabean.setCname((String) map.get("cname"));
			tchabean.setPid(Integer.parseInt((String) map.get("pid")));
			tchabean.setLev(Integer.parseInt((String) map.get("lev")));
			if(tchabean.getLev()==1){
				tchabean.setStrlev("一级");
			}else{
				tchabean.setStrlev("二级");
			}
			tchabean.setIsleaf(Integer.parseInt((String) map.get("isleaf")));
			if(tchabean.getIsleaf()==1){
				tchabean.setStrIsleaf("是");
			}else{
				tchabean.setStrIsleaf("不是");
			}
//			tchabean.setSort(Integer.parseInt(map.get("sort")));
			
			// 将Tchannelbean对象加入集合中
			tchas.add(tchabean);
		}
		return tchas;
	}

	@Override
	//查询全部栏目信息
	public List<TchannelBean> queryAll() {
		// TODO 自动生成的方法存根
		//查询所有信息，不需要条件和参数，所以直接给null
		String sql="select * from tchannel ";
		//返回根据sql语句查询的结果
		return queryByTiaoJian(sql,null);
	}

	@Override
	//新增栏目信息
	public int insertTcha(TchannelBean tcha) {
		// TODO 自动生成的方法存根
		
		//添加用户信息的sql语句
		String sql="insert into tchannel values(null,?,?,?,?)";
		//创建获得栏目信息的Object数组
		Object [ ] obj = {tcha.getCname(),tcha.getPid(),tcha.getLev(),tcha.getIsleaf() };
		//使用新增方法新增栏目信息，将新增的结果赋值给int类型的result
		int result = db.execUpdate(sql,obj);
		//返回result
		return result;
	}
	
	@Override
	//根据ID查询栏目信息
	public List<TchannelBean> queryTchaById(int id) throws SysException {
		// TODO 自动生成的方法存根
		String sql= "select * from tchannel where id=? ";
		//创建数组
		Object  [ ] obj = {id};
		//返回查询结果
		return this.queryByTiaoJian(sql, obj);
	}

	@Override
	//修改栏目
	public int updateTcha(TchannelBean tcha) throws SysException {
		// TODO 自动生成的方法存根
		String sql = "update tchannel set cname=?,pid=?,lev=?,isleaf=?  where id=?";
		//创建数组
		Object [ ]  obj = { tcha.getCname(),tcha.getPid(),tcha.getLev(),tcha.getIsleaf(),tcha.getId() };
		//将修改结果赋值给int类型的值
		int result = db.execUpdate(sql,obj);
		//返回该结果值
		return result;
	}

	@Override
	//根据栏目名查询栏目
	public List<TchannelBean> queryByCname(String cname) {
		// TODO 自动生成的方法存根
		String sql="select * from tchannel where cname=? ";
		//将栏目名赋值给数组
		Object [ ] obj = {cname};
		//返回根据数组信息为条件查询到的结果
		return this.queryByTiaoJian(sql, obj);
	}

	@Override
	public int deleteTcha(int id) throws SysException {
		// TODO 自动生成的方法存根
		//删除用户信息
		String sql="delete from tchannel where id=?";		
		Object []  obj={id};
		//创建int类型变量接收删除结果
		int result = db.execUpdate(sql,obj);
		//返回变量
		return result;
		
	}
	
}
