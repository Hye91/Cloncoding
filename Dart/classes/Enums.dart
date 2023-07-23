import 'FristClass.dart';

enum Team { red, blue }

enum XPLevel { beginner, medium, pro }

class Player {
  //생성자 만들기
  String name; //late을 붙여서 변수선언만 하고, 값은 나중에 받아오기로함
  XPLevel xp;
  //String team;
  Team team;

  //생성자의 이름을 조금 바꾼 형태
  Player({required this.name, required this.xp, required this.team});

  //메서드 생성
  void sayHello() {
    print("Hi my name is $name");
  }
}

void main() {
  var nico = Player(name: 'nico', xp: XPLevel.beginner, team: Team.red);
  // team 속성의 스펠링을 틀리는 경우가 생길수 있다
  // flex를 felx라고 쓴다던지하는 오타실수
  // 이런걸 줄이고자 enum을 사용할수 있다.
  //enum 설정을 통해서 red 또는 blue를 설정할수 있게된다.

  var potato = nico
    ..name = 'las'
    ..xp = XPLevel.medium
    ..team = Team.blue
    ..sayHello();
}
