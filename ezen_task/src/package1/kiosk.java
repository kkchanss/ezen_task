package package1;

import java.util.Scanner;
import java.io.*;

class PocDuct{
	int coke;
	int sprite;
	int fanta;
	
	PocDuct(int coke, int sprite, int fanta) {
		this.coke = coke;
		this.sprite = sprite;
		this.fanta = fanta;
	}
}

public class kiosk {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in); // 입력 함수 설정 
		
		PocDuct pd = new PocDuct(10, 8, 15); // 제품 객체 생성
		// 파일 가져오기
		try {
			File file = new File("D:/kiosk/Output.txt");
			
			BufferedReader file_reader = new BufferedReader(new FileReader(file));
			
			String[] str = file_reader.readLine().split("/");
			
			
			pd.coke = Integer.parseInt(str[0]);
			pd.sprite = Integer.parseInt(str[1]);
			pd.fanta = Integer.parseInt(str[2]);
			
			file_reader.close();
		}catch(FileNotFoundException e) {
			e.getStackTrace();
		}catch (Exception e) {
			e.getStackTrace();
		}
		// 파일 작성하기
		try {
		    OutputStream output = new FileOutputStream("D:/kiosk/Output.txt");
		    String str = pd.coke + "/" + pd.sprite + "/" + pd.fanta;
		    byte[] by=str.getBytes();
		    output.write(by);
				
		} catch (Exception e) {
	            e.getStackTrace();
		}
		
		// 장바구니
		int basket_Co = 0;
		int basket_Sp = 0;
		int basket_Fa = 0;
		while(true) {
			
			System.out.println("1. 콜라 2. 사이다 3. 환타 4. 결제");
			
			int menu = sc.nextInt(); 
			
			switch (menu) {
			case 1: { // 콜라
				if(pd.coke > 0) { 
					System.out.println("장바구니에 Coke가 담겼습니다.");
					basket_Co += 1;
					pd.coke-=1;
				}
				else System.out.println("재고가 부족합니다.");
				
				break;
			}
			case 2: { // 사이다
				if(pd.sprite > 0) { 
					System.out.println("장바구니에 Coke가 담겼습니다.");
					basket_Sp += 1;
					pd.sprite-=1;
				}
				else System.out.println("재고가 부족합니다.");
				
				break;
			}
			case 3: { // 환타
				if(pd.fanta > 0) { 
					System.out.println("장바구니에 Coke가 담겼습니다.");
					basket_Fa += 1;
					pd.fanta-=1;
				}
				else System.out.println("재고가 부족합니다.");
				
				break;
			} 
			case 4: { // 결제
				System.out.println("----------------------");
				System.out.println("제품명   수량  가격");
				
				// 만약 장바구니에 담았다면 보여주기
				if(basket_Co > 0) {
					System.out.println("콜라 " + basket_Co + " " + basket_Co*300 );
				}
				if(basket_Sp > 0) {
					System.out.println("사이다 " + basket_Sp + " " + basket_Sp*400 );
				}
				if(basket_Fa > 0) {
					System.out.println("환타 " + basket_Fa + " " + basket_Fa*500 );
				}
				
				System.out.println("1. 결제 2. 취소");
				int payMent = sc.nextInt();
				
				if(payMent == 1) { // 결제 
					System.out.println("금액을 입력해주세요.");
					int money = sc.nextInt();
					int sum = basket_Co*300+basket_Sp*400+basket_Fa*500;
					if(money-sum < 0) { // 돈이 없다면
						System.out.println("금액이 부족합니다. 결제가 취소됩니다.");
						basket_Co = 0; basket_Fa = 0; basket_Sp = 0;
						
						// 가져오기 ( 제품 재고 초기화 )
						try {
							File file = new File("D:/kiosk/Output.txt");
							BufferedReader file_reader = new BufferedReader(new FileReader(file));
							String[] str = file_reader.readLine().split("/");
							
							pd.coke = Integer.parseInt(str[0]);
							pd.sprite = Integer.parseInt(str[1]);
							pd.fanta = Integer.parseInt(str[2]);
						
						}catch(FileNotFoundException e) {
							e.getStackTrace();
						}catch (Exception e) {
							e.getStackTrace();
						}
						System.out.println("-------test-----------");
					}
					else { // 돈이 있다면 
						System.out.println("결제애 성공했습니다!");
						money-=sum;
						System.out.println("남은 금액 : " + money);
						basket_Co = 0; basket_Fa = 0; basket_Sp = 0;
						
						try {
						    OutputStream output = new FileOutputStream("D:/kiosk/Output.txt");
						    String str = pd.coke + "/" + pd.sprite + "/" + pd.fanta;
						    byte[] by=str.getBytes();
						    output.write(by);
								
						} catch (Exception e) {
					            e.getStackTrace();
						}
					}
				}
				else { // 취소
					
					basket_Co = 0; basket_Fa = 0; basket_Sp = 0;
					
					// 가져오기
					try {
						
						File file = new File("D:/kiosk/Output.txt");
						BufferedReader file_reader = new BufferedReader(new FileReader(file));
						String[] str = file_reader.readLine().split("/");
					
						pd.coke = Integer.parseInt(str[0]);
						pd.sprite = Integer.parseInt(str[1]);
						pd.fanta = Integer.parseInt(str[2]);
						
						file_reader.close();
					}catch(FileNotFoundException e) {
						e.getStackTrace();
					}catch (Exception e) {
						e.getStackTrace();
					}
				}
				break;
			}
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
			}
			
		}
	}

}
