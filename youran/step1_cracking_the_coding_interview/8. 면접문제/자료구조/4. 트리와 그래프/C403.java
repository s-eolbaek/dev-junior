import java.util.ArrayList;
import java.util.LinkedList;

/*
*  이진트리가 주어졌을 때 같은 깊이에 있는 노드를 연결리스트로 연결하기
*
*  시간복잡도는 너비,깊이우선 모두 O(n)
*  공간은 재귀함수를 호출하는 깊이우선이 더 많이 소비하지만
*  두 알고리즘 모두 O(n)의 데이터를 반환해야 하므로 재귀호출시 요구되는 추가 공간은 의미있는 차이는 아니다.
*
*  테스트 코드에서도 배울 게 많다***
*  배열을 이용해 트리를 생성하는 함수
*  iterator 이용해 트리의 데이터를 출력해주는 함수
*  모두 문제 풀다보면 자주 쓸만한 코드
*
*
*
* */
public class C403 {

    //내가 풀어본 코드
    //책에서 제시한 첫번째 방법과 유사. pre-order(현재노드,좌,우 순으로 방문) 알고리즘을 살짝 변형한 것이라고.
    // 책에서는 배열 대신 arrayList를 사용했고,
    // 각 깊이에서의 연결리스트를 탐색과정에서 초기화한다. 그렇게 하는 게 바람직하긴 하겠다.
    public static void search(LinkedList<TreeNode>[] lists, TreeNode node, int depth){
        if(node == null) return;

        lists[depth].add(node);
        if(node.left!=null) search(lists, node.left, depth+1);
        if(node.right!=null) search(lists, node.right, depth+1);
    }

    // 너비우선탐색 이용한 예제
    public static ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root){
        ArrayList<LinkedList<TreeNode>> result = new ArrayList<>();
        LinkedList<TreeNode> current = new LinkedList<>();
        if(root != null){
            current.add(root);
        }
        while(current.size() > 0){
            result.add(current);
            LinkedList<TreeNode> parents = current;
            current = new LinkedList<>();
            for(TreeNode parent : parents){
                if(parent.left != null) current.add(parent.left);
                if(parent.right != null) current.add(parent.right);
            }
        }
        return result;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        LinkedList<TreeNode>[] lists = new LinkedList[root.height()];
        for(int i=0; i<lists.length; i++){
            lists[i] = new LinkedList<TreeNode>();
        }
        search(lists, root, 0);
        ArrayList<LinkedList<TreeNode>> result = createLevelLinkedList(root);
        for(int i=0; i< result.size(); i++){
            System.out.println("******** depth "+ i + "*********");
            for(TreeNode node : result.get(i)){
                System.out.print(node.data+" ");
            }
            System.out.println();
        }
      /*  for(int i=0; i<lists.length; i++){
            System.out.println("******** depth "+ i + "*********");
            while(!lists[i].isEmpty())
                System.out.println(lists[i].poll().data);
        }*/


    }
}
