package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import Login.DAOClass;


class boardBean{
	
	String title;
	String name;
	String time;
	int hits;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	
	
	
}



public class login {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		DAOClass dao = new DAOClass();
		
		
		while(true) {
			System.out.println("1. 회원가입 2. 로그인 3. 종료");
			int num = sc.nextInt();
			
			if(num == 1) {
				System.out.println("아이디 : ");
				String id = sc.next();
				System.out.println("비밀번호 : ");
				String pw = sc.next();
				dao.insert_data(id, pw);
			}
			else if(num == 2) {
				System.out.println("아이디 : ");
				String id = sc.next();
				System.out.println("비밀번호 : ");
				String pw = sc.next();
				int result = dao.login_f(id,pw);
				if(result == 1) {
					dao.board_f(id);
				}
			}
			else if(num == 3) {
				System.out.println("종료합니다.");
				break;
			}
			else {
				System.out.println("다시 선택해주세요");
			}
		}
		
	}

}
