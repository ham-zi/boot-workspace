package com.kh.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("study")
// SpringWebStarter에는 기본적으로 Json형태로 Converting해줄 수 있는 Jackson라이브러리를 포함
// produce기본값이 apllication/json; charset="UTF-8"이기 때문에
// 자동으로 MessageConverter로 변환됨 잘 응답됨
public class StudyController {
	
	/*
	 * 뷰리졸브는 어디갔는가? 
	 * (강력한 개념)
	 * Spring Starter
	 * 라이브러리를 무엇을써야하는가? 무슨 버전을 써야하는가?
	 * 
	 * 특정 기능에 필요한 의존성 라이브러리들을 한 번에 관리할 수 있는 개념
	 * 
	 * 웹개발 -> Servlet, Spring MVC, Spring DispatcherServlet, Json관련 라이브러리 등
	 *  -> Spring Boot Starter Web : 웹 애플리케이션에 개발에 필요한 의존성들이 모여있음
	 * 
	 *  개발자가 필요한 기능이 있따 => starter에 가서 추가해야지 => 의존성 관리를 직접하지 않는다.
	 *  모든 개발자가 동일한 stater를 쓰기 때문에 프로젝트 간 의존성 충돌을 방지할 수 있음

	 * 
	 * -주의
	 * Starter에 세상 모든 라이브러리가 존재하지 않음
	 * 
	 * 
	 * 
	 */
	
	@Autowired
	private StudyBean studyBean;
	
	@GetMapping
	public String test() {
		return "돌아갑니다";
	}
}
