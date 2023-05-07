String sayHello(
    {required String name, required int age, required String country}) {
  return "Hello $name, you are $age, and you come from $country";
} //name Argument 사용하려는 요소들을 {} 안에 넣으면 함수 호출시
//순서에 상관없이 넣으려는 요소 : 값 순으로 (데이터 타입에 맞게) 입력하면
//바로 적용이 가능해진다!

//dart는 null Safety를 지원하므로 만약 요소의 값이 null인경우에 대해서
//오류를 띄우게 된다. 이때 해결가능한 방법 1. 기본값을 설정해준다.
// {String name = 'none', int age = 12, String country = 'none'}
//기본값을 지정하고 싶지않은 경우 2. 요소들의 앞에 required를 붙이면된다.

void main() {
  //print(sayHello('nico', 19, 'korea'));
  //사용자가 요소의 순서를 다 지정해야해서 까먹을수 있다.
  //그리고 요소가 각각 뜻하는게 무엇인지 바로 알수 없음.
  //named Argument 를 여기서 사용하면 좋다.

  print(sayHello(age: 12, country: 'cuba', name: 'noco'));
}
