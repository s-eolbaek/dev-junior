import datastructure.stack.MyStack;

/*
 4. 스택으로 큐 : 스택 두 개로 큐 하나를 구현한 MyQueue 클래스

 stack은 연결리스트로 구현했던 MyStack를 활용함.
 이번에는 책과 나의 해법이 비슷했다.
 스택과 큐의 가장 중요한 차이가 순서라는 점을 먼저 떠올리자.
 스택에서 가장 먼저 입력된 값을 꺼내려면 전체를 다 꺼내서 마지막으로 꺼낸 것을 반환해야 한다.
 하나의 스택에서 꺼낸 요소들을 다른 스택에 순서대로 넣어두면 역순, 즉 입력한 순서대로 요소를 꺼낼 수 있다.
 그러나 하나의 요소를 꺼낼 때마다 다른 스택에 옮겼다가 돌려놓는 작업을 반복하면 
 peek(),remove() 호출시 매번 O(n)의 시간이 소요된다.
 한번 스택을 옮긴 상태에서 똑같은 명령을 연속으로 수행한다면 굳이 이동할 필요가 없다.
 입력을 받을 스택과 출력할 스택을 구분하고 필요할 때만 이동시키는 방법으로 구현할 수 있다.

 ---> 책을 보고 개선한 부분: 나는 add()와 pop() or push()를 오갈 때마다 해당 스택을 비워줬는데,
       책대로 해보니 한번 removeStack에 옮겨놓은 요소는 굳이 다시 addStack으로 옮길 필요가 없다.
       removeStack에 값이 남아있는 상태에서 addStack에 새 요소가 추가되어도,
       removeStack은 완전히 빈 상태가 되기 전까지는 shift하지 않을 것이므로 순서에 지장이 없다!

*/

class MyQueue2 {

    private MyStack<Integer> addStack;
    private MyStack<Integer> removeStack;
    private boolean isAdd;

    MyQueue2(){
        addStack = new MyStack<>();
        removeStack = new MyStack<>();
    }

    void add(int value){
       // if(!removeStack.isEmpty()) shift(removeStack, addStack);
        addStack.push(value);
    }

    int remove(){
       // if(!addStack.isEmpty()) shift(addStack, removeStack);
       if(removeStack.isEmpty()) shift(addStack, removeStack);
        return removeStack.pop();
    }

    int peek(){
        //if(!addStack.isEmpty()) shift(addStack, removeStack);
        if(removeStack.isEmpty()) shift(addStack, removeStack);
        return removeStack.peek();
    }

    void shift(MyStack from, MyStack to){
        System.out.println("shift!");
        while(!from.isEmpty()) to.push(from.pop());
    }

    boolean isEmpty(){
        return addStack.isEmpty() && removeStack.isEmpty();
    }

}

public class C304 {

     public static void main(String [] args){
        MyQueue2 q = new MyQueue2();
        MyQueue original = new MyQueue();
        for(int i=1; i<=5; i++){
            q.add(i);
            original.add(i);
        }
        System.out.println(q.peek());
        System.out.println(original.peek());
        while(!q.isEmpty()) System.out.println("MyQueue: " + q.remove());
        while(!original.isEmpty()) System.out.println("Original: "+original.remove());


    }
}