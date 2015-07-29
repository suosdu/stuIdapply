package com.stu_id_apply.util;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFactory {
		private static SqlMapClient sqlMapClient;

		static{
			System.out.print("SqlMapClient.init");
			try {
				Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
				sqlMapClient=SqlMapClientBuilder.buildSqlMapClient(reader);
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Something bad happened while building the SqlMapClient instance."+e,e);
			}
		}
		public static SqlMapClient getSqlMapClient() {
			return sqlMapClient;
		}		
}
