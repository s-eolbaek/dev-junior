## StringBuilder

### 문자열 더하기
- String + operation을 사용할 경우

```java
String joinWords(String][] words){
    String sentence = "";
    for(String w : words){
        sentence = sentence + w;
    }
    return sentence;
}
```
항상 크기가 x인 문자열을 더한다고 가정할 때, + 연산을 이용하면 매번 두 개의 문자열을 읽어들인 후 문자 하나하나를 새로운 문자열에 복사해야 한다. (char array의 형태로 문자를 하나씩 복사한다고 생각해보자) 
-> 시간복잡도 O(xn^2) ( 문자열을 이어붙일 때마다 x + 2x + 3x + ... nx = n(n-1)/2 -> O(N^2))

- StringBuilder는 가변크기 배열을 이용해 필요한 경우에만 문자열을 복사하게끔 해준다. 

## java String issues
- String + 연산도 내부적으로 StringBuilder를 이용하지만 매번 Builder 객체를 생성하므로 연산을 다량 수행해야 할 때는 StringBuilder를 사용하는 것이 좋다. 
- 멀티 스레드 환경에서는 동기화가 되는 StringBuffer 사용
- String.concat() 은 매번 String 객체를 생성해 복사하는 방식

## 더 알아야 할 것
- String과 StringBuilder 어떻게 작동되는지 더 자세히.
- 직접 구현해보기
- 아스키코드, 유니코드 등의 인코딩 포맷에 대해 조사

## references
- [StringBuffer, StringBuilder 가 String 보다 성능이 좋은 이유와 원리](https://cjh5414.github.io/why-StringBuffer-and-StringBuilder-are-better-than-String/)

