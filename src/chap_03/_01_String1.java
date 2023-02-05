package chap_03;

public class _01_String1 {
    public static void main(String[] args) {
        String s = "I like Java and Python and C.";
        System.out.println(s);

        // 문자열의 길이
        System.out.println(s.length()); //29
        //상품평 후기 쓸때 몇글자 이상으로 작성하시오 할때 사용가능

        // 대소문자 변환
        System.out.println(s.toUpperCase()); //모두 대문자로 변환
        System.out.println(s.toLowerCase()); //모두 소문자로 변환

        // 포함 관계
        System.out.println(s.contains("Java")); //포함되면 true, 아니면 false
            //파일이나 인터넷에서 데이터를 읽어올때 문자열을 확인하기 어려운 경우
            //문장 속에서 특정 문구 확인할때 사용
        System.out.println(s.contains("C#")); //포함되지 않으면 false
        System.out.println(s.indexOf("Java")); //입력한 문자열의 위치정보, 7
            //문자열의 위치 셀때 처음이 1이 아니고 0부터 시작한다. 0,1,2,3..(인덱스 위치)
        System.out.println(s.indexOf("C#")); //포함되어 있지 않은 문자열은 -1로 반환
        System.out.println(s.indexOf("and")); //문장에서 처음으로 만나는 and의 위치
        System.out.println(s.lastIndexOf("and" )); //문장에서 마지막에 위치하는 and 위치
        //어떤 문자열로 시작하는지 또는 어떤 문자열로 끝나는지,
        //시작 문구와 끝나는 문자를 확인해서 원하는 문장형태가 맞는지 확인할 수 있다.
        System.out.println(s.startsWith("I like")); // 이 문자열로 시작하면 true
        System.out.println(s.endsWith(".")); //이 문장이 .으로 끝나는지, 끝나면 true
    }
}
