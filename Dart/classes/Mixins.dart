//Mixins 생성자가 없는 클래스
//extends 와는 다르다. 상속을 통해서는 확장한 클래스는 부모클래스가 되고
// 자식 클래스는 super을 통해서 접글할수 있고, 그 순간 부모클래스의 instance가 된다.

class Strong {
  final double strengthLevel = 1500.99;
}

class QuickRunner {
  void runQuick() {
    print("ruuuuuun!");
  }
}

class Tall {
  final double height = 1.99;
}

//위에서 만든 클래스들을 여러번 재사용할수 있다 with를 통해서

class Horse with Strong, QuickRunner {}

class Baby with Strong {}

enum Team { red, blue }

class Player with Strong, QuickRunner, Tall {
  //with 를 통해서 다른 클래스틔 프로퍼티와 메서드 긁어오는것.
  final Team team;

  Player({
    required this.team,
  });
}

void main() {
  var player = Player(
    team: Team.red,
  );

  player.runQuick(); //with를 통해서 다른 클래스의 메서드도 사용할수 있다.
}
