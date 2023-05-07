void main() {
  var name = 'nico';
  //List
  //list는 Collection If와 Collection for을 지원한다
  var giveMeFive = true;
  var numbers = [
    1,
    2,
    3,
    4, //리스트의 마지막을 ,로 마무리하면 여러줄로 형태가 잡힌다
    if (giveMeFive) 5, //giveMeFive가 true면 요소로 5를 추가한다.
    // if로 존재할 수도 안할수도 있는 요소를 가지고 만들수 있다.
  ];
  var number = [
    1,
    2,
    3,
    4,
    5,
  ];
  print(numbers);

  // string interpolation
  //이미 변수가 존재할때
  var greeting = 'Hello everyone, my name is $name, nice to meet you';
  print(greeting);
  var age = 10;
  // 지정한 age에 +2를 해서 출력하고 싶을때
  var greeting2 = "Hello everyone, my name is $name and I'm ${age + 2}";
  print(greeting2);

  //Collection For
  var oldFriends = ['nico', 'lynn'];
  var newFriends = [
    'lewis',
    'ralph',
    'darren',
    for (var friend in oldFriends) "⭐ $friend",
  ];
  //newFriend에 oldFriend를 살짝 바꿔서 넣으려고 할때
  print(newFriends);
}
