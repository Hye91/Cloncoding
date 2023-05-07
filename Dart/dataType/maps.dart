void main() {
  var player = {
    'name': 'nico',
    'xp': 19.99,
    'superpower': false,
  };
  // 빈 map 만들기 : 데이터 타입을 내가 지정해서 사용할수있다.
  Map<int, bool> player2 = {1: false, 2: true};

  Map<List<int>, bool> player3 = {
    //복잡한 형태도 key값으로 올수있다.
    [1, 2, 3]: true
  };
}
