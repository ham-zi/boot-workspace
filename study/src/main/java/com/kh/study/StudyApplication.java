package com.kh.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyApplication {

	//애플리케이션을 구동 -> @SpringBootApplication 애노테이션이 붙은 클래스의
	// main메소드를 호출
	
	// @EnableAutoConfiguration
	// AutoConfiguration(자동 구성) => 스프링부트의 핵심 기능
	// -> 애플리케이션의 클래스패스에 존재하는 라이브러리 및 설정을 기반으로 Bean을 싸그리몽땅 자동 구성해줌
	// 개발자가 설정 하나도 안하고 바로 개발을 시작할 수 있음
	
	// 이 기능을 어떻게 키는가? @EnalbeAutoConfiguration
	// 이건 SpringApplication에 있다.
	// ComponentScan으로 컴포넌트 쫙 스캔 => 모든 자동구성이 활성화가 끝나면 애플리케이션을 실행
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}

}
