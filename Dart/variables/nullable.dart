void main() {
  //null safety
  //개발자가 null을 '참조'할수 없도록 하는것
  //null safety가 없으면 런타임에러가 발생한다.
  String nico = 'nico';
  // nico = null; nico는 String이어야 하므로 불가능

  //지정한 타입말고 null도 될수 있게하려면
  // (부재를 나타내거나, 그 자체가 유효한 상태를 나타낼때)
  String? mimi = 'mimi';
  // null이 될수도 있게 한 다음, 변수를 사용하기 전에 확인이 가능해진다.
  mimi = null;
  // mimi.length; 컴파일 에러가 뜬다
  if (mimi != null) {
    mimi.length; //컴파일에러가 뜨지 않는다
  }
  mimi?.isNotEmpty; //null인지 확인하고, 아니라면 실행 할수 있게 해준다

  //dart에는 null safety가 있고
  // '?'를 타입뒤에 적으면 null값이 있다고 컴파일 에러를 보여주게 된다.
  // null값을 참조하지 않도록 도와주는것
}
