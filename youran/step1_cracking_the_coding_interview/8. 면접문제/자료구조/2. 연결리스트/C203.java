import java.util.Scanner;

/*
*  중간노드 삭제
*
*  조건 1. 처음과 끝을 제외한 중간 위치 어디든 관계없다.
*  조건 2. 삭제할 노드에만 접근할 수 있다.
*
*  --> 삭제할 노드에만 접근할 수 있다는 말은 해당 노드만 변경할 수 있다는 의미?
*      조건2의 제약이 없다면 삭제 대상 직전 노드의 next를 변경하는 방식을 쓸 수 있겠지만
*      여기서는 삭제할 노드의 data를 변경하는 방식으로 삭제를 구현해야 한다.
* */
public class C203 {

    public static void deleteThisNode(Node node){
        if(node != null && node.next != null){
            node.data = node.next.data;
            node.next = node.next.next;
        }
    }
    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        while(len-- > 0) list.appendToTail(sc.nextInt());

        deleteThisNode(list.head.next);  //두번째 노드 삭제
        list.printList();

    }
}
