package project1.ver04;

import java.util.Scanner;

import project1.ver04.PhonebookManager;

public class PhonebookManager {
	
	PhoneInfo[]	piArr;
	int index;
	
	//생성자
	public PhonebookManager() {
		piArr = new PhoneInfo[100];
		index = 0;
	}
	
	//메뉴출력
	public void printMenu() {
		for(int i=0 ; i<index ; i++) {
			piArr[i].showPhoneInfo();
			System.out.println("");
		}
	}
	
	//메뉴입력
	public void dataInput() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("데이터 입력을 시작합니다..");
		System.out.println("1.일반, 2.동창, 3.회사");
		System.out.print("선택>>");
		int choice = scanner.nextInt();
		scanner.nextLine();
		
		String name = "";
		String phoneNumber = "";
		
		System.out.print("이름:");
		name = scanner.nextLine();
		System.out.print("전화번호:");
		phoneNumber = scanner.nextLine();
		
			switch(choice) {
			case 1:
				piArr[index++] = new PhoneInfo(name, phoneNumber);
				break;
				
			case 2:
				System.out.print("전공:");
				String major = scanner.nextLine();
				System.out.print("학년:");
				int grade = scanner.nextInt();
				piArr[index++] = new PhoneSchoolInfo(name, phoneNumber, major, grade);
				break;
				
			case 3:
				System.out.print("회사명:");
				String companyName = scanner.nextLine();
				piArr[index++] = new PhoneCompanyInfo(name, phoneNumber, companyName);
				break;
			}
			System.out.println("데이터 입력 완료");
	}
	
	//메뉴검색
	public void dataSearch() {
		System.out.println("데이터 검색을 시작합니다..");
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름:");
		String search = scanner.nextLine();
		boolean isFind = false;
		for(int i=0 ; i<index ; i++) {
			if(search.compareTo(piArr[i].name)==0) {
				piArr[i].showPhoneInfo();
				isFind = true;
			}
		}
		
		if(isFind==false) {
			System.out.println("검색 결과가 없습니다.");
		}
		
	}
	
	//메뉴삭제
	public void dataDelete() {
		System.out.println("데이터 삭제를 시작합니다..");
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름:");
		String search = scanner.nextLine();
		int deleteIndex = -1;
			for(int i=0 ; i<index ; i++) {
				if(search.compareTo(piArr[i].name) == 0) {
					deleteIndex = i;
					index--;
					break;
				}
			}
		if(deleteIndex==-1) {
			System.out.println("==삭제된 데이터가 없습니다==");
		}
		else {
			for(int i=deleteIndex ; i<index ; i++) {
				piArr[i] = piArr[i+1];
			}
			System.out.println("데이터 삭제가 완료되었습니다.");
		}
	}
	
	public void dataAllshow() {
		for(int i=0 ; i<index ; i++) {
			piArr[i].showPhoneInfo();
		}
		System.out.println("전체정보가 출력되었습니다.");		
	}
}
