package chap_02;

public class _04_Operator4 {
    public static void main(String[] args) {
        //논리 연산자
        boolean 김치찌개 = true;
        boolean 계란말이 = true;
        boolean 제육볶음 = true;
            //셋 중에 하나라도 식당에 있으면 괜찮은 식당!
            //그래서 셋중 하나라도 있으면 true로 출력해보기
        System.out.println(김치찌개 || 계란말이 || 제육볶음);
            //자바에서는 or이 ||으로 표시 //하나라도 true이면 좋은 식당
        System.out.println(김치찌개 && 계란말이 && 제육볶음);
            //3개가 다 있어야 최고의 식당인 경우
            //자바에서 and 는 &&으로 표시

        //불리안 아니고 연산의 값이 셋중 하나만 맞아도 true
        //셋 모두 맞아야 true인 경우도 출력할수 있다.

        // And 연산
        System.out.println((5 > 3) && (3 > 1));
            //5는 3보다 크고 3은 1보다 크다 (true)
        System.out.println((5 > 3) && (3 < 1));
            //5는 3보다 크고, 3은 1보다 작다 (false)

        //Or 연산
        System.out.println((5 > 3) || (3 > 1));
            //5는 3보다 크거나 3은 1보다 크다 (true)
        System.out.println((5 > 3) || (3 < 1));
            //5는 3보다 크거나 3은 1보다 작다 (true)
        System.out.println((5 < 3) || (3 < 1));
            //5는 3보다 작거나 3은 1보다 작다 (false)

        //System.out.println(1 < 3 < 6); 연속적인 비교는 불가능.
        //System.out.println(1 < 3 && 3 < 6); 이렇게는 가능. 괄호없어도 연산은 가능하나
        // System.out.println((1 < 3) && (3 < 6))
        //가독성을 위해 괄호로 나누어주는게 좋다.

        //논리 부정 연산자
        System.out.println(!true); //false
        System.out.println(!false); //true
        System.out.println(! (5 == 5)); // false
        System.out.println(! (5 == 3)); //true
        // !붙이면 식의 결과의 반대값이 출력
    }
}
