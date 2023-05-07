String sayHello(String name, int age,
        [String? /*not required*/ country = 'couaa']) =>
    'Hello $name, you are $age, come from $country';

//named Argument는 하고 싶지않은데, country는 required 하지 않고 싶다면?
// country 에 []을 하고 기본값을 지정해주면 된다.
void main() {
  var results = sayHello('noci', 13 /*, 'cuba'*/);
  print(results);
}
