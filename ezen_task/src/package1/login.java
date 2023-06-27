package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class login {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String pass = "0000";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "0000"); 
			System.out.println("success");
		}catch(SQLException e) {
			System.out.println("SQLException" + e);
		}
		
		public int login_f(String id, String pw) {
			
		}
		
		while(true) {
			System.out.println("1. 회원가입 2. 로그인");
			int i = sc.nextInt();
			
			if(i == 1) {
				System.out.println("아이디 : ");
				String id = sc.next();
				System.out.println("비밀번호 : ");
				String pw = sc.next();
				login_f(id, pw);
			}
			else if(i == 2) {
				System.out.println("아이디 : ");
				String id = sc.next();
				System.out.println("비밀번호 : ");
				String pw = sc.next();
			}
			else {
				System.out.println("다시 선택해주세요");
			}
		}
		
		
	}

}
