String sayHello(String name) {
  return "Hello $name nice to meet you!";
}

//String sayHello(String name) => "Hello $name nice to meet you!";
//위 함수와 이렇게 쓴 함수 동일. 이 함수는 바로 위 문장을 return한다는 뜻.

//void로 function이 지정되어있으면 이 함수는 아무것도 return하지 않는다는뜻
//console에 뭔가 출력하고 있는 것뿐. void에 String을 하면 String을
//return하는 것이다.

void main() {
  //단순히 호출되는 메서드 (void로 지정되어있으므로)
  print(sayHello('nico'));
}
