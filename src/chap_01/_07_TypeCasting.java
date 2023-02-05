package chap_01;

public class _07_TypeCasting {
    public static void main(String[] args) {
        //형 변환 TypeCasting
        // 정수형에서 실수형으로
        // 실수형에서 정수형으로

        //int score = 93 + 98.8 ; //연산결과는 실수형태인데 정수형 변수에 넣으니 오류.

        //int to float, double 정수-> 실수
        int score = 93;
        System.out.println(score); //93
        System.out.println((float)score); //93.0
        System.out.println((double) score); //93.0

        //float, double to int 실수 -> 정수
        float score_f = 93.3F;
        double score_d = 98.8;
        System.out.println((int) score_f); //93
        System.out.println((int) score_d); //98

        //정수 + 실수 연산
        score = 93 + (int)98.8; // 93 + 98
        System.out.println(score);
         //정수와 실수의 계산의 값이 실수로 들어갈때에는 오류가 뜨지 않는다
        // 자동으로 double로 변환되어 계산
        score_d = (double)93 + 98.8; // 93.0 + 98.8
        System.out.println(score_d);

        //변수에 형변환된 데이터 집어넣기
        double convertedScoreDouble = score; // 191 -> 191.0
        // double(실수)에 정수형(int) score 넣으면 자동변환
        // int -> long -> float -> double (자동 형 변환)
        // 범위의 크기 : int < long < float < double

        int convertedScoreInt = (int)score_d; //191.8 -> 191
        // 큰범위의 데이터를 작은범위 데이터에 넣으면 일부가 잘린다.
        // double -> float -> long -> int (수동 형변환)

        //정수를 문자열로
        String s1 = String.valueOf(93);
        //String이라는 클래스가 제공해주는 valueOf라는 기능을 써서 93이라는 정수를 문자열로 변환
        s1 = Integer.toString(93);
        //Integer라는 클래스가 제공하는 toString이라는 기능을 써서 정수를 문자열로 변환
        System.out.println(s1); //93

        //실수를 문자열로
        String s2 = String.valueOf(98.8);
        s2 = Double.toString(98.8); //실수의 경우 Double의 toString기능 사용
        System.out.println(s2);

        //문자열을 정수형 숫자로
        int i = Integer.parseInt("93");
        System.out.println(i);

        //문자열을 실수형 숫자로
        double d = Double.parseDouble("98.8");
        System.out.println(d);

        //만약, 괄호 속에 들어가는 데이터가 올바른 정수,실수가 아니라면?
        //int error = Integer.parseInt("자바"); -> 오류
        //""속의 데이터가 올바로 들어가있어야한다.
    }
}
