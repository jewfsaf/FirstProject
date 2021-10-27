package project1;

import java.util.Scanner;

import project1.ver04.PhonebookManager;

public class PhoneBookVer04 {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		PhonebookManager pbm = new PhonebookManager();
		while(true) {
			System.out.println("선택하세요...");
			System.out.println("1. 데이터 입력");
			System.out.println("2. 데이터 검색");
			System.out.println("3. 데이터 삭제");
			System.out.println("4. 데이터 출력");
			System.out.println("5. 프로그램 종료");
			System.out.print("선택: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			case 1:
				pbm.dataInput();
				break;
			case 2:
				pbm.dataSearch();
				break;
			case 3:
				pbm.dataDelete();
				break;
			case 4:
				pbm.dataAllshow();
				break;
			case 5:
				System.out.println("프로그램종료");
				return;
			}
		}
	}
}
