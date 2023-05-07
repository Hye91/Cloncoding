typedef ListOfInts = List<int>; //alias, Map이나 Set에 적용가능

//List<int> reverseListOfNumbers(List<int> list) {
ListOfInts reverseListOfNumbers(ListOfInts list) {
  var reversed = list.reversed;
  return reversed.toList();
}

void main() {
  print(reverseListOfNumbers([1, 2, 3]));
}
