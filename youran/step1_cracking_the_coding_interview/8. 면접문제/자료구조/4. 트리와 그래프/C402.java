public class C402 {

    public static void main(String[] args){
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        /*
        책에서 제공하는 TreeNode 클래스를 이용.
        트리 순회 비용을 줄일 수 있다고 함.
        그런데 insertValue 를 재귀실행할 때마다 부모 노드를 넘기면
        매번 루트부터 순회하지 않아도 되는 것 아닌가?
        책에서 제시한 방법1(root.insertValue(int v))과 createMinMST를 비교해보자.
         */
        TreeNode minBSTRoot = TreeNode.createMinBST(arr);
        System.out.println("height: "+minBSTRoot.height());
        System.out.println("is BST?: "+ minBSTRoot.isBST());
        System.out.println("root: "+minBSTRoot.data);
    }


}
