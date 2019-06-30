import java.util.LinkedList;

// 균형 확인
// 이진트리가 균형 잡혀있는지 확인하기
// 균형잡힌 트리 - 모든 노드에 대해서 왼쪽 부분 트리의 높이와 오른쪽 부분 트리의 높이 차이가 최대 1
public class C404 {
    /*
     * 내가 풀어본 풀이: 왼쪽과 오른쪽의 하위트리 높이 차이가 최대 1이라는 건
     * 만약 어느 단계에서 왼쪽이나 오른쪽 하나에만 노드가 있다면 그게 말단 노드여야 한다는 의미로 이해했다.
     * 그래서 높이값 자체는 구할 필요가 없다고 생각했는데 해답을 보니 내가 문제를 잘못 이해한건지 헷갈린다.
     */
    public static boolean isBalancedBT(TreeNode node) {

        if(node == null) return false;

        if (node.left != null && node.right != null) {
            if(!isBalancedBT(node.left) || !isBalancedBT(node.right)) return false;
        } else if (node.left != null && node.right == null) {
            if(node.left.left != null || node.left.right != null) return false;
        } else if (node.left == null && node.right != null){
            if(node.right.left != null || node.right.right != null) return false;
        }

        return true;
    }

    public static boolean isBalancedBT2(TreeNode root){
        if(root == null) return false;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node.left != null && node.right != null){
                q.add(node.left);
                q.add(node.right);
            }else if(node.left != null && node.right == null) {
                if(node.left.left != null || node.left.right != null) return false;
            }else if(node.left== null && node.right != null) {
                if(node.right.left != null || node.right.right != null) return false;
            }
        }
        return true;
    }

    //책의 해법. 노드의 높이를 계산하면서 균형 여부도 체크하므로 매번 하위 노드 전체를 순회할 필요가 없다
    static int checkHeight(TreeNode root){
        if(root == null) return -1;

        int leftHeight = checkHeight(root.left);
        if(leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int rightHeight = checkHeight(root.right);
        if(rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        int heightDiff = leftHeight - rightHeight;
        if(Math.abs(heightDiff) > 1) return Integer.MIN_VALUE; //균형잡혀있지 않으면 에러값 반환
        else return Math.max(leftHeight, rightHeight) + 1;
    }

    public static boolean isBalanced(TreeNode node){
        return checkHeight(node) != Integer.MIN_VALUE;
    }


    public static void main(String[] args){
       /* TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        */

        // Create balanced tree
        int[] array = {0, 1, 2, 3, 5, 6, 7, 8, 9, 10};
        TreeNode root = TreeNode.createMinBST(array);

        root.insertInOrder(4); // Add 4 to make it unbalanced


        System.out.println(isBalancedBT(root));
        System.out.println(isBalancedBT2(root));
        System.out.println(isBalanced(root));
    }
}
