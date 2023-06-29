package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DAOClass {
	public static PreparedStatement pstmt;
	public static ResultSet rs;
	public static Connection conn;
	
	// DB 연결
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "0000"); 
			System.out.println("success");
		}catch(SQLException e) {
			System.out.println("SQLException" + e);
		}
			
		return conn;
	}
	
	// 회원가입
	public static void insert_data(String id, String pw) {
		String SQL = "insert into member(mid, mpwd) values(?,?)";	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			System.out.println("id = " + id);
			System.out.println("pw = " + pw);
			pstmt.setString(2, pw);
					
			pstmt.executeUpdate();
			System.out.println("성공적으로 추가되었습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 로그인 
	public static int login_f(String id, String pw) {
		String SQL = "SELECT mpwd FROM member where mid = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).contentEquals(pw)) {
					System.out.println("로그인 성공");
						
					return 1; // 로그인 성공
				}
				else {
					System.out.println("불일치");
					return 0; // 아이디와 비밀번호 불일치
				}
			}
			else {
				System.out.println("아이디가 없음");
				return -1; // 아이디x
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2; // DB 오류
	}
	
	// 게시판
	public static void board_f(String id) {
			
		Scanner sc = new Scanner(System.in);
			
		while(true) {
		
			String SQL = "SELECT title, name, time, hits FROM board";
			System.out.println("번호\t제목\t작성자\t작성일\t조회수");
				
			// 결과 담을 ArrayList 생성
			ArrayList<boardBean> list = new ArrayList<boardBean>();
				
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(SQL);
				rs = pstmt.executeQuery();
					
				while(rs.next()) {
					boardBean bean = new boardBean();
					bean.setTitle(rs.getString("title"));
					bean.setName(rs.getString("name"));
					bean.setTime(rs.getString("time"));
					bean.setHits(rs.getInt("hits"));
					list.add(bean);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
				
			for(int i = 0; i < list.size(); i++) {
				System.out.println((i+1) +  "\t" + list.get(i).getTitle() + 
				"\t" + list.get(i).getName() + "\t" + list.get(i).getTime() 
				+ "\t" + list.get(i).getHits());
			}
				
			System.out.println("1. 글쓰기 2. 글보기 3. 로그아웃 4. 이전 5. 다음");
			int num = sc.nextInt();
				
			if(num == 1) {
				System.out.println("제목 입력 : ");
				String titleIn = sc.next();
				System.out.println("내용 입력 : ");
				String contentIn = sc.next();
					
				SQL = "insert into board(title, mid, hits, content) values(?,?,?,?)";
				try {
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, titleIn);
					pstmt.setString(2, id);
					pstmt.setInt(3, 0);
					pstmt.setString(4, contentIn);
						
					pstmt.executeUpdate();
					System.out.println("성공적으로 글이 등록되었습니다.");
						
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			else if(num == 2) {
				while(true) {
					System.out.println("몇 번째 글을 보실껀가요?");
					int num2 = sc.nextInt();
					if(num2 < 1 || num2 >= list.size()) {
						System.out.println("다시 선택해주시길 바랍니다.");
						continue;
					}
					break;
				}
				SQL = "update board set hits='?' where time=?";
				
				try {
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				SQL = "select title, name, hits, content from board where mid = ?";
				System.out.println("제목\t작성자\t조회수");
			}
			else if(num == 3) {
				System.out.println("로그아웃이 됩니다.");
				return;
			}
		}
	}
}
