package com.kh.study.busan.model.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kh.study.busan.model.dao.ReviewMapper;
import com.kh.study.busan.model.dto.ReviewDto;

@Service
public class BusanService {
	
	@Autowired
	private ReviewMapper mapper;
	
	public String getRes(int page) {
		
		String url= "https://apis.data.go.kr/6260000/FoodService/getFoodKr?serviceKey=6f970256c2b27b3546884d705b3fc806bc519904b675b417927a91c8ae702b31&numOfRows=6&resultType=json";
		url += "&pageNo=" + page;
		
		new RestTemplate();
		
		URI uri = null;
		
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String apiResponse = new RestTemplate().getForObject(uri, String.class);
		
		return apiResponse;
	}

	public String getDetail(int seq) {
		
		String url = "https://apis.data.go.kr/6260000/FoodService/getFoodKr?serviceKey=6f970256c2b27b3546884d705b3fc806bc519904b675b417927a91c8ae702b31&resultType=json";
		url += "&UC_SEQ=" + seq;
		
		URI uri = null;
		
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String apiResponse = new RestTemplate().getForObject(uri, String.class);
		
		return apiResponse;
	}

	public void save(Long ucSeq, ReviewDto review) {
		review.setUcSeq(ucSeq);
		mapper.save(review);
	}
	
	public List<ReviewDto> findBySeq(Long ucSeq) {
		return mapper.findBySeq(ucSeq);
	}
	
	

}
