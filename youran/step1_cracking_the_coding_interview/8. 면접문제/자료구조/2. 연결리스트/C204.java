import java.util.Scanner;
/*
* 분할 : x보다 작은 값은 왼쪽그룹으로, 같거나 큰 값은 오른쪽 그룹으로
* 그룹 내 순서는 관계없으며 x값이 그룹 사이에 위치할 필요는 없음
*
* 연결리스트를 순회하면서 x보다 큰 값이 나오면 맨 끝으로 보내는 방식으로 풀어보았으나 실패
* (원소를 이동하면서도 모든 원소를 한번씩 순회하고 끝내는 게 뜻대로 되지 않아서 무한루프에 갇힘)
* 여기서 제시하는 해법은 리스트를 새로 만드는 방식이다.
*
* */
public class C204 {

    // 1. 두 개의 그룹으로 나누어 리스트를 만들고 병합 (원소의 원래 순서를 유지하므로 안정적)
    public static Node partition(Node node, Integer x){
       Node before1 = null;
       Node before2 = null;
       Node after1 = null;
       Node after2 = null;

       while(node != null){
           Node next = node.next;
           node.next = null; //기존 연결정보를 제거하고 data만 남기기
           Integer data = Integer.valueOf(node.data.toString());
           if(data < x){
               if(before1 == null){
                   before1 = node;
                   before2 = before1;
               }else{
                   before2.next = node;
                   before2 = before2.next;
               }
           }else{
               if(after1 == null){
                   after1 = node;
                   after2 = after1;
               }else{
                   after2.next = node;
                   after2 = after2.next;
               }
           }
           node = next;
       }

       if(before1==null) return after1;
       before2.next = after1;
       return before1;
    }


    //. 하나의 새로운 리스트 만들기
    public static Node partition2(Node node, Integer x){
        Node head = node;
        Node tail = node;
        while(node != null){
            Node next = node.next;
            Integer data = Integer.valueOf(node.data.toString());
            if(data < x){
                node.next = head; //노드 뒤에 기존 head를 연결
                head = node; // head가 바뀜
            }else{
                tail.next = node; // 기존 tail 뒤에 노드 연결
                tail = node; //tail을 맨 끝으로 이동
            }
            node = next;
        }
        tail.next = null; //기존 노드의 연결정보 제거

        return head; //새로운 head 반환
    }

    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        while(len-- > 0) list.appendToTail(sc.nextInt());

        Node head =  partition(list.head, 5);
        LinkedList<Integer> result = new LinkedList<>();
        result.head = head;
        result.printList();
    }
}
