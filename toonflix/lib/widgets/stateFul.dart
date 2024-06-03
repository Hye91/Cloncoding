import 'package:flutter/material.dart';

void main() {
  runApp(const App());
}

class App extends StatefulWidget {
  const App({super.key});

  @override
  State<App> createState() => _AppState();
  //단순히 StateFul상태를 나타낸다
}

class _AppState extends State<App> {
  @override
  Widget build(BuildContext context) {
    return const MaterialApp();
  }
}
