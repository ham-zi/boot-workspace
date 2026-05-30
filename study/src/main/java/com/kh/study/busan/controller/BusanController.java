package com.kh.study.busan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.study.busan.model.dto.ReviewDto;
import com.kh.study.busan.model.dto.UpdateReviewDto;
import com.kh.study.busan.model.service.BusanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Validated
@Tag(name= "부산 맛집 API", description="맛집 조회 및 리뷰 API")
@RestController
@CrossOrigin("*")
@RequestMapping("/api/busans")
public class BusanController {

	

	@Autowired
	private BusanService service;

	
	@Operation(summary="맛집 목록 조회", description="페이지 단위로 조회")
	@GetMapping
	public String getRes(@Parameter(description="페이지번호", example="1")@RequestParam(value="page", defaultValue="1")@Min(1)int page) {
		return service.getRes(page);
	}
	
	@Operation(summary="맛집 상세 조회")
	@ApiResponses({
		@ApiResponse(responseCode="200", description="조회성공"),
		@ApiResponse(responseCode="404", description="맛집이 없음")
	})
	@GetMapping("/{seq}")
	public String getDetail(@Parameter(description="맛집 식별자", example="70")@PathVariable(value="seq")int seq) {
		return service.getDetail(seq);
	}

	@Operation(summary="맛집 리뷰 작성", description="리뷰 작성")
	@PostMapping("/{seq}/reviews")
	public ResponseEntity<?> save(@Parameter(description="맛집 식별자", example="70")@PathVariable(value="seq")Long ucSeq,@Valid @RequestBody ReviewDto review){
		service.save(ucSeq, review);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@Operation(summary="맛집 리뷰 목록 조회", description="페이지 단위로 조회")
	@GetMapping("/{seq}/reviews")
	public ResponseEntity<List<ReviewDto>> findBySeq(@Parameter(description="맛집 식별자", example="70")@PathVariable(value="seq")Long ucSeq){
		List<ReviewDto>reviews = service.findBySeq(ucSeq);
		return ResponseEntity.ok(reviews);
	}
	
	@ApiResponses({
		@ApiResponse(responseCode="200", description="업데이트성공"),
		@ApiResponse(responseCode="404", description="리뷰존재하지않음")
	})
	@Operation(summary="맛집 리뷰 내용 수정", description="맛집 리뷰 내용 수정")
	@PatchMapping("/{seq}/reviews")
	public ResponseEntity<Void> update(@Parameter(description="맛집 식별자", example="70")@PathVariable(value="seq")Long ucSeq,@RequestBody UpdateReviewDto urd){
		
		// Method : Patch
		
		// /api/busan/70/reviews
		
		// { "content" : "원본",
		//   "updateContent : "바꾸고싶은 내용" }
		service.update(ucSeq, urd);
		
		return ResponseEntity.ok().build();
	}
	
	//@DeleteMapping = 삭제 
	// /api/busans/70/reviews/리뷰번호
	@Operation(summary="맛집 리뷰 삭제", description="맛집 리뷰 삭제")
	@DeleteMapping("/{seq}/reviews")
	public ResponseEntity<Void> delete(@PathVariable(value="seq")Long ucSeq, @RequestBody UpdateReviewDto urd) {
		System.out.println(urd);
		service.delete(ucSeq, urd);
		//Method : DELETE
		// {"content" : "원본내용" }
		return ResponseEntity.noContent().build(); // 204 돌려줄게 없다.
	}
	
	// 생성 201 CREATE
	// 삭제 204 No Content
	// 조회 / 수정 200 OK
	
}
