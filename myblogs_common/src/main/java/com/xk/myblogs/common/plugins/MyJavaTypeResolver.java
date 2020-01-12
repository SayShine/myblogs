package com.xk.myblogs.common.plugins;


import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * 
 * @author xiongkai
 * @date 2020年01月12日 23时43分20秒
 **/
public class MyJavaTypeResolver extends JavaTypeResolverDefaultImpl {

	public MyJavaTypeResolver() {
		super();
		// 自定义tinyint类型生成的实体字段类型Integer，默认是Boolean
		typeMap.put(Types.TINYINT,
				new JdbcTypeInformation("INTEGER", new FullyQualifiedJavaType(Integer.class.getName())));
	}

}
