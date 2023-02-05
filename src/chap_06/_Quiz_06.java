package chap_06;

public class _Quiz_06 {
    public static String getHiddenData(String data, int index){
        //입력한 데이터의 일부분을 별로 바꾸는 작업
        //전체 데이터 길이에서 index값을 뺀 나머지를 별로 바꾸기
        String hiddenData = data.substring(0, index);
        for (int i = 0; i < data.length() - index ; i++) {
            //for (int i = index; i <data.lenght(); i++)로 해도 된다.
            hiddenData += "*";
        }
        return hiddenData;
    }
    public static void main(String[] args) {
        //개인 정보 중 일부를 비공개로 전환하는 프로그램을 작성하시오
        // 나코딩             나**
        // 990130-1234567    990130-1******
        // 010-1234-5678     010-1234-****

        //개인정보를 비공개로 전환하는 메소드 작성
        //하나의 메소드에서 모든 동작 처리
        //각 정보는 아래 위치부터 비공개 적용
        // 이름 : 2번째 글자 / 주민번호 : 9번째 글자 / 전화번호 : 10번째 글자
        // 메소드 이름 : getHiddenData

        String name = "나코딩";
        String id = "990130-1234567";
        String phone = "010-1234-5678";

        System.out.println("이름 : " + getHiddenData(name,1));
        System.out.println("주민등록번호 : " + getHiddenData(id,8));
        System.out.println("전화번호 : " + getHiddenData(phone, 9));


    }
}
