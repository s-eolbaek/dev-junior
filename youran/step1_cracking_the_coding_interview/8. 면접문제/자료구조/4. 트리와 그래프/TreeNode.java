
public class TreeNode {

    public int data;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
    private int size = 0;

    public TreeNode(int d){
        data = d;
        size = 1;
    }

    private void setLeftChild(TreeNode left){
        this.left = left;
        if(left != null) {
            left.parent = this;
        }
    }

    private void setRightChild(TreeNode right){
        this.right = right;
        if(right != null) {
            right.parent = this;
        }
    }

    public void insertInOrder(int d){
        if(d <= data) {
            if(left == null){
                setLeftChild(new TreeNode(d));
            } else{
                left.insertInOrder(d);
            }
        }else{
            if(right == null){
                setRightChild(new TreeNode(d));
            }else{
                right.insertInOrder(d);
            }
        }
        size++;
    }

    public int size(){
        return size;
    }

    public boolean isBST(){
        if(left != null){
            if(data < left.data || !left.isBST()) return false;
        }
        if(right != null){
            if(data >= right.data || !right.isBST()) return false;
        }
        return true;
    }

    public int height(){
        int leftHeight = left != null ? left.height():0;
        int rightHeight = right != null ? right.height():0;
        return 1 + Math.max(leftHeight,rightHeight);
    }


    public TreeNode find(int d){
        if(data == d) return this;
        else if(d <= data) return left!= null ? left.find(d) : null;
        else if(d > data) return right!=null ? right.find(d) : null;
        return null;
    }


    private static TreeNode createMinBST(int[] arr, int start, int end){
        System.out.println(start+" "+ end);
        TreeNode node = null;
        if(end == start){
            node = new TreeNode(arr[start]);
        }else if( start < end){
            //원래 else 로 처리했으나 짝수길이 배열로 테스트하니 StackOverFlowError 발생.
            //중앙값을 구했을 때 왼쪽이나 오른쪽에 값이 하나만 남은 경우 end가 start보다 작아지고 중복값을 가진 노드가 계속 추가되며
            //무한 루프에 빠지게 되었다.
            // -> start보다 end가 크면 더이상 남은 요소가 없는 것이므로 아무 작업도 수행하지 않도록 조건 추가

            /*
            * 책에서는 이렇게 처리했다.
            * if(end < start) return null;
            *
            * start와 end가 같을 경우에도 똑같이 재귀함수를 실행함. start==end일 때, 즉 더이상 요소가 남아있지 않을 때
            * left, right 를 실행해도 결국 null을 반환할테니 굳이 조건을 따로 둬서 처리할 필요가 없구나.
            * */
            int c = (start + end)/2;
            node = new TreeNode(arr[c]);
            node.left = createMinBST(arr, start, c-1);
            node.right = createMinBST(arr, c+1, end);
        }
       return node;
    }

    public static TreeNode createMinBST(int[] arr){
        return createMinBST(arr, 0, arr.length-1);
    }
}



