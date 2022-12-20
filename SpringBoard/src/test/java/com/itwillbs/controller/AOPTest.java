package com.itwillbs.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.aop.Calculator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/AOPTest2.xml" }
		)
public class AOPTest {
	
	// @Qualifier("bean이름") : 주입 받기위해서 해당 이름을 가진 객체를 참조
	
	// @Controller @Repository @Service => 객체를 인식 기능 포함
	//  바로 객체 주입 가능 
	
	// 일반객체는 객체 인식하기 어렵다. 바로 객체주입 불가능
	// @Qualifier 사용해서 객체를 못찾을때 힌트로 사용
	
	@Qualifier("proxyCalc")
	@Inject
	private Calculator calculator;
	
	private static final Logger mylog = LoggerFactory.getLogger(AOPTest.class);
	
	@Test
	public void test1() {
		//mylog.debug(calculator+"");
		mylog.debug(calculator.toString());
		calculator.add(1000, 2000);
	}
	

}
