package chap_05;

public class _01_Array {
    public static void main(String[] args) {
        // 배열 : 같은 자료형의 값 여러개를 저장하는 연속된 공간
        // ex. 공항 의자(여러개가 붙어 있다)
        // 일반 의자의 경우 다른 위치로 옮길때 하나만을 따로 움직여서 이동 가능
        // 공항 의자같이 붙어 있는 경우 하나만을 따로 떼서 이동하기는 힘들다.
        // 공항 의자 중간에 다른 의자를 집어 넣는 다던지, 그 옆에 다른 의자를 붙인다던지
        // 하는게 불가능하다.

        // 여러 자료를 한꺼번에 관리 하기 위해서 배열이란것을 쓴다.
        // 일반 의자 = 변수 , 공항 의자(크기 모두 동일) = 배열

        String coffeeRose = "아메리카노";
        String coffeeRachel = "카페모카";
        String coffeeChandler = "라떼";
        String coffeeMonica = "카푸치노";

        System.out.println(coffeeRose + " 하나"); //아메리카노 하나
        System.out.println(coffeeRachel + " 하나");
        System.out.println(coffeeChandler + " 하나");
        System.out.println(coffeeMonica + " 하나");
        System.out.println("주세요");

        //만약 100잔 이상을 주문해야 하면 변수를 100개를 만들어야하고
        //사람마다 변수명도 다 다르기때문에 헷갈릴 수 있다. -> 배열 활용할 수 있다.

        // 배열 선언 첫번째 방법.
        // 4명이 의자에 앉는다고 하면, 4자리에 해당하는 크기 정의 필요.
//        String[] coffees = new String[4]; // 크기 4에 해당하는 스트링 배열 만듦
        // 배열 선언 두번째 방법.
        //String coffees[] = new String[4];

//        coffees[0] = "아메리카노"; //어떤 의자에 앉을지 어떤 위치에 앉을지를 정해야한다.
//                    //[]안에 index값을 지정해줘야 한다. 인덱스 값은 0부터 시작
//        coffees[1] = "카페모카";
//        coffees[2] = "라떼";
//        coffees[3] = "카푸치노";
//        //각각의 위치값을 정해서 넣어줄수 있다

        //위 처럼 하지 않고 배열을 만들면서 동시에 바로 값을 입력할 수도 있다.
        // 배열 선언 세번째 방법
//      String[] coffees = new String[] {"아메리카노", "카페모카", "라떼", "카푸치노"};
        //배열의 크기를 따로 명시하지 않고, 중괄호 속에 값을 넣어주면
        //입력 해둔 값 만큼의 공간을 만든다. -> new String[] <<대괄호 안에 숫자를 넣지 않는다.

        // 배열 선언 네번째 방법
        String[] coffees = {"아메리카노", "카페모카", "라떼", "카푸치노"};

        System.out.println("---구분선---");

        //커피 주문
        System.out.println(coffees[0] + " 하나"); // 아메리카노 하나
        System.out.println(coffees[1] + " 하나");

        //만약 챈들러가 주문하기 직전에 음료를 다른 것으로 바꾸겠다고 했을때...!
        coffees[2] = "에스프레소"; // 변수 업데이트 해주듯이 해주면 된다.
        System.out.println(coffees[2] + " 하나");
        System.out.println(coffees[3] + " 하나");
        System.out.println("주세요");

        //다른 자료형?
        int[] i = new int[3];
        i[0] = 1;
        i[1] = 2;
        i[2] = 3;

        double[] d = new double[] {10.0, 11.2, 13.5};

        boolean[] b = {true, false, false};

        // 배열에 들어가는 값들은 앞에 선언된 자료형으로 들어가야 한다.
    }
}
