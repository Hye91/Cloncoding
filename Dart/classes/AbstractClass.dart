//추상화 클래스
//다른 클래스들이 직접 구현해야하는 메서드들을
// 모아놓은 일종의 청사진 interface랑 비슷한건가

abstract class Human {
  void walk();
  //Human 이라는 클래스는 walk라는 메서드를 가지고
  // void를 반환한다는걸 지정.
}

class Player extends Human {
  void walk() {
    //이 메서드를 지정하지 않으면 Player클래스 오류뜬다
    print("im walk");
  }
}

class Coach extends Human {
  void walk() {
    print("im a Coach");
  }
}

void main() {}
