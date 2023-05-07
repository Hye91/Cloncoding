void main() {
  //dart에서 const : compile-time constant를 만들어준다
  const name = 'nico';
  //final과 같이 수정이 안된다.
  //compile-time에 알고 있는 값이어야한다?
  const API = '121212';
  // 바뀌지 않고, 컴파일이 될때 그 값을 알고 있게 된다.

  // 앱스토어에 올리기 전에 알고 있는 값!
  // 어떤 값인지 모르고, API에서 받아와야하거나, 사용자가 입력해야하는
  //값이면 const 사용할 수없다!
  //앱에서 사용할 상수들이 있다면 const 사용
  const max_allowed_price = 120;

  //final의 경우 사용자가 앱을 실행하면서 변수를 만들수 있다.
}
