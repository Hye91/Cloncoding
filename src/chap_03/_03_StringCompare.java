package chap_03;

public class _03_StringCompare {
    public static void main(String[] args) {
        //문자열 비교
        String s1 = "Java";
        String s2 = "Python";
            //s1과 s2가 똑같은지 비교
        System.out.println(s1.equals(s2)); //두 내용이 같으면 true, 아니면 false
        System.out.println(s1.equals("Java")); //s1의 Java와 입력한 문자열 Java가 같은지 비교
        System.out.println(s2.equals("python")); //대소문자를 구별하기때문에 false
        System.out.println(s2.equalsIgnoreCase("python"));
            //대소문자 구별없이 내용자체만 같은지 알아볼때 equalsIgnoreCase 사용

        //☆문자열 비교 심화☆
        // 비밀번호를 입력해야 출입할수 있는 식당화장실
        // 비밀번호를 식당의 한 귀퉁이에 벽에 붙여놓음.
        s1 = "1234"; //벽에 붙은 메모지의 비밀번호 정보 (참조)
            //모든 손님이 지나가면서 이 메모지의 비밀번호를 보고 가서 누른다.
            // 메모리의 어떤 공간에 1234라는 데이터가 고정되어있다.
        s2 = "1234"; // s2를 또 다른 메모리 공간에 1234 값을 저장해두지 않고
                    // s1에 선언되어 있는 메모리 공간을 같이 그대로 쓰게 된다.
        System.out.println(s1.equals(s2)); // true (내용비교)
        System.out.println(s1 == s2); // ture (참조비교) //숫자 비교시 ==으로 같은지 비교
            //문자열에서도 ==으로 비교하는 경우 있다.
            //1234라는 메모지는 하나만 있고, 그 메모지 값을 s1과 s2 스트링변수가 '참조'하는것

        //다른 방식의 문자열 선언 방법.
        s1 = new String("1234"); //각각 서로 다른 메모지의 비밀번호(다른 참조)
            //손님들에게 각각 화장실 비밀번호가 적힌 메모지를 나눠주게 된다.
        s2 = new String("1234"); // 서로 참조하는 곳이 서로 다르게 된다.
            //서로 다른 어떤 메모리의 공간에 각각 s1 s2로 저장이 된다. ->참조가 달라진다.
        System.out.println(s1.equals(s2)); //true
        System.out.println(s1 == s2); //false
        //문자열에서 내용을 비교하려면 ==가 아닌 equals 사용
    }
}
