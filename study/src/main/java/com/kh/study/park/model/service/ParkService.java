package com.kh.study.park.model.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kh.study.park.model.dto.ParkDto;

@Service
public class ParkService {

	public String findAll(String page) {
		
		String url = "https://api.data.go.kr/openapi/tn_pubr_public_cty_park_info_api?serviceKey=6f970256c2b27b3546884d705b3fc806bc519904b675b417927a91c8ae702b31&numOfRows=5&type=json";
		url += "&pageNo=" + page;
		
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

	public String findByNo(String manageNo) {
		System.out.println(manageNo);
		String url = "https://api.data.go.kr/openapi/tn_pubr_public_cty_park_info_api?serviceKey=6f970256c2b27b3546884d705b3fc806bc519904b675b417927a91c8ae702b31&type=json";
		url += "&MANAGE_NO=" + manageNo;
		
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

}
