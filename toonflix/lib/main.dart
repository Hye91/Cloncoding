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

//widget = 레고블록 = class! 매번 new를 통해서 초기화 시켜주지 않아도 된다.
//widget을 쌓아서 하나의 UI를 만들게 된다.

//meterial , cupertino : 구글, 애플 디자인 시스템
//scaffold 앱을 구성할때 팰요한 요소
//class만들고 나서 ,찍어주면 어떤 요소를 하고 있는지 알수 있다!

class App extends StatelessWidget {
  //Root Widget이 된다.
  //StatelessWidget을 사용하면 build 메서드를 구현해야한다
  @override
  Widget build(BuildContext context) {
    //App의 시작점을 정해주는것
    return MaterialApp(
      home: Scaffold(
          backgroundColor: Color(0xff181818), //Colors.하면 지정된 색들을 사용
          body: Padding(
            //전체적인 코드를 padding으로 감싸주는것.
            padding: EdgeInsets.symmetric(horizontal: 40),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              //Row는 수평, Column은 수직배열을 위한것.
              //Row에서는 가로가 main, Column에서는 세로가 main
              children: [
                SizedBox(
                  //사이즈를 가진 빈 박스, 이걸 사용해서 공간을 만든다.
                  height: 80,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.end,
                      //수평방향정렬
                      children: [
                        Text(
                          "Hey, Selena",
                          style: TextStyle(
                            color: Colors.white,
                            fontSize: 28,
                            fontWeight: FontWeight.w800,
                          ),
                        ),
                        Text(
                          "Welcome back",
                          style: TextStyle(
                            color: Colors.white.withOpacity(0.8),
                            fontSize: 18,
                          ),
                        ),
                      ],
                    )
                  ],
                ),
                SizedBox(
                  height: 120,
                ),
                Text(
                  'Total Balance',
                  style: TextStyle(
                    fontSize: 22,
                    color: Colors.white.withOpacity(0.8),
                  ),
                ),
                SizedBox(
                  height: 5,
                ),
                Text(
                  '\$5 194 482',
                  style: TextStyle(
                    fontSize: 48,
                    fontWeight: FontWeight.w600,
                    color: Colors.white,
                  ),
                ),
                SizedBox(
                  height: 30,
                ),
                Row(
                  children: [
                    Container(
                      //html에서 div같은것
                      decoration: BoxDecoration(
                        color: Colors.amber,
                        borderRadius: BorderRadius.circular(45),
                      ),
                      child: Padding(
                        padding: EdgeInsets.symmetric(
                          vertical: 20,
                          horizontal: 50,
                        ),
                        child: Text(
                          'Transfer',
                          style: TextStyle(
                            fontSize: 22,
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ],
            ),
          )),
    );
  }
}
