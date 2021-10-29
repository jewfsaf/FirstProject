package project1.ver05;

public class PhoneInfo {
	String name;	//이름
	String phoneNumber;	//전화번호
	
	//2개의 매개변수를 갖는 생성자 오버로딩 : 생년월일은 선택사항
	public PhoneInfo(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public void showPhoneInfo() {
		System.out.println("이름:"+ name);
		System.out.println("전화번호:"+ phoneNumber);
	}
}
