package chap_06;

public class _07_VariableScope {
//    public static void methodA(){
//        System.out.println(number);
//    } main 영역 내에서 선언된 변수는 main영역 내에서만 사용가능하다
    //다른 메소드에서는 사용이 불가능.

    public static void methodB (){
        int result = 1; //지역 변수 : 변수가 선언된 영역내에서만 사용가능, {}범위 벗어나면 불가
    }

    public static void main(String[] args) {
        //메소드를 배우기 전까지는 main영역 내에서만 변수를 선언하고 사용했다.
        //메소드를 쓰게 되면 사용할수 있는 변수의 범위가 중요해진다.
        int number = 3; //지역 변수 : 변수가 선언된 영역내에서만 사용가능

        //System.out.println(result);
        //methodB에서 만든 변수 result는 다른 곳에서 사용이 불가능

        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
        //System.out.println(i);
        // for 문 내에서 선언된 i는 {}범위를 벗어나면 사용 불가

        {
            int j = 0;
            System.out.println(j);
            System.out.println(number); //number이 선언된 {}의 범위 내에서는
                                        // 얼마든지 사용 가능하다.
        }
        //System.out.println(j); //{}범위 밖에서의 j는 사용 불가

        //선언된 곳을 벗어가는 곳에서의 변수를 다른 메소드에서 사용하고 싶다면
        //전달 값을 이용해서 변수 입력해주면 사용 가능
        //ex) public static void methodA(int number){}
    }
}
