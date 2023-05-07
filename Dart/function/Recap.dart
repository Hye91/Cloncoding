String sayHello({
  required String name,
  required int age,
  required String country,
}) {
  return "Hello $name, you are $age, and you come from $country";
}

//입력 파라미터가 3개 이상인 경우 position parameter 말고
//named parameter 을 사용하는게 더 편하다
void main() {
  sayHello(age: 10, country: 'cuba', name: 'noci');
}
