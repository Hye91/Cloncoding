void main() {
  var numbers = {1, 2, 3, 4}; //set은 {}, list는 []
  Set<int> numbers2 = {1, 2};

  //set과 list의 차이점
  //set 내부의 값들은 unique

  numbers.add(1);
  numbers.add(1);
  numbers.add(1);
  numbers.add(1);
  print(numbers);
  // 이렇게 값을 추가하고 실행해도 1 2 3 4만 적용된다. 중복불가

  List<int> numbers_2 = [1, 2, 3, 4];
  numbers_2.add(1);
  numbers_2.add(1);
  numbers_2.add(1);
  numbers_2.add(1);
  print(numbers_2);
  // list에는 값을 추가하면 추가하는 대로 값이 다 들어감
}
