package kr.or.ddit.basic.vo;

import java.io.Serializable;


//파일 전송용 vo클래스 --클라이언트에도 같은 위치에 동일한 내용이 있어야한다
public class fileInfoVo implements Serializable {
	
	private String fileName; //파일 이름이 저장될 변수
	private byte[] fileData; //파일의 내용이 저장될 변수
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	
	
	

}
