package project1.ver04;

public class PhoneCompanyInfo extends PhoneInfo {
	
	String companyName; //회사명
	
	public PhoneCompanyInfo(String name, String phoneNumber, String companyName) {
		super(name, phoneNumber);
		this.companyName = companyName;
	}
	
	@Override
	public void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("회사명:"+ companyName);
	}
}
