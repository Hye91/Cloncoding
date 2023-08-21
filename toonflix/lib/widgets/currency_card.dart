import 'package:flutter/material.dart';

class CurrencyCard extends StatelessWidget {
  //카드에서 변경가능한건 이름, 화폐단위, 양, 화폐 아이콘 일 것이다.
  final String name, code, amount;
  final IconData icon;
  final bool isInverted;

  final _blackColor = const Color(0xFF1F2123);

  const CurrencyCard({
    super.key,
    required this.name,
    required this.code,
    required this.amount,
    required this.icon,
    required this.isInverted,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      clipBehavior: Clip.hardEdge, //Container에서 튀어나온 부분을 어떻게 처리할 것인지를 정하게된다.
      decoration: BoxDecoration(
        color: isInverted
            ? Colors.white
            : _blackColor, //isInverted가 true면 흰색, false 면 검음색이 되게 한다.
        borderRadius: BorderRadius.circular(20),
      ),
      child: Padding(
        padding: const EdgeInsets.all(30),
        child: Row(
          mainAxisAlignment:
              MainAxisAlignment.spaceBetween, //Row main 가로 Column main 세로
          children: [
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  name,
                  style: TextStyle(
                    color: isInverted ? _blackColor : Colors.white,
                    fontSize: 32,
                    fontWeight: FontWeight.w600,
                  ),
                ),
                const SizedBox(
                  height: 10,
                ),
                Row(
                  children: [
                    Text(
                      amount,
                      style: TextStyle(
                        color: isInverted ? _blackColor : Colors.white,
                        fontSize: 20,
                      ),
                    ),
                    const SizedBox(
                      width: 5,
                    ),
                    Text(
                      code,
                      style: TextStyle(
                        color: isInverted ? _blackColor : Colors.white,
                        fontSize: 20,
                      ),
                    ),
                  ],
                ),
              ],
            ),
            Transform.scale(
              //카드의 크기는 변하게 하지 않고 아이콘의 크기만을 변화시키게 된다.
              scale: 2.2,
              child: Transform.translate(
                offset: const Offset(
                  -5,
                  13,
                ), // x, y축으로의 이동하는걸로 사용하는 위젯
                child: Icon(
                  icon,
                  color: isInverted ? _blackColor : Colors.white,
                  size: 88,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
