package alidayu_demo.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;


public abstract class MybatisRepository {

	public static final String SUFFIX_MAPPER = "Mapper";
	
	protected SqlSessionFactory sqlSessionFactory;
	
	protected DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	protected TransactionFactory transactionFactory;
	public void setTransactionFactory(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}
	
	protected String config = "mybatis.xml";
	public void setConfig(String config) {
		this.config = config;
	}
	
	abstract public void init();
	
	public void init(Class<?>... resultClasses) {
		if(dataSource != null) {
			if(transactionFactory == null) transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("current", transactionFactory, dataSource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(new Configuration(environment));
		} else {
			try (InputStream inputStream = Resources.getResourceAsStream(config)) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch(IOException x) {
				throw new RuntimeException(x);
			}
		}
		
		Configuration configuration = sqlSessionFactory.getConfiguration();
		for(Class<?> resultClass : resultClasses) {
			try {
				Class<?> mapperClass = Class.forName(resultClass.getName() + SUFFIX_MAPPER);
				configuration.addMapper(mapperClass);
			} catch(Exception x) {
				x.printStackTrace();
				throw new RuntimeException(x);
			}
		}
	}
	
	public void destory() { }
	
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	// 重新定义适用本项目的方法，区别于其他通用方法，故而方法名使用"_h"结尾
	public <T> T create_h(T object) { // success to INSERT （在openSession方法中形参boolean autoCommit为true）
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			int n = session.insert(object.getClass().getName() + SUFFIX_MAPPER + ".create", object);
			return n > 0 ? object : null;
		}
	}
	public <T> void insert_h(T object) { // success to INSERT
		try(SqlSession session = sqlSessionFactory.openSession()) {
			session.selectOne(object.getClass().getName() + SUFFIX_MAPPER + ".insert", object);
		}
	}
	public <T> int delete_h(Class<T> resultClass, Object param) { // success to DELETE （占位符参数直接使用实体类传参）
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			return session.delete(resultClass.getName() + SUFFIX_MAPPER + ".delete", param);
		}
	}
	public <T> T update_h(T object) { // success to UPDATE 
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			int n = session.update(object.getClass().getName() + SUFFIX_MAPPER + ".update", object);
			return n > 0 ? object : null;
		}
	}
	public <T> T get_h(String verb, Class<T> resultClass, Object param) { // success to GET T
		try(SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne(resultClass.getName() + SUFFIX_MAPPER + "." + verb, param);
		}
	}
	public <T> List<T> query_h(String verb, Class<T> resultClass, Object param) { // success to QUERY List
		try(SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectList(resultClass.getName() + SUFFIX_MAPPER + "." + verb, param);
		}
	}
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	@Deprecated
	public <T> Object execute(Class<T> resultClass, Excecution excecution) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Object mapper = session.getMapper(Class.forName(resultClass.getName() + SUFFIX_MAPPER));
			if(mapper == null) throw new NullPointerException("mapper == null");
			Object result = excecution.apply(mapper);
			session.commit();
			return result;
		} catch(Exception x) {
			session.rollback();
			throw new RuntimeException(x);
		} finally {
			session.close();
		}
	}
	@Deprecated
	public <T> T create(T object) {
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			int n = session.insert(object.getClass().getName() + SUFFIX_MAPPER + ".create", object);
			return n > 0 ? object : null;
		}
	}
	@Deprecated
	public <T> T get(String verb, Class<T> resultClass, Object criteria) {
		try(SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne(resultClass.getName() + SUFFIX_MAPPER + "." + verb, criteria);
		}
	}
	@Deprecated
	public <T> T get(Class<T> resultClass, Object criteria) {
		return get("get", resultClass, criteria);
	}
	@Deprecated
	public <T> int count(Class<T> resultClass, Object criteria) {
		try(SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne(resultClass.getName() + SUFFIX_MAPPER + ".count", criteria);
		}
	}
	@Deprecated
	public <T> List<T> query(String verb, Class<T> resultClass, Object criteria) {
		try(SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectList(resultClass.getName() + SUFFIX_MAPPER + "." + verb, criteria);
		}
	}
	@Deprecated
	public <T> List<T> query(Class<T> resultClass, Object criteria) {
		return query("query", resultClass, criteria);
	}
	@Deprecated
	public <T> int delete(Class<T> resultClass, Object criteria) {
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			return session.delete(resultClass.getName() + SUFFIX_MAPPER + ".delete", criteria);
		}
	}
	
//	public <T> void query(String verb, Class<T> resultClass, Object criteria, final RowHandler<T> handler) {
//		try(SqlSession session = sqlSessionFactory.openSession()) {
//			session.select(resultClass.getName() + SUFFIX_MAPPER + "." + verb, criteria, new ResultHandler<T>() {
//				@Override
//				public void handleResult(ResultContext<? extends T> resultContext) {
//					handler.handle(resultContext.getResultObject());
//				}
//			});
//		}
//	}

//	public <T> void query(Class<T> resultClass, Object criteria, RowHandler<T> handler) {
//		query("query", resultClass, criteria, handler);
//	}
//
//	public <T> ResultSet<T> retrieve(Class<T> resultClass, Object criteria) {
//		SqlSession session = sqlSessionFactory.openSession();
//		Cursor<T> cursor = session.selectCursor(resultClass.getName() + SUFFIX_MAPPER + ".query", criteria);
//		return new ResultSet<T>(session, cursor);
//	}
//
	@Deprecated
	public <T> T update(T object) {
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			int n = session.update(object.getClass().getName() + SUFFIX_MAPPER + ".update", object);
			return n > 0 ? object : null;
		}
	}

}
