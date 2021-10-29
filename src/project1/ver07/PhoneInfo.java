package project1.ver07;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		PhoneInfo pi = (PhoneInfo)obj;
		if(pi.name.equals(this.name)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
