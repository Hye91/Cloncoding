class Player {
  //class 생성할때는 꼭 타입 명시
  //final String name = 'nico';
  //int xp = 1500;

  //생성자 만들기
  late String name; //late을 붙여서 변수선언만 하고, 값은 나중에 받아오기로함
  late int xp;
  String team;
  int age; //class가 커질수록 positional parameter이 힘들어진다
  // named Constructor parameter 사용하면 좋음

  // Player(String name, int xp) {
  //   this.name = name;
  //   this.xp = xp;
  // } 타입 명시를 반복적으로 해줘야 한다.
  //더 간략한 코드로 변환 (dart의 특징)
  //Player(this.name, this.xp, this.team, this.age); //positional parameter
  Player({
    required this.name, //null인 경우 처리를 위해 required
    required this.xp,
    required this.team,
    required this.age,
  }); //named Constructor parameter
  //메서드 생성
  void sayHello() {
    print("Hi my name is $name"); //this.name이라고 하지 않아도 사용가능
    //메서드 내에 클래스에서의 변수 이름과 중복이 되는 변수가 존재하는 경우에는
    //this.~ 해서 구별을 해붜야하지만, 그렇지 않은 경우에는 dart는 this를
    //사용하지 않아도 구별할수 있게된다.
  }
}

void main() {
  //Player의 instance 생성
  //var player = Player('nico', 1500, 'red', 13);
  var player = Player(
    name: 'nico',
    team: 'red',
    age: 12,
    xp: 4500,
  );
  var player2 = Player(
    name: 'nico',
    team: 'blue',
    age: 14,
    xp: 4600,
  );
  //player.name;
  //player.xp; //인스턴스 생성했으므로 property에 접근 가능!
  print(player.name); //nico로 출력된다.
  //player.name = 'hi'; //final을 붙이면 값을 변경하지 못하게 된다.
  //print(player.name); //hi로 출력된다.

  player.sayHello();
  player2.sayHello();
}
