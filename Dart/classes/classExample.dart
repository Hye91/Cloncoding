class Player {
  //생성자 만들기
  final String name; //late을 붙여서 변수선언만 하고, 값은 나중에 받아오기로함
  int xp;
  String team;

  //생성자의 이름을 조금 바꾼 형태
  Player.fromJson(Map<String, dynamic> playerJson)
      : name = playerJson['name'],
        xp = playerJson['xp'],
        team = playerJson['team'];

  //메서드 생성
  void sayHello() {
    print("Hi my name is $name");
  }
}

void main() {
  var apiData = [
    //리스트 안에 named positional 사용
    {
      "name": "nico",
      "team": "red",
      "xp": 0,
    },
    {
      "name": "lynn",
      "team": "red",
      "xp": 0,
    },
    {
      "name": "dal",
      "team": "red",
      "xp": 0,
    },
  ];

  apiData.forEach((playerJson) {
    var player = Player.fromJson(playerJson);
    player.sayHello();
  });
}
