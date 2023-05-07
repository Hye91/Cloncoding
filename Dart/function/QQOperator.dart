// 물음표 두개 QQ
// 물음표 + =의 결합

//String capitalizeName(String? name) => name.toUpperCase();
//이름을 대문자로 바꾸는 메서드
// 물음표를 붙여서 null일지도 모른다고 하게되면 null값은 대문자로
//바꿀수 없다는 오류가 뜨는데, 이건 if를 통해서 null이 아니면 대문자로 바꾸고
// null이라면 return을 'ANON'으로 바꾸는 방법을 할 수 있다.

// String capitalizeName(String? name) {
//   if (name != null) {
//     return name.toUpperCase();
//   }
//   return 'ANONE';
// }

String capitalizeName(String? name) =>
    //name != null ? name.toUpperCase() : 'ANON';
    // name이 null이 아니면, 대문자변환메서드를 실행하고, 맞다면 'ANON' 출력
    //더 짧게 메서드 만들수 있다.
    name?.toUpperCase() ?? 'ANON';
//물음표를 붇이면 might be null인것.
// left ?? right = left가 null이면 right return.
//left가 null이 아니면 그대로 left return.

// String? name;
// name ??= 'nico' name이 null이면 니코 이름 넣는다.

void main() {
  capitalizeName('nico'); //NICO
  capitalizeName(null);
}
