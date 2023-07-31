import 'package:flutter/material.dart';

class Button extends StatelessWidget {
  //재사용이 가능한 클래스로 구현하기 위해서
  //커스텀이 되는 상태로 저장이 되길 원한다.
  // 버튼이름, 글자색깔, 버튼색깔은 커스텀이 가능하게 저장하려고함.

  final String text;
  final Color bgColor;
  final Color textColor;

  const Button(
      {super.key,
      required this.text,
      required this.bgColor,
      required this.textColor});
  //변수를 생성하거나 버튼이 프로퍼티들을 가지고 있다고 설정해주기 전에
  //생성자를 만들어야한다.!

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: bgColor,
        borderRadius: BorderRadius.circular(45),
      ),
      child: Padding(
        //커스텀이 된 프로퍼티를 사용하기 때문에 더이상 const를 가질수 없게되서 지움.
        padding: const EdgeInsets.symmetric(
          vertical: 20,
          horizontal: 50,
        ),
        child: Text(
          text,
          style: TextStyle(
            color: textColor,
            fontSize: 22,
          ),
        ),
      ),
    );
  }
}
