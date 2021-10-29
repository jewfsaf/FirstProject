package project1.ver08;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PhonebookManager implements SubDataMenu {
	
	HashSet<PhoneInfo> set;
	
	//생성자
	public PhonebookManager() {
		set = new HashSet<PhoneInfo>();
		readFriendInfo();
	}
	
	//메뉴출력
	public void printMenu() {
		for (PhoneInfo pi : set) {
			pi.showPhoneInfo();
			System.out.println("");
		}
	}
	
	//메뉴입력
	public void dataInput() throws MenuSelectException {
		
		Scanner scanner = new Scanner(System.in);
		PhoneInfo pi = null;
		
		int choice = 0;
		
		System.out.println("데이터 입력을 시작합니다..");
		try {
			System.out.println("1.일반, 2.동창, 3.회사");
			System.out.print("선택>>");
			choice = scanner.nextInt();
			scanner.nextLine();
		}
		catch(InputMismatchException e) {
			System.out.println("정수대신 문자열을 입력하셨습니다.");
			return;
		}
		if(choice<1 || choice>3) {
			MenuSelectException ex = new MenuSelectException();
			throw ex;
		}
		
		String name = "";
		String phoneNumber = "";
		
		System.out.print("이름:");
		name = scanner.nextLine();
		System.out.print("전화번호:");
		phoneNumber = scanner.nextLine();
		
		switch(choice) {
		case basic:
			pi = new PhoneInfo(name, phoneNumber);
			break;
			
		case school:
			System.out.print("전공:");
			String major = scanner.nextLine();
			System.out.print("학년:");
			int grade = scanner.nextInt();
			scanner.nextLine();
			pi = new PhoneSchoolInfo(name, phoneNumber, major, grade);
			break;
			
		case company:
			System.out.print("회사명:");
			String companyName = scanner.nextLine();
			pi = new PhoneCompanyInfo(name, phoneNumber, companyName);
			break;
		}
		
		
		//중복검사
		boolean overwrite = set.add(pi);
		if(overwrite == false) {
			System.out.println("이미 저장된 데이터 입니다.");
			System.out.print("덮어쓸까요? Y(y) / N(n)");
			String yn = scanner.nextLine();
			if(yn.equalsIgnoreCase("Y")) {
				set.remove(pi);
				set.add(pi);
				System.out.println("입력한 정보가 저장되었습니다.");
			}
			else if (yn.equalsIgnoreCase("N")) {
				System.out.println("기존 데이터를 유지합니다");
			}
		}
		else {
			System.out.println("데이터 입력 완료");	
		}
	}	
	
	//메뉴검색
	public void dataSearch() {
		System.out.println("데이터 검색을 시작합니다..");
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름:");
		String search = scanner.nextLine();
		boolean isFind = false;
		for (PhoneInfo pi : set) {
			if (search.equals(pi.name)) {
				pi.showPhoneInfo();
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
			for(PhoneInfo pi : set) {
				if(search.equals(pi.name)) {
					set.remove(pi);
					deleteIndex = 0;
				}
			}
		if(deleteIndex==-1) {
			System.out.println("==삭제된 데이터가 없습니다==");
		}
		else {
			System.out.println("데이터 삭제가 완료되었습니다.");
		}
	}
	
	//전체정보출력
	public void dataAllshow() {
		for(PhoneInfo pi : set) {
			pi.showPhoneInfo();
		}
		System.out.println("전체정보가 출력되었습니다.");		
	}
	
	
	//저장(직렬)
	public void saveFriendInfo() {		
		try {
			ObjectOutputStream out =
				new ObjectOutputStream(
					new FileOutputStream("src/project1/ver08/phonebook.obj")
				);
			
			for(PhoneInfo fr : set) {
				out.writeObject(fr);
			}
			out.close();
		}
		catch (Exception e) {
			System.out.println("친구 정보 직렬화시 예외발생");
		}
	}
	
	//저장(역직렬)
	public void readFriendInfo() {
		try {
			ObjectInputStream in =
				new ObjectInputStream(
					new FileInputStream("src/project1/ver08/phonebook.obj"));
			while(true) {
				PhoneInfo fr =(PhoneInfo)in.readObject();
				set.add(fr);
				if(fr==null) break;
			}
			in.close();
		}
		catch(Exception e) {
			System.out.println("더 이상 읽을 객체가 없습니다.");
		}
		System.out.println("친구정보가 복원되었습니다.");
	}
	
	public void saveOption(AutoSaver as) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		try {
			System.out.println("저장 옵션 선택");
			System.out.println("1.자동 저장 켜기");
			System.out.println("2.자동 저장 끄기");
			System.out.print("선택>>");
			choice = sc.nextInt();
		}
		catch (InputMismatchException e) {
			System.out.println("문자는 입력 불가능");
			return;
		}
		
		if (choice == 1) {
			if (as.isAlive()) {
				System.out.println("이미 실행중 입니다");
			}
			else {
				as.setDaemon(true);
				as.start();
				System.out.println("자동 저장 켜짐");
			}
		}
		else if (choice == 2) {
			as.interrupt();
			System.out.println("자동 저장 꺼짐");
		}
		else {
			System.out.println("정해진 숫자만 입력해주세요");
			return;
		}
	}
	
	public void saveTextInfo() {
		System.out.println("자동 저장 실행중");
		
		try {
			PrintWriter out = new PrintWriter(
					new FileWriter("src/project1/ver08/PhoneBook.txt"));
			
			for (PhoneInfo pi : set) {
				out.println(pi.toString());
			}
			out.close();
		}
		catch (IOException e) {
			System.out.println("입출력 예외");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
