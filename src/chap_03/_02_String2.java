package chap_03;

public class _02_String2 {
    public static void main(String[] args) {
        String s = "I like Java and Python and C.";

        // 문자열 변환
            // " and"라는 문자열을 ","로 바꾸는 작업
        System.out.println(s.replace(" and", ","));
        System.out.println(s.substring(7));
            //인덱스 기준 7부터 시작 (이전 내용은 삭제) 단, s변수에 변환결과가 반영되는건 아님
        System.out.println(s.substring(s.indexOf("Java")));
            //7이라는 인덱스 위치를 우리가 직접 입력하는게 아니고
            //s.indexOf로 Java의 위치를 알아낸다음 그 위치이후의 내용 출력
        System.out.println(s.substring(s.indexOf("Java"),s.indexOf(".")));
            //입력한 시작위치는 포함하되, 끝위치 '직전'까지 포함하는 내용 출력. 끝위치는 포함X

        // 공백 제거
        s =  "    I love Java.      ";
        System.out.println(s);
        System.out.println(s.trim());

        // 문자열 결합
        String s1 = "Java";
        String s2 = "Python";
        System.out.println(s1 + s2); //JavaPython
        System.out.println(s1 + "," + s2); //이렇게 표현해도 되지만
            //문자열을 넣어야하는게 복잡해지면 어려울수 있다. //Java,Python
        System.out.println(s1.concat(",").concat(s2)); //Java,Python
                //concat 문자열 결합 기능

    }
}
