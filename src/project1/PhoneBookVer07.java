package project1;

import java.util.InputMismatchException;
import java.util.Scanner;

import project1.ver07.PhonebookManager;
import project1.ver07.DataMenu;
import project1.ver07.MenuSelectException;

public class PhoneBookVer07 implements DataMenu{
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		PhonebookManager pbm = new PhonebookManager();
		int choice = 0;
		while(true) {
			try {
				choice = menu(choice);
			}
			catch(MenuSelectException e) {
				System.out.println(e.getMessage());
			}
			catch(InputMismatchException e) {
				System.out.println("정수대신 문자열을 입력하셨습니다.");
			}
			catch(NullPointerException e) {
				System.out.println("검색결과가 없습니다.");
			}
			
			switch(choice) {
			case dataInput:
				try {
					pbm.dataInput();
					break;
				}
				catch(MenuSelectException e) {
					System.out.println("1~3이외의 정수를 입력했습니다.");
					break;
				}
			case dataSerch:
				pbm.dataSearch();
				break;
			case dataDelete:
				pbm.dataDelete();
				break;
			case dataAllshow:
				pbm.dataAllshow();
				break;
			case end:
				System.out.println("프로그램종료");
				return;
			}
		}
	}
	
	public static int menu(int choice) throws MenuSelectException{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("선택하세요...");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 검색");
		System.out.println("3. 데이터 삭제");
		System.out.println("4. 데이터 출력");
		System.out.println("5. 프로그램 종료");
		System.out.print("선택: ");
		choice = sc.nextInt();
		
		if (choice < 1 || choice > 5) {
			throw new MenuSelectException();
		}
		return choice;
	}
}
