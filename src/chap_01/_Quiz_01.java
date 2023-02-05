package chap_01;

public class _Quiz_01 {
    public static void main(String[] args) {
        //버스 번호는 "1234", "상암08"과 같은 형태 //문자+정수형태
        //남은 시간은 분단위 (예: 3분, 5분) //정수형태
        //남은 거리는 km단위 (예 : 1.5km, 0.8km) //실수형태

        // 실행결과
        // ㅇㅇ번 버스
        //약 n분 후 도착
        //남은 거리 nnkm

        String busNo = "상암08";
        int minute = 3;
        double distance = 1.5; //float 사용해도 가능

        System.out.println(busNo + "번 버스");
        System.out.println("약 " + minute + "분 후 도착");
        System.out.println("남은 거리 " + distance + "km");
    }
}
