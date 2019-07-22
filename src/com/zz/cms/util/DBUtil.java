package com.zz.cms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBUtil {
	// 驱动
	private String driver = "com.mysql.jdbc.Driver";
	// 连接地址，参数是支持中文插入
	private String url = "jdbc:mysql://localhost:3306/cms?useUnicode=true&characterEncoding=utf-8";
	// 连接用户名
	private String user = "root";
	// 连接密码
	private String password = "870919";

	// 获得数据库连接的方法，不允许外界调用
	private Connection getConn() {
		Connection conn = null;
		try {
			// 加载并注册驱动
			Class.forName(driver);
			// 获得数据库连接
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭资源的方法：rs结果集；ps处理对象；conn连接
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	//增删改的方法：sql需要执行的带参sql语句；objs带参SQL语句内需要设置的参数；返回执行结果
	public int execUpdate(String sql, Object [] objs) {
		// 获得数据库连接
		Connection conn = this.getConn();
		// 声明预编译对象
		PreparedStatement ps = null;
		// 如果连接为空时跳出无法执行
		if (conn == null) {
			return 0;
		}
		// 从连接中获得预编译对象
		try {
			ps = conn.prepareStatement(sql);
			// 依次将参数set到ps中参数注入
			for (int i = 0; i < objs.length; i++) {
				// 索引从1开始
				ps.setObject(i + 1, objs[i]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		} finally {
			this.close(null, ps, conn);
		}
	}

	/*
	 * 查询的方法：sql带参的SQL语句
	 * objs为SQL语句中的参数，因为不知道是int还是String，所以用object存参数
	 * List<Map<String,Object>>不知道查询得到的数据为哪个Bean类型所以用Map来封装数据
	 * Map的key为数据库字段名，Value为数据库的字段值
	 */
	public List<Map<String, Object>> execQuery(String sql,Object [] objs){
		//获得连接
		Connection conn = this.getConn();
		//定义处理对象的变量初始化为null
		PreparedStatement ps = null;
		//定义结果集初始化为null
		ResultSet rs = null;
		//定义一个用于存放封装数据的Map集合的List集合
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		try {
			//从数据库连接中获得处理对象
			ps=conn.prepareStatement(sql);
			//把参数注入到SQL中
			if(objs!=null){
				for (int i = 0; i < objs.length; i++) {
					//依次把数组内的参数取出set带ps的i+1位置
					//数组从0开始，参数的位置从1开始
					ps.setObject(i+1, objs[i]);
				}
			}
			
			//获得结果集
			rs=ps.executeQuery();
			//获得结果集的结构
			ResultSetMetaData rm = rs.getMetaData();
			//依次从结果集中取出值
			while(rs.next()){
				//Map集合用于存放一条数据
				Map<String, Object> map = new HashMap<String, Object>();
				//把数据封装到Map
				for (int i = 1; i <= rm.getColumnCount(); i++) {
					//结果集结构中获得字段名rm.getCatalogName(i)，i从1开始
					//结果集获得字段值 rs.getObject(i)，i从1开始
					map.put(rm.getColumnLabel(i), rs.getString(i));	
				}
				//把一条信息放入List集合
				maps.add(map);
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			//调用本类的关闭资源
			this.close(rs, ps, conn);
		}
		return maps;
	}
	

	
}
