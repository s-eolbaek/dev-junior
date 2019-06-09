import java.util.Scanner;

/*
* 뒤에서 k번째 원소 구하기
*
* */
public class C202 {

    //1.리스트의 길이를 안다: 리스트를 순회해서 length-k번째 노드를 찾으면 된다.

    //2. 재귀적 방법 - 노드와 카운터(index)를 모두 저장해 반환하기 위해
    // 카운터값을 감쌀  Wrapper class 작성(또는 배열 사용)
    // (+) 단순히 출력만 해도 된다면 카운터만 반환하면 된다.
    // (++) C++로 구현하면 '참조를 통한 값 전달'로 다수 값 반환이 가능하다.
    // (+++) 카운터를 static변수에 저장하거나 카운터와 노드를 함께 저장할 클래스 객체를 만들 수도 있다.
    // time : O(N)? , space : O(N)
    class Index{
        public int value = 0;
    }

    public Node getKthToLast(Node head, int k){
        Index index = new Index();//재귀스택의 모든 계층에서 카운터 정보를 공유할 수 있다.
        return getKthToLast(head, k, index);
    }

    public Node getKthToLast(Node head, int k, Index index){
        if(head == null) return null;
        Node node = getKthToLast(head.next, k, index);
        index.value = index.value+1;
        if(index.value == k) return head;

        return node;
    }

    //3. 순환적(iterative) 방법 - time: O(N), space: O(1)
    public Node getKthToLast2(Node head, int k){
        Node p1 = head;
        Node p2 = head;
        for(int i=0; i<k; i++){ //p1 포인터의 위치를 k번째로 이동
            if(p1 == null) return null;
            p1 = p1.next;
        }
        while(p1 != null){//head보다 k번째에서 시작한 p1이 먼저 끝에 도달하므로
            p1 = p1.next;
            p2 = p2.next;
        }
        //k번째 자리에 있던 p1은 length-k만큼 이동해야 끝에 도달한다.
        //head에 있던 p2는 length-k만큼 이동하면 뒤에서 k번째 자리에 놓인다.
        return p2;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int k = sc.nextInt();
        while (len-- > 0) list.appendToTail(sc.nextInt());

        C202 c = new C202();
        Node node = c.getKthToLast2(list.head, k);
        System.out.println(node.data);
    }
}
