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

// 게시판 객체
class boardBean{
	
	int bid;
	String title;
	String mid;
	String time;
	String content;
	int hits;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
			
			// 회원가입
			if(num == 1) {
				System.out.println("아이디 : ");
				String id = sc.next();
				System.out.println("비밀번호 : ");
				String pw = sc.next();
				dao.insert_data(id, pw);
			}
			// 로그인
			else if(num == 2) {
				System.out.println("아이디 : ");
				String id = sc.next();
				System.out.println("비밀번호 : ");
				String pw = sc.next();
				int result = dao.login_f(id,pw);
				
				// 로그인 성공
				if(result == 1) {
					while(true) {
						// 게시판 목록 출력
						dao.board_list();
						
						System.out.println("\n1. 글쓰기 2. 글보기 3. 로그아웃 4. 이전 5. 다음");
						int num2 = sc.nextInt();
						
						// 글 쓰기
						if(num2 == 1) {
							System.out.println("제목 입력 : ");
							String titleIn = sc.next();
							System.out.println("내용 입력 : ");
							String contentIn = sc.next();
								
							// 글 저장
							dao.write_board(titleIn, contentIn, id);
						}
						// 글 보기
						else if(num2 == 2) {
							while(true) {
								System.out.println("몇 번째 글을 보실껀가요?");
								int num3 = sc.nextInt();
								dao.read_board(num3);
								break;
							}
							
							
							try {
								
							}catch(Exception e) {
								e.printStackTrace();
							}
							
						}
						else if(num2 == 3) {
							System.out.println("로그아웃이 됩니다.");
							break;
						}
						else if(num2 == 4) {
							if(dao.page == 0) {
								System.out.println("최소 페이지입니다. 다시 선택해주시길 바랍니다.");
							}
							
						}
						else if(num2 == 5) {
							if(dao.page == dao.max_page) {
								System.out.println("최대 페이지입니다. 다시 선택해주시길 바랍니다.");
							}
							
						}
					}
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
		
	} // main end

	private static void read_board(int num3) {
		// TODO Auto-generated method stub
		
	}

} // class login end
