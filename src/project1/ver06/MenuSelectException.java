package project1.ver06;

public class MenuSelectException extends Exception{
	
	public MenuSelectException() {
		super("1~5이외의 정수를 입력했습니다.");
	}
}
