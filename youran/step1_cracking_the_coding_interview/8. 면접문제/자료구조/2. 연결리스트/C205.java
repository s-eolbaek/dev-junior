import java.util.Scanner;

/*
* 리스트의 합
* 
* 1. 연결리스트로 하나의 숫자를 표현하며, 각 노드는 자릿수 하나를 가리킨다.
* 2. 각 숫자는 역순으로 배열된다.
* 3. 위와 같은 방식으로 표현된 숫자를 담은 리스트 두 개가 제시되면 두 수의 합을 같은 방식의 리스트로 반환한다.
* 
* 숫자값을 문자열로 변환하고 다시 숫자로 변환해서 더하고 그걸 다시 분할....하지 않아도 되는 방법을 찾아보자
* */
public class C205 {

    //1. 나의 풀이: 두 리스트를 순회하며 같은 자릿수부터 더하고 10이 넘으면 다음 차례에 1을 더하기
    public static Node sum(Node<Integer> node1, Node<Integer> node2){
        Node head = node1;
        int carry = 0;
        while(node1 != null){ //node1을 기준으로 순회하며 값을 더한다.
            if(node2 != null){
                node1.data = node1.data + node2.data;
                node2 = node2.next;
            }
            node1.data = node1.data + carry; // 책의 풀이처럼 합계값을 따로 변수로 저장하는게 더 깔끔하겠다
            carry = node1.data / 10; //또는 10 이상일 때만 1 더해주기
            node1.data %= 10; // 한자리값만 남기기
            node1 = node1.next;
        }

        if(node2 != null){ //node2에 남은 자릿수가 있으면 node1 뒤로 이어준다.
            node2.data += carry;
            node1.next = node2;
        }

        return head; //순회 및 연산 전 저장해둔 node1 head를 반환
    }

    /*
     *  2. 책의 풀이: 1과 비슷한 방식을 재귀함수로 구현
     */
    public static Node recursiveSum(Node<Integer> node1, Node<Integer> node2, int carry){
        if( node1 == null && node2 == null && carry == 0) return null;

        Node<Integer> result = new Node<>(); //인자 없는 생성자를 새로 추가함
        int value = carry;
        if(node1 != null) value += node1.data;
        if(node2 != null) value += node2.data;
        result.data = value % 10;

        if(node1 != null || node2 != null){
            Node<Integer> more = recursiveSum( node1 == null ? null : node1.next
                                                , node2 == null ? null : node2.next
                                                ,value >= 10 ? 1 : 0);
            result.next = more;
        }
        return result;
    }

    public static void main(String args[]){
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        while(len-- > 0) list.appendToTail(sc.nextInt());
        int len2 = sc.nextInt();
        while(len2-- > 0) list2.appendToTail(sc.nextInt());
        //Node node = sum(list.head, list2.head);
        Node node = recursiveSum(list.head, list2.head, 0);
        LinkedList<Integer> answer = new LinkedList<>();
        answer.head = node;
        answer.printList();

    }
}
