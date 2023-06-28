package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.xdevapi.PreparableStatement;

public class login {

	public PreparedStatement pstmt;
	public ResultSet rs;
	public Connection conn;
	
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "0000"); 
			System.out.println("success");
		}catch(SQLException e) {
			System.out.println("SQLException" + e);
		}
	}
	
	
	public static int login_f(String id, String pw) {
		String SQL = "SELECT pw FROM login where id = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).contentEquals(pw)) {
					return 1; // 로그인 성공
				}
				else {
					return 0; // 아이디와 비밀번호 불일치
				}
			}
			else return -1; // 아이디x
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2; // DB 오류
	}
	
	public static int insert_data(String id, String pw) {
		String SQL = "insert into login(id, pw) values(" + id + ", " + pw + ")";
		
		System.out.println(SQL);
		conn = getConnection();
		Statement state = null;
		int count = 0;
		
		try {
			state = conn.createStatement();
			count = state.executeUpdate(SQL);
			
			System.out.println("성공적으로 추가되었습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(state != null) {
					state.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		
		
		
		while(true) {
			System.out.println("1. 회원가입 2. 로그인 3. 종료");
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
				insert_data(id,pw);
			}
			else if(i == 3) {
				System.out.println("종료합니다.");
				break;
			}
			else {
				System.out.println("다시 선택해주세요");
			}
		}
		
		
	}

}
