package chap_06;

public class _03_Return {
    //메소드에서 어떤 값을 반환해서 메소드를 호출한 곳에서
    //반환된 값을 받아서 어떤 처리할 수도 있다.

    //특정 호텔 정보를 관리하는 프로그램
    // 호텔이 가지고 있는 정보들을 투숙객이나 손님들에게 제공하는 프로그램
    // 호텔 전화번호
    public static String getPhoneNumber () { //전달값이 없는 메소드
        //public static 'void' :반환값이 없는 메소드
        String phoneNumber = "02-1234-5678";
        return phoneNumber; //반환 하기 위해선 return 쓰고 반환 하려는 값을 적으면된다.
        //이때 반환해주는 형태와 정의해줄때 정한 자료형은 똑같아야 한다.
    }
    // 호텔 주소
    public static String getAddress() {
        return "서울시 어딘가";
    }
    // 호텔 액티비티
    public static String getActivities() {
        return "볼링장, 탁구장, 노래방";
    }

    public static void main(String[] args) {
        //반환값, Return
        String contactNumber = getPhoneNumber();
        //contactNumber의 변수에 메소드 getPhoneNumber이 호출이 되게 된다.
        //메소드 정의할때 쓴 phoneNumber와
        //호출시 변수를 phoneNumber으로 써도 둘은 전혀 다른것.
        System.out.println("호텔 전화번호 : " + contactNumber);

        String address = getAddress();
        System.out.println("호텔 주소 : " + address);

//        String activities = getActivities();
//        System.out.println("호텔 액티비티 : " + activities);
        System.out.println("호텔 액티비티 : " + getActivities());
    }
}
