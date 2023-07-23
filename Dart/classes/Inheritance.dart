class Human {
  final String name;
  //Human({required this.name}); //부모클래스의 생성자
  Human(this.name);
  void sayHello() {
    print("Hi my name is $name");
  }
}

enum Team { red, blue }

class Player extends Human {
  final Team team;

  //Player({required this.team, required String name}) : super(name: name);
  Player({
    required this.team,
    required String name,
  }) : super(name); //dart에서 부모클래스를 사용할때 super을 이렇게 사용함
  //부모클래스의 생성자를 호출함.
  //super을 통해서 name을 Human 클래스에 전달한다. 부모클래스와 상호작용하게함.

  @override
  void sayHello() {
    super.sayHello(); //부모클래스의 메서드를 가져옴.
    print("and I play for ${team}");
  }
}

void main() {
  var player = Player(team: Team.red, name: "nico");
  player.sayHello();
}
