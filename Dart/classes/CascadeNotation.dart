import 'FristClass.dart';

class Player {
  //생성자 만들기
  String name; //late을 붙여서 변수선언만 하고, 값은 나중에 받아오기로함
  int xp;
  String team;

  //생성자의 이름을 조금 바꾼 형태
  Player({required this.name, required this.xp, required this.team});

  //메서드 생성
  void sayHello() {
    print("Hi my name is $name");
  }
}

void main() {
  var nico = Player(name: 'nico', xp: 1200, team: 'red');
  //여기서 nico의 데이터를 바꿔주려면
  // nico.name = ~;
  // nico.team =~;
  // nico.xp =~ ;이런식으로 입력하지 않고
  var potato = nico
    ..name = 'las'
    ..xp = 1200000
    ..team = 'blue' //이런식으로 입력해서 한번에 바꿀 수 있다.
    ..sayHello();
}
