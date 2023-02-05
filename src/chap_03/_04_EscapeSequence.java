package chap_03;

public class _04_EscapeSequence {
    public static void main(String[] args) {
        //특수문자, 이스케이프 문자(Escape Sequence, Escape Character, Special Character)
        // \n \t \\ \" \'
        System.out.println("자바가");
        System.out.println("너무");
        System.out.println("재밌어요");

        System.out.println("자바가\n너무\n재밌어요"); // \n : 줄바꿈

        // 해물파전     9000원
        // 김치전      8000원
        // 부추전      8000원
        System.out.println("해물파전\t9000원");
        System.out.println("김치전\t8000원");
        System.out.println("부추전\t8000원"); // \t : 탭 효과

        System.out.println("C:\\program Files\\Java");
                //폴더 경로나 파일경로를 표현할때는 \\을 써야한다
                // \\: 역슬래시 // C:\program Files\Java 이렇게 쓰면 \p나 \J 로 인식

        // 단비가 "냐옹"이라고 했어요 \" : 큰 따옴표
        //System.out.println("단비가 "냐옹"이라고 했어요"); 냐옹이라는 문자열이 인식되지 않음
        System.out.println("단비가 \"냐옹\"이라고 했어요");
        // \' : 작은 따옴표
        System.out.println("단비가 \'뭘 봐?\'라는 표정을 지었어요");
        //System.out.println("단비가 '뭘 봐?'라는 표정을 지었어요"); 라고 해도 똑같이 출력
        // \'을 사용하는 경우는 어떤 경우인가? ↓
        char c = 'A'; //여러 문자열이 아닌 한개의 문자열을 나타내는 변수 char의 경우
        //작은 따옴표를 이용하게 되는데 이럴때 \'을 사용하게 된다.
        // c = ''''; 작은따옴표와 작은 따옴표 사이에 작은 따옴표를 넣어야하는데 인식을 못하고 오류가 뜬다.
        c = '\'';
        System.out.println(c);



    }
}
