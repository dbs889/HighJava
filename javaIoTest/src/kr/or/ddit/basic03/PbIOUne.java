package kr.or.ddit.basic03;

public class PbIOUne {
	
	
	//생성자
	public PbIOUne(){
		
		
		
	}
	
	

	public static void main(String[] args) {
		

	}

}

class pbookUne{
	
	private String name;
	private int age = 0;
	private String addr;
	private String tel;
	
	
	public pbookUne(String name, int age, String addr, String tel) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.tel = tel;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
	
	
}
