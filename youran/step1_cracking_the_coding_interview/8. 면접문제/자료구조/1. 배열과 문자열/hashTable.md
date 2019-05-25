## Hash Table

*컴퓨팅에서 키를 값에 매핑할 수 있는 구조인, 연관 배열 추가에 사용되는 자료 구조이다. 해시 테이블은 해시 함수를 사용하여 색인(index)을 버킷(bucket)이나 슬롯(slot)의 배열로 계산한다.* [위키백과](https://ko.wikipedia.org/wiki/%ED%95%B4%EC%8B%9C_%ED%85%8C%EC%9D%B4%EB%B8%94)

*해시함수를 사용하여 키를 해시값으로 매핑하고, 이 해시값을 색인(index) 혹은 주소 삼아 데이터의 값(value)을 키와 함께 저장하는 자료구조를 해시테이블(hash table)이라고 합니다. 이 때 데이터가 저장되는 곳을 버킷(bucket) 또는 슬롯(slot)이라고 합니다. 해시테이블의 기본 연산은 삽입, 삭제, 탐색(search)입니다.* [ratsgo's blog](https://ratsgo.github.io/data%20structure&algorithm/2017/10/25/hash/)

### 구현하기
(0. 임의의 key값을 일정한 크기의 값으로 변환하는 해시함수를 만든다.)
1. 해시함수를 이용해 각 key의 해시코드를 계산한다. 
2. hash(key)% array_length 와 같은 방식으로 해시코드를 이용해 배열의 인덱스를 구한다.
3. key와 value를 연결리스트의 해당 인덱스에 저장한다.

![해시테이블의 예](https://ko.wikipedia.org/wiki/%ED%95%B4%EC%8B%9C_%ED%85%8C%EC%9D%B4%EB%B8%94#/media/File:Hash_table_3_1_1_0_1_0_0_SP.svg)

### 해시 충돌
key의 개수는 무한히 커질 수 있지만, key나 해시코드, 인덱스의 값은 한정적이다. 따라서 여러 개의 키가 같은 해시코드를 가리키거나 각각 다른 해시코드가 하나의 인덱스를 가질 수 있다. 이러한 경우를 충돌이라고 한다.
**해시테이블의 시간 복잡도는 O(1)이지만 충돌이 자주 발생하는 최악의 경우 O(N)이 된다.** (N = 키의 개수)

### 더 알아야 할 것들
- 책에서는 key->hashcode->index의 순서로 변환하는데 다른 설명을 보면 key->hashcode = index 라고 되어 있는 경우도 있어서 헷갈린다. 
- 자바에도 hashTable객체가 구현되어 있지만, 똑같은 기능을 하면서도 성능이 지속적으로 개선되고 있는 hashMap을 이용하는 것을 권장한다고 한다. 
- 충돌을 해결하기 위한 다양한 방법들
- 해시테이블 직접 구현하기
- 균형이진탐색트리로 구현하기(트리를 공부한 후에...)

### references
- [위키백과 - 해시테이블](https://ko.wikipedia.org/wiki/%ED%95%B4%EC%8B%9C_%ED%85%8C%EC%9D%B4%EB%B8%94#/media/File:Hash_table_3_1_1_0_1_0_0_SP.svg)
- [ratsgo's blog - 해싱,해시함수,해시테이블](https://ratsgo.github.io/data%20structure&algorithm/2017/10/25/hash/)
- [Naver D2 - Java HashMap은 어떻게 동작하는가?](https://d2.naver.com/helloworld/831311)
- [Implementing our own hash table with Separate Chaining in Java](https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/)