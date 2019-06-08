package datastructure.queue;

import java.util.NoSuchElementException;
/*
*  연결리스트를 이용한 큐 구현
*
*  First In First Out
*
*  너비우선탐색, 캐시 구현에 유용
*
* */
public class MyQueue<T> {

    private static class QueueNode<T>{
        private T data;
        private QueueNode next;
        public QueueNode(T data){
            this.data  = data;
        }
    }

    QueueNode<T> first;
    QueueNode<T> last;

    public void add(T item){
        QueueNode node = new QueueNode(item);
        if(last != null) last.next = node;
        last = node;
        if(first == null) first = last; //first = node 로 써도 되지만 이쪽이 의미 전달이 더 명확하다.
    }

    public T remove(){
        if(first == null) throw new NoSuchElementException();
        T item = first.data;
        first = first.next;
        if(first == null) last = null; //first , last 노드 모두 갱신해야 함. 주의
        return item;
    }

    public T peek(){
        if(first == null) throw new NoSuchElementException();
        return first.data;
    }

    public boolean isEmpty(){
        return first == null;
    }

}
