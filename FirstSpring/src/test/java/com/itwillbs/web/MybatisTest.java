package com.itwillbs.web;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }
		)
public class MybatisTest {
	
	// Mybatis를 사용한 디비연결 테스트

	
	// Mybatis 객체정보(설정정보) 가지고있는 객체 필요함
	// => sqlSessionFactory 객체를 의존하고있다. DI
	
	@Inject
	private SqlSessionFactory sqlFactory;
	
	@Test
	public void 마이바티스_연결테스트() {
		
		System.out.println("@@@@@@@@ sqlFactory : "+sqlFactory);
		
		// 디비 연결
		SqlSession session = sqlFactory.openSession();
		System.out.println("@@@@@ SqlSession : "+session);
		
	}
	
	
	
	
	
	
	
	
	

	
}
