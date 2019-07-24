package com.zz.cms.tchannel.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zz.cms.exception.SysException;
import com.zz.cms.tarticle.bean.TarticleBean;
import com.zz.cms.tchannel.bean.TchannelBean;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.util.DBUtil;

public class TchannelDaoImpl implements TchannelDao {
	//创建数据库连接
	DBUtil db = new DBUtil();
	
	@Override
	/**
	 * 根据栏目名称查询栏目信息
	 */
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
			//赋值栏目名称
			tchabean.setCname((String) map.get("cname"));
			//赋值上级栏目id
			tchabean.setPid(Integer.parseInt(String.valueOf(map.get("pid"))));
			//赋值栏目等级
			tchabean.setLev(Integer.parseInt((String) map.get("lev")));
			//将栏目等级转为文字
			if(tchabean.getLev()==1){
				tchabean.setStrlev("一级");
			}else{
				tchabean.setStrlev("二级");
			}
			//赋值是否叶子
			tchabean.setIsleaf(Integer.parseInt((String) map.get("isleaf")));
			//将是否叶子换成文字
			if(tchabean.getIsleaf()==1){
				tchabean.setStrIsleaf("是");
			}else{
				tchabean.setStrIsleaf("不是");
			}
			//赋值栏目顺序
			tchabean.setSort(Integer.parseInt(String.valueOf(map.get("sort"))));
			
			//将tchabean对象加入集合中
			tchas.add(tchabean);
		}
		//返回TchannelBean集合
		return tchas;
		
	}
	
	@Override
	/**
	 * 根据条件查询栏目信息
	 */
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
			int id = Integer.parseInt((String) map.get("id"));
			//赋值id
			tchabean.setId(id);
			//赋值栏目名称
			tchabean.setCname((String) map.get("cname"));
			//获取数据库上级栏目
			int pid = Integer.parseInt((String) map.get("pid"));
			//赋值上级栏目
			tchabean.setPid(pid);
			//根据查询别名获取上级栏目名称来赋值
			if(map.get("pname")!=null) {
				tchabean.setPname(String.valueOf(map.get("pname")));
			}else {
				tchabean.setPname("无上级栏目");
			}
			
/*			for (TchannelBean tcha : queryAll()) {
				if(pid==tcha.getId()) {
					tchabean.setStrPid(tcha.getCname());
				}
			}
			tchabean.setStrPid("无");
*/
			//赋值栏目等级
			tchabean.setLev(Integer.parseInt(String.valueOf(map.get("lev"))));
			//将栏目等级换成文字
			if(tchabean.getLev()==1){
				tchabean.setStrlev("一级");
			}else{
				tchabean.setStrlev("二级");
			}
			//赋值是否叶子
			tchabean.setIsleaf(Integer.parseInt((String) map.get("isleaf")));
/*			if(tchabean.getIsleaf()==1){
				tchabean.setStrIsleaf("是");
			}else{
				tchabean.setStrIsleaf("不是");
			}
*/			
			try {
				//根据id查询上级栏目，查到赋值是叶子
				if(queryTchaByPidInt(id)==1) {
					tchabean.setStrIsleaf("是");
				}else {
					//查不到赋值不是叶子
					tchabean.setStrIsleaf("不是");
				}
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SysException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//赋值栏目顺序
			tchabean.setSort(Integer.parseInt(String.valueOf(map.get("sort"))));
			// 将Tchannelbean对象加入集合中
			tchas.add(tchabean);
		}
		//返回TchannelBean集合
		return tchas;
	}

	@Override
	/**
	 * 查询全部栏目信息
	 */
	public List<TchannelBean> queryAll() {
		// TODO 自动生成的方法存根
		//查询所有信息，不需要条件和参数，所以直接给null
		//String sql="select * from tchannel ";
		String sql = "select t.*,c.cname pname from tchannel t left join tchannel c on t.pid=c.id;";
		//查询并赋值给集合
		List<Map<String , Object>> list = db.execQuery(sql, null);
		//System.out.println(String.valueOf(list.get(0).get("pname")));
		//返回根据sql语句查询的结果
		//查询并返回
		return queryByTiaoJian(sql,null);
	}

	@Override
	/**
	 * 新增栏目信息
	 */
	public int insertTcha(TchannelBean tcha) {
		// TODO 自动生成的方法存根
		
		//添加用户信息的sql语句
		String sql="insert into tchannel values(null,?,?,?,?,?)";
		//创建获得栏目信息的Object数组
		Object [ ] obj = {tcha.getCname(),tcha.getPid(),tcha.getLev(),tcha.getIsleaf(),tcha.getSort() };
		//使用新增方法新增栏目信息，将新增的结果赋值给int类型的result
		int result = db.execUpdate(sql,obj);
		//返回result
		return result;
	}
	
	@Override
	/**
	 * 根据ID查询栏目信息
	 */
	public List<TchannelBean> queryTchaById(int id) throws SysException {
		// TODO 自动生成的方法存根
		//String sql= "select * from tchannel where id=? ";
		String sql = "select t.*,c.cname pname from tchannel t left join tchannel c on t.pid=c.id where t.id=?;";
		//创建数组
		Object  [ ] obj = {id};
		//返回查询结果
		return this.queryByTiaoJian(sql, obj);
	}

	@Override
	/**
	 * 修改栏目
	 */
	public int updateTcha(TchannelBean tcha) throws SysException {
		// TODO 自动生成的方法存根
		String sql = "update tchannel set cname=?,pid=?,lev=?,isleaf=?,sort=?  where id=?";
		//创建数组
		Object [ ]  obj = { tcha.getCname(),tcha.getPid(),tcha.getLev(),tcha.getIsleaf(),tcha.getSort(),tcha.getId() };
		//将修改结果赋值给int类型的值
		int result = db.execUpdate(sql,obj);
		//返回该结果值
		return result;
	}

	@Override
	/**
	 * 根据栏目名查询栏目
	 */
	public int queryByCname(String cname) {
		// TODO 自动生成的方法存根
		String sql="select * from tchannel where cname=? ";
		//将栏目名赋值给数组
		Object [ ] obj = {cname};
		//返回根据数组信息为条件查询到的结果
		List<Map<String , Object>> tchas = db.execQuery(sql, obj);
		if(tchas==null||tchas.size()==0) {
			return 0;
		}else {
			return 1;
		}
		
	}

	@Override
	/**
	 * 删除栏目
	 */
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

	/**
	 * 根据id查询上级栏目是否存在
	 */
	@Override
	public int queryTchaByPidInt(int id) throws SysException {
		// TODO Auto-generated method stub
		
		String sql="select * from tchannel where pid=? ";
		//将栏目名赋值给数组
		Object [ ] obj = {id};
		//返回根据数组信息为条件查询到的结果
		List<Map<String , Object>> list = db.execQuery(sql, obj);
		//查到东西时
		if(list==null||list.size()==0) {
			return 1;
		}else {
			return 2;
		}
		
	}
	
	/**
	 * 根据pid查询是否有上级栏目并将上级栏目名称返回
	 */
	@Override
	public String queryTchaByPidString(int pid) throws SysException {
		// TODO Auto-generated method stub
		
		String sql="select * from tchannel where id=? ";
		//将栏目名赋值给数组
		Object [ ] obj = {pid};
		//返回根据数组信息为条件查询到的结果
		List<Map<String , Object>> list = db.execQuery(sql, obj);
		//查到东西时将查到的栏目名称返回
		if(list!=null&&list.size()!=0) {
			return String.valueOf(list.get(0).get("cname"));
		}else {
			//否则返回无
			return "无";
		}
		
		
		
	}

	/**
	 * 分页查询
	 */
	@Override
	public List<TchannelBean> queryByPage(String name, int start, int size) {
		// TODO Auto-generated method stub
		//查询所有，不需要条件和参数，那么直接给null
		//左外连接
		//String sql="select t.*,c.cname sname from tarticle t left join tchannel c on t.channel=c.id where title like ? order by t.id desc limit ?,?";
		String sql = "select t.*,c.cname pname from tchannel t left join tchannel c on t.pid=c.id where t.cname like ? order by t.id desc limit ?,?;";
		//将name作为模糊查询的条件拼串，并作为参数
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
		String sql = "select count(id) count from tchannel";
		//查询并赋值
		List<Map<String, Object>> list = db.execQuery(sql,null);
		//获取count的值
		int tchaCounts = Integer.parseInt((String) list.get(0).get("count"));
		//返回总页数
		return tchaCounts;
	}
	
}
