package com.kh.semi.file.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LocalFileService implements FileService{

	private final Path fileLocation;
	
	public LocalFileService() {
		this.fileLocation = Paths.get("uploads").toAbsolutePath().normalize();
	}
	
	@Override
	public String store(MultipartFile file) {
		
		//이름바꾸기 생략
		
		
		String originalFileName = file.getOriginalFilename();
		Path targetLocation = this.fileLocation.resolve(originalFileName);
		
		try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			
			return "http://localhost/uploads/"+originalFileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("이상한 파일");
		}
	}
	
	//이름변경 
	private String changeName(String originName) {
		StringBuilder sb = new StringBuilder();
		sb.append("unknown_");
		sb.append(1);
		return null;
	}
	

}
