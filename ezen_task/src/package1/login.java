package package1;

import java.util.Scanner;

public class login {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("1. 회원가입 2. 로그인");
			int i = sc.nextInt();
			
			if(i == 1) {
				System.out.println("아이디 : ");
				String id = sc.next();
				System.out.println("비밀번호 : ");
				String pw = sc.next();
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
