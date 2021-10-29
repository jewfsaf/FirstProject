package project1.ver08;

public class AutoSaver extends Thread {
	
	PhonebookManager pbm;
	
	public AutoSaver(PhonebookManager pbm) {
		this.pbm = pbm;
	}
	
	@Override
	public void run() {
		
		try {
			while(true) {
				pbm.saveTextInfo();
				sleep(5000);
			}
		}
		catch (InterruptedException e) {
			System.out.println("쓰레드 OFF");
		}
	}
}
