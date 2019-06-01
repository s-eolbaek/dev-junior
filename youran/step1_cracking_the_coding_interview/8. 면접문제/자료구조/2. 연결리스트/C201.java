import java.util.HashSet;
import java.util.Scanner;

// 1. 중복제거
// 중복제거시 최초 발견된 요소만 남기고 나머지 중복요소는 모두 제거
public class C201 {
    // 1. hashSet 이용해서 중복 확인 - 시간 O(N), 공간 O(N)
    public static void deleteDuplications(LinkedList list){
        HashSet<Object> set = new HashSet<>();
        Node n = list.head;
        Node prev = null; 
        while(n!=null){
            if(set.contains(n.data)) prev.next = n.next; 
            else{
                set.add(n.data);
                prev = n;
            }
            n = n.next;
        }
    }
    //2. Runner 이용 : 임시버퍼를 사용할 수 없을 때 - 시간 O(N^2), O(1)
    public static void deleteDuplications2(LinkedList list){
        Node pointer1 = list.head; //리스트를 순회할 pointer
        Node pointer2 = null; //pointer1과 하나씩 비교할 runner 
        while(pointer1 != null){
            pointer2 = pointer1;
            while(pointer2.next != null){ 
                if(pointer1.data.equals(pointer2.next.data)) pointer2.next = pointer2.next.next;
                else pointer2 = pointer2.next;
            }
            pointer1 = pointer1.next;
        }

    }

    public static void main(String args[]){
        LinkedList<Integer> list = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        while(len-- > 0) list.appendToTail(sc.nextInt());
        list.printList();
        System.out.println();
        //deleteDuplications(list);
        deleteDuplications2(list);
        list.printList();
    }
}
