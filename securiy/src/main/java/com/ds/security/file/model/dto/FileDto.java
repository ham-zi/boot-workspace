package com.ds.security.file.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FileDto {
	private Long fileNo;
	private Long refBno;
	private String originName;
	private String changeName;
	private String filePath;
	private String fileLevel;//M(Main) or C(Common)
}
