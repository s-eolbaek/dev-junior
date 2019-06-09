package datastructure.stack;

import java.util.EmptyStackException;

//연결리스트로 구현한 스택
/*

   Last In First Out
*  배열로 구현하면 상수시간안에 i번째 요소에 접근할 수 있다는 장점이 있음
*  반면 연결리스트로 구현하면 상수시간 안에 pop, push 가능함
*
*  스택과 재귀 알고리즘
*  스택은 재귀 알고리즘을 사용할 때 유용.
*  -----------------> 스택을 이용해 재귀 알고리즘을 반복적 형태(iterative)로 바꿔서 구현해보기
*
* */
public class MyStack<T> {

    private static class StackNode<T>{
        private T data;
        private StackNode<T> next;
        public StackNode(T data) {
            this.data = data;
        }
    }

    StackNode<T> top;

    public T pop(){
        if(top == null) throw new EmptyStackException();
        T item = top.data;
        top = top.next;
        return item;
    }

    public void push(T item){
        StackNode node = new StackNode(item);
        node.next = top;
        top = node;
    }

    public T peek(){
        if(top == null) throw new EmptyStackException();
        return top.data;
    }

    public boolean isEmpty(){
        return top == null;
    }
}
