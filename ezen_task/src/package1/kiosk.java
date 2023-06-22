package package1;

import java.util.Scanner;

public class kiosk {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int coke = 10;
		int sprite = 8;
		int fanta = 15;
		
		int basket_Co = 0;
		int basket_Sp = 0;
		int basket_Fa = 0;
		
		
		
		
		
		while(true) {
			System.out.println("1. 콜라 2. 사이다 3. 환타 4. 결제");
			int menu = sc.nextInt(); 
			switch (menu) {
			case 1: {
				if(coke > 0) { 
					System.out.println("장바구니에 Coke가 담겼습니다.");
					basket_Co += 1;
					coke-=1;
				}
				else System.out.println("재고가 부족합니다.");
				
				break;
			}
			case 2: {
				if(sprite > 0) { 
					System.out.println("장바구니에 Coke가 담겼습니다.");
					basket_Sp += 1;
					sprite-=1;
				}
				else System.out.println("재고가 부족합니다.");
				
				break;
			}
			case 3: {
				if(fanta > 0) { 
					System.out.println("장바구니에 Coke가 담겼습니다.");
					basket_Fa += 1;
					fanta-=1;
				}
				else System.out.println("재고가 부족합니다.");
				
				break;
			}
			case 4: {
				System.out.println("----------------------");
				System.out.println("제품명   수량  가격");
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
				if(payMent == 1) {
					
					System.out.println("금액을 입력해주세요.");
					int money = sc.nextInt();
					int sum = basket_Co*300+basket_Sp*400+basket_Fa*500;
					if(money-sum < 0) {
						System.out.println("금액이 부족합니다. 결제가 취소됩니다.");
						basket_Co = 0; basket_Fa = 0; basket_Sp = 0;
						coke = 10; sprite = 8; fanta = 15;
					}
					else {
						System.out.println("결제애 성공했습니다!");
						money-=sum;
						System.out.println("남은 금액 : " + money);
						basket_Co = 0; basket_Fa = 0; basket_Sp = 0;
					}
				}
				else {
					basket_Co = 0; basket_Fa = 0; basket_Sp = 0;
					coke = 10; sprite = 8; fanta = 15;
				}
				
				break;
			}
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
			}
			
		}
	}

}
