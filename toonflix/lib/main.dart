import 'package:flutter/material.dart';

// class Player {
//   String? name; // ?를 한 순간부터 name을 가질수도 안가질수도 있다
//   required가 아니라는 이야기.
//   Player(); // ?만으로 안에 굳이 값을 가지지 않아도 오류가 뜨지 않는다.
//   Player({required this.name}); //중괄호 안에 표시하면 named Constructor
//   Player(this.name); 이렇게 하는게 positional 방법 -> 위치를 정확히 해줘야함
// }

void main() {
  //var nico = Player();
  runApp(App());
}

//widget = 레고블록
//widget을 쌓아서 하나의 UI를 만들게 된다.

class App extends StatelessWidget {
  //Root Widget이 된다.
  //StatelessWidget을 사용하면 build 메서드를 구현해야한다
  @override
  Widget build(BuildContext context) {
    //App의 시작점을 정해주는것
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          elevation: 5,
          title: Text('Hello Flutter'),
        ),
        body: Center(
          child: Text('HelloWorld'),
        ),
      ),
    );
    //meterial , cupertino : 구글, 애플 디자인 시스템
    //scaffold 앱을 구성할때 팰요한 요소
    //class만들고 나서 ,찍어주면 어떤 요소를 하고 있는지 알수 있다!
  }
}
