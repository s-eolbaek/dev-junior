# 연결리스트 LinkedList

- 차례로 연결된 노드를 표현해주는 자료구조. 
- 단방향: 노드의 끝에만 추가 가능 /양방향: 노드의 앞뒤로 추가 가능 
- 배열과 달리 특정 인덱스를 상수 시간에 접근할 수 없다.
- 리스트의 시작지점에서 아이템을 추가하거나 삭제하는 연산을 상수 시간에 할 수 있다.

## 연결리스트 만들기

```java
//기본적인 단방향 연결리스트 구현 예시
//연결리스트에 접근할 때 head노드의 주소를 참조
//여러 객체가 동시에 연결리스트를 참조하는 도중에 head가 바뀌어도
//여전히 이전 head를 가리키는 객체가 남아있을 수 있으므로 주의해햐 함
// --> Node클래스를 포함하는 LinkedList 클래스를 구현해보라

class Node{ //head만 생성자를 통해 만들어지고 나머지 노드는 모두 head를 참조해 생성 및 연결된다
    Node next = null;
    int data;
    public Node(int d){
        data = d;
    }

    void appendToTail(int d){  // 노드를 추가할 때마다 바로 앞의 노드에 연결됨
        Node end = new Node(d); 
        Node n = this; 
        while(n.next != null){ 
            n = n.next;
        }
        n.next = end; 
    }
}
```

## 단방향 연결리스트에서 노드 삭제
```java
//널 포인터 검사를 반드시 해야 함
//필요하면 head, tail 포인터도 갱신해야 함
//메모리 관리가 필요한 언어로 구현할 때는 삭제한 노드에 할당된 메모리 반환도 반드시 확인
Node deleteNode(Node head, int d){
    Node n = head;
    if(n.data == d){ //삭제하려는 노드가 head 자신이면 다음 노드를 반환해서 head를 변경
     return head.next;
    }
    while(n.next != null){
        if(n.next.data == d){
            n.next = n.next.next; //조건에 맞는 노드가 나타나면 next를 그 다음 노드로 변경 
            return head;
        }
        n = n.next; //data 확인 후 다음 노드로 이동
    }
    return head;
}
```

## Runner 기법
- Runner 부가포인터 : 연결리스트를 순회할 때 두 개의 포인터를 동시에 사용.
- 이 때 한 포인터가 다른 포인터보다 앞서도록 한다.(fast pointer - slow pointer)
- ex) 리스트의 길이가 짝수일 때, 포인터A는 한번에 1씩, 포인터B는 한번에 2씩 이동하게 하면 포인터B가 리스트의 끝에 도달했을 때 포인터A는 리스트의 중앙에 위치하게 된다. 이러한 차이를 이용해서 리스트의 중간위치를 구하거나 새로운 규칙으로 정렬(재배치)할 수 있음

## 재귀 문제
- 연결리스트 관련 문제 가운데 상당수는 재귀 호출에 의존한다.
- 재귀호출 깊이가 n이면 해당 알고리즘이 적어도 O(n)만큼의 공간을 사용한다는 사실을 기억하자.




## 더 해보기
- [연결리스트 구현](https://www.geeksforgeeks.org/implementing-a-linked-list-in-java-using-class/)
  - insert, print, deleteByKey, deleteByPosition
- [LinkedList in Java](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html)  