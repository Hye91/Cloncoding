package chap_04;

public class _05_For {
    public static void main(String[] args) {
        // 반복문 For
        // 의류 매장에 들어가면 인사소리가 들린다
        System.out.println("어서오세요. 나코입니다.");
        // 또 다른 손님이 들어오면?
        System.out.println("어서오세요. 나코입니다.");
        System.out.println("어서오세요. 나코입니다.");
        System.out.println("어서오세요. 나코입니다.");
        System.out.println("어서오세요. 나코입니다.");
        System.out.println("어서오세요. 나코입니다.");
        System.out.println("어서오세요. 나코입니다.");
        // 만약에 인사 법이 바뀌면?
        System.out.println("환영합니다. 나코입니다.");
        System.out.println("환영합니다. 나코입니다.");
        System.out.println("환영합니다. 나코입니다.");

        // 매장의 이름이 바뀌면?
        // 나코 -> 코나
        System.out.println("환영합니다. 코나입니다.");
        System.out.println("환영합니다. 코나입니다.");
        System.out.println("환영합니다. 코나입니다.");
                // alt 누른 상태로 드래그하면 원하는 부분 한번에수정가능
        //동일한 작업 바뀌면 일일이 바꾸는 불편함이 있다.
        System.out.println("---반복문 사용---");
        //반복문 사용 For
        //for (선언 ; 조건 ; 증감) { ...수행 명령 ..} //선언을 조건이 참인동안 증감시키면서 수행
        for (int i = 0; i < 10; i++) { //i가 10보다 작은 동안 문장 반복해서 진행
            //System.out.println("어서오세요. 나코입니다. " + i);
            //System.out.println("환영합니다. 나코입니다. " + i);
            System.out.println("환영합니다. 코나입니다. " + i);
        }
        
        // 짝수만 출력 //인텔리제이에서는 fori까지 치면 기본적 틀 구성
        for (int i = 0; i < 10; i += 2) {
            System.out.print(i);
            //System.out.print(i);을 하게되면 따로 줄바꿈은 하지 않고 출력
        }

        System.out.println(); //줄바꿈만 하게 된다 안하면 0246813579로 출력

        // 홀수만 출력
        for (int i = 1; i < 10; i += 2) {
            System.out.print(i);
        }

        System.out.println();

        // 거꾸로 숫자 5 4 3 2 1
        for (int i = 5; i > 0; i --) {
            System.out.print(i);
        }

        System.out.println();

        // 1부터 10까지의 수들의 합
        // 1 + 2 + ... + 10 == 55
        int sum = 0;
        for (int i = 1; i <= 10 ; i++) {
            sum += i;
            System.out.println("현재까지 총 합은 " + sum + "입니다.");
        }
        System.out.println("1부터 10까지의 모든 수의 총합은 " + sum + "입니다.");

//        for (int i = 1; i <=10 ; i++) {
//            System.out.println(i);} //이때 출력되는 값들을 합쳐주는 계산과정이 필요하다 -> int sum = 0; 설정
//            // sum += i ; -> 1이 나오면 위의 조건에 만족하므로 += i만큼 더해지는걸 반복하게 된다.

    }
}
