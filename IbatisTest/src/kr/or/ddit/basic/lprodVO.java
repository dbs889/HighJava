package kr.or.ddit.basic;

public class lprodVO {
	/*
	 * 
	 * --vo객체의 멤버변수를 자동으로 만들어 주는 쿼리문
			select 'public ' ||
			        decode(Upper(data_type),'NUMBER','int ','String ') ||
			        lower(column_name) || ';'
			from cols
			where upper(table_name) = 'LPROD';
	 * 
	 * 
	 */
	
	private int lprod_id;
	private String lprod_gu;
	private String lprod_nm;
	
	
	
	public int getLprod_id() {
		return lprod_id;
		
		
	}
	public void setLprod_id(int lprod_id) {
		this.lprod_id = lprod_id;
	}
	public String getLprod_gu() {
		return lprod_gu;
	}
	public void setLprod_gu(String lprod_gu) {
		this.lprod_gu = lprod_gu;
	}
	public String getLprod_nm() {
		return lprod_nm;
	}
	public void setLprod_nm(String lprod_nm) {
		this.lprod_nm = lprod_nm;
	}
	
	
	
	
	
}
