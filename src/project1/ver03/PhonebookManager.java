package project1.ver03;

import java.util.Scanner;

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
		System.out.print("이름:");
		String name = scanner.nextLine();
		System.out.print("전화번호:");
		String phoneNum = scanner.nextLine();
		System.out.print("생년월일:");
		String birthday = scanner.nextLine();
		
		piArr[index++] = new PhoneInfo(name, phoneNum, birthday);
		
		System.out.println("데이터 입력이 완료되었습니다."); 
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
