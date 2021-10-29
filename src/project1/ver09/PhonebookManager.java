package project1.ver09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PhonebookManager {
	
	PreparedStatement ps;
	Statement st;
	Connection con;
	ResultSet rs;
	
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
		
		String name = "";
		String phoneNum = "";
		String birthday = "";
		
		try {
			connect();
			
			String sql ="INSERT INTO phonebook_tb VALUES "
						+ " (seq_phonebook.nextval, ?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			
			System.out.println("데이터 입력을 시작합니다..");
			System.out.print("이름:");
			name = scanner.nextLine();
			System.out.print("전화번호:");
			phoneNum = scanner.nextLine();
			System.out.print("생년월일:");
			birthday = scanner.nextLine();
			
			ps.setString(1, name);
			ps.setString(2, phoneNum);
			ps.setString(3, birthday);
			
			piArr[index++] = new PhoneInfo(name, phoneNum, birthday);
			int data = ps.executeUpdate();
			System.out.println(data+"행 입력 성공");
		}
		catch(SQLException e) {
			System.out.println("Db 연결 실패");
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("알 수 없는 예외발생");
			e.printStackTrace();
		}
		finally {
			close();
		}
		System.out.println("데이터 입력이 완료되었습니다."); 
	}
	
	//메뉴검색
	public void dataSearch() {
		try {
			connect();
			
			st = con.createStatement();
			
			System.out.println("데이터 검색을 시작합니다..");
			Scanner scanner = new Scanner(System.in);
			System.out.print("이름:");
			String search = scanner.nextLine();
			boolean isFind = false;
			
			String sql = "SELECT idx, name, phonenum, "
					+ " to_char(birthday, 'yyyy-mm-dd') FROM "
					+ " phonebook_tb WHERE name LIKE '%" +search+ "%'";
			
			rs = st.executeQuery(sql);
			System.out.println("오라클 검색 결과");
			while (rs.next()) {
				int idx = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String date = rs.getString(4);
				
				System.out.printf("%d %s %s %s\n", idx, name, phone, date);
			}
			
			for(int i=0 ; i<index ; i++) {
				if(search.compareTo(piArr[i].name)==0) {
					piArr[i].showPhoneInfo();
					isFind = true;
				}
			}
			
			if(isFind==false) {}
		}
		catch(Exception e) {
			System.out.println("알 수 없는 예외발생");
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	//메뉴삭제
	public void dataDelete() {
		try {
			connect();
			
			String sql = "DELETE FROM phonebook_tb WHERE name = ?";
			
			ps = con.prepareStatement(sql);
			
			System.out.println("데이터 삭제를 시작합니다..");
			Scanner scanner = new Scanner(System.in);
			System.out.print("이름:");
			String search = scanner.nextLine();
			int deleteIndex = -1;
			
			ps.setString(1, search);
			
			int data = ps.executeUpdate();
			System.out.println(data + "행 삭제완료");
			
			for(int i=0 ; i<index ; i++) {
				if(search.compareTo(piArr[i].name) == 0) {
					deleteIndex = i;
					index--;
					break;
				}
			}
			
			if(deleteIndex==-1) {}
			else {
				for(int i=deleteIndex ; i<index ; i++) {
					piArr[i] = piArr[i+1];
				}
				System.out.println("데이터 삭제가 완료되었습니다.");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	public void dataAllshow() {
		for(int i=0 ; i<index ; i++) {
			piArr[i].showPhoneInfo();
		}
		System.out.println("전체정보가 출력되었습니다.");		
	}
	
	//오라클 연결
	public void connect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection
					("jdbc:oracle:thin://@localhost:1521:xe", "kosmo", "1234");
			if(con!=null) {
				System.out.println("Oracle 연결성공");
			}
			else {
				System.out.println("Oracle 연결실패");
			}
		}
		catch(Exception e) {
			System.out.println("Oracle 연결시 예외발생");
			e.printStackTrace();
		}
	}
	
	//스트림 닫아주기
	public void close() {
		try {
			if(ps != null)ps.close();
			if(con != null)con.close();
			if(st != null)st.close();
			if(rs != null)rs.close();
		}
		catch(SQLException e) {
			System.out.println("자원 반납시 예외 발생");
		}
	}
}
