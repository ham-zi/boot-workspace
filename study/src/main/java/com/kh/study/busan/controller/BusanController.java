package com.kh.study.busan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.study.busan.model.dto.ReviewDto;
import com.kh.study.busan.model.service.BusanService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/busans")
public class BusanController {

	

	@Autowired
	private BusanService service;

	@GetMapping
	public String getRes(@RequestParam(value="page") int page) {
		return service.getRes(page);
	}
	
	@GetMapping("/{seq}")
	public String getDetail(@PathVariable(value="seq")int seq) {
		return service.getDetail(seq);
	}

	
	@PostMapping("/{seq}/reviews")
	public ResponseEntity<?> save(@PathVariable(value="seq")Long ucSeq,@RequestBody ReviewDto review){
		service.save(ucSeq, review);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/{seq}/reviews")
	public ResponseEntity<List<ReviewDto>> findBySeq(@PathVariable(value="seq")Long ucSeq){
		List<ReviewDto>reviews = service.findBySeq(ucSeq);
		return ResponseEntity.ok(reviews);
	}
}
