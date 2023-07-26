import 'package:flutter/material.dart';

void main() {
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
