package com.ds.security.file.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ds.security.exception.NotFoundIdException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {
	
	private final Path fileLocation;

	
	public FileService() {
		this.fileLocation = Paths.get("uploads").toAbsolutePath().normalize();
	}
	
	
	public String rename(MultipartFile upfile) {
		StringBuilder sb = new StringBuilder();
		sb.append("DS_");
		sb.append(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
		sb.append("_");
		sb.append((int)(Math.random()*100)+1);
		sb.append(upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
		return sb.toString();
	}
	
	public String fileTransferTo(MultipartFile upfile, String changeName) {
		Path targetLocation = this.fileLocation.resolve(changeName);
		
		try {
			Files.copy(upfile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return "http://localhost/uploads/"+changeName;
		} catch (IOException e) {
			throw new NotFoundIdException("이상한 파일입니다.");
		}
	}
	
	
}
