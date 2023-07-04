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
	public static int page = 0;
	public static int max_page = 0;
	
	// DB 연결
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "0000"); 
			//ystem.out.println("success");
		}catch(SQLException e) {
			System.out.println("SQLException" + e);
		}
			
		return conn;
	}
	
	// 회원가입 (회원 정보 DB 저장)
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
					System.out.println("로그인 성공\n");
						
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
	
	// 게시판 목록
	public static void board_list() {
			
		Scanner sc = new Scanner(System.in);
		ArrayList<boardBean> list = new ArrayList<boardBean>();
		
		
		String SQL = "SELECT bid, title, mid, time, hits FROM board";
		System.out.println("번호\t제목\t작성자\t작성일\t조회수");
				
			
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				boardBean bean = new boardBean();
				bean.setBid(rs.getInt("bid"));
				bean.setTitle(rs.getString("title"));
				bean.setMid(rs.getString("mid"));
				bean.setTime(rs.getString("time"));
				bean.setHits(rs.getInt("hits"));
				list.add(bean);
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getBid() +  "\t" + list.get(i).getTitle() + 
			"\t" + list.get(i).getMid() + "\t" + list.get(i).getTime() 
			+ "\t" + list.get(i).getHits());
		}
				
		
	}
	
	// 게시판 보기
	public static void read_board(int read_num) {
		System.out.println("게시판보기 들어옴");
		// 조회수 올리기
		hitsUp(read_num);
		
		String SQL = "select bid,title,mid,hits,content from board where bid = ?";
		System.out.println("번호\t제목\t작성자\t조회수\t내용");
		ArrayList<boardBean> list = new ArrayList<boardBean>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, read_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boardBean bean = new boardBean();
				bean.setBid(rs.getInt("bid"));
				bean.setTitle(rs.getString("title"));
				bean.setMid(rs.getString("mid"));
				bean.setHits(rs.getInt("hits"));
				bean.setContent(rs.getString("content"));
				list.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getBid() + "\t" + list.get(i).getTitle() + 
			"\t" + list.get(i).getMid() + "\t" + list.get(i).getHits() 
			+ "\t" + list.get(i).getContent());
		}
	}
	
	// 게시판 쓰기 
	public static void write_board(String titleIn, String contentIn, String id) {
		
		String SQL = "select count(*) from board";
		int count = 0;
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
				System.out.println("----------------count" + rs.getInt(1));
				count++;
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 수정
		SQL = "insert into board(bid, title, mid, hits, content) values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, count);
			pstmt.setString(2, titleIn);
			pstmt.setString(3, id);
			pstmt.setInt(4, 0);
			pstmt.setString(5, contentIn);
				
			pstmt.executeUpdate();
			System.out.println("성공적으로 글이 등록되었습니다.");
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 조회수 올리기
	public static void hitsUp(int read_num) {
		
		int hitsUp = hitsGet(read_num);
		String SQL = "update board set hits=? where bid=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, hitsUp);
			pstmt.setInt(2, read_num);
			pstmt.executeUpdate();
			System.out.println("조회수가 올라갔습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 조회수 가져오기 
	public static int hitsGet(int read_num) {
		System.out.println("조회수 올리기 들어옴");
		String SQL = "select hits from board where bid=?";
		int hitsUp = 0;
		//String SQL = "update board set hits='?' where bid=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, read_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				hitsUp = rs.getInt("hits");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		hitsUp++;
		return hitsUp;
	}
}
