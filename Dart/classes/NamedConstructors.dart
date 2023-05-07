class Player {
  //생성자 만들기
  final String name; //late을 붙여서 변수선언만 하고, 값은 나중에 받아오기로함
  int xp, age;
  String team;

  Player({
    required this.name,
    required this.xp,
    required this.team,
    required this.age,
  }); //named Constructor parameter

  //name과 age만 입력받고, 나머지는 초기화시키는 각각의 두 생성자 만들기
  //Named Constructor
  Player.createBluePlayer({required String name, required int age})
      : this.age = age,
        this.name = name,
        this.team = 'blue',
        this.xp = 0;

  Player.createRedPlayer(String name, int age) //positional 방법사용
      : this.age = age,
        this.name = name,
        this.team = 'Red',
        this.xp = 0;

  //메서드 생성
  void sayHello() {
    print("Hi my name is $name");
  }
}

void main() {
  //두개의 다른 값을 가지는 생성자 만들기
  var player = Player.createBluePlayer(
    name: 'nico',
    age: 12,
  );

  var player2 = Player.createRedPlayer(
    'nico',
    14,
  );

  print(player.name);

  player.sayHello();
  player2.sayHello();
}
