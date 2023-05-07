void main() {
  //late : var이나 final앞에 붙일수 있는 수식어
  late final String name;
  //late는 초기 데이터 없이 변수를 선언할 수있게 해준다
  //print(name); //데이터를 입력하지 않았기때문에 출력할수 없다고 오류 뜬다.
  // do something, go to api
  name = 'nico';
  //데이터 없이 변수를 만들어주고(필요한 데이터가 아직 없을수도 있다)
  // 그런 다음 API에서 데이터를 받아서 변수에 넣어준다.
}
