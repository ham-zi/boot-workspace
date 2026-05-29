package com.kh.study.park.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.study.park.model.dto.ParkDto;
import com.kh.study.park.model.service.ParkService;

@CrossOrigin("*")
@RequestMapping("/api/parks")
@RestController
public class ParkController {
	
	@Autowired
	private ParkService parkService;
	//전체조회
	@GetMapping()
	public ResponseEntity<String> findAll(@RequestParam(value="page")String page){
		String parks = parkService.findAll(page);
		return ResponseEntity.ok(parks);
	}
	
	//단일조회 MANAGE_NO
	@GetMapping("/{no}")
	public ResponseEntity<String> findByNo(@PathVariable(value="no")int manageNo) {
		String park = parkService.findByNo(manageNo);
		return ResponseEntity.ok(park);
		
	}
	
}
