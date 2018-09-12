package com.example.demo.config.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 从数据源配置
 * @author wb0024
 *
 */
@Configuration
//读取自定义配置文件 datasource.properties
@PropertySource("classpath:config/datasource.properties")
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig {
	// 精确到 cluster 目录，以便跟其他数据源隔离
	static final String PACKAGE = "com.xiaoniu.dao.cluster";
	static final String TYPE_ALIASES_PACKAGE = "com.xiaoniu.domain.entity.cluster";
	static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";
	@Bean(name = "clusterDataSource")
	@ConfigurationProperties(prefix = "datasource.cluster")
	public DataSource clusterDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "clusterTransactionManager")
	public DataSourceTransactionManager clusterTransactionManager() {
		return new DataSourceTransactionManager(clusterDataSource());
	}

	@Bean(name = "clusterSqlSessionFactory")
	public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(clusterDataSource);
		sessionFactory.setTypeAliasesPackage(ClusterDataSourceConfig.TYPE_ALIASES_PACKAGE);
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}
}