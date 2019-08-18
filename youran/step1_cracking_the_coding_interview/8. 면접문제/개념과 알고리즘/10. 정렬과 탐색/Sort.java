
import CtCILibrary.AssortedMethods;

import java.util.Arrays;



public class Sort {

    public void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /*
        버블정렬
        이웃한 요소(index-1 or index+1)와 비교하여 왼쪽에 있는 요소가 값이 더 크면 두 요소의 값을 교환한다.
        배열 길이 (요소 개수) n * 이웃한 요소와 비교 횟수 n-1 = n * (n-1) = n^2 - n => O(n^2)
    */
    public void bubbleSort(int[] arr){
        if(arr == null) return;
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length-1; j++){
                if(arr[j]>arr[j+1]){
                    swap(arr, j, j+1);
                }
            }
        }
    }

    /*
    *  선택정렬
    *  전체 요소 중 가장 작은 값을 찾아 0번째 자리로 보낸다.(0번째 요소와 교환)
    *  -> 남은 요소들 중 가장 작은 값을 찾아 1번째 자리로 보낸다.
    *  -> 모든 요소가 정렬되 때까지 반복
    *  배열 길이(요소 개수) n -> 정렬을 반복할 때마다 연산이 1번씩 줄어든다.
    *  (n-1) + (n-2) + ... + 2 + 1 = n * (n-1) / 2 = n^2 - n / 2 = n^2
    * */
    public void selectionSort(int[] arr){
        if(arr == null) return;
        int minIdx;  //최소값을 가진 요소의 인덱스를 저장할 변수
        for(int i=0; i<arr.length; i++) {
            minIdx = i; // 탐색 시작 위치의 인덱스로 초기화해둔다.
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            swap(arr, minIdx, i); // 최종 최소값과 i번째 자리의 값을 교환
        }
    }

    /*
    *  병합정렬
    *  각 배열의 요소가 1개가 될 때까지 배열을 계속 반으로 나눈 후 정렬하며 병합해나간다.
    *  {3,1,4,7,9,2} -> {3,1,4} / {7,9,2} -> {3,1}/{4} // {7,9}/{2}
    *  -> {3}/{1}//{4}///{7}/{9}//{2} -> {1,3}/{4}//{7,9}/{2}
    *  -> {1,3,4}/{2,7,9} -> {1,2,3,4,7,9}
    *
    *  전체 요소 n개를 절반씩 나누어가는 과정: logN
     *  (길이 8인 배열을 계속 반으로 나누려면 4+2 -> (2+2)+(2+2) -> (1+1+1+1)+(1+1+1+1) -> 3번 연산: 2^3 = 8)
     * 절반씩 나누는 과정의 각 단계에서 모든 요소를 한 번씩 검사하므로 N
     *  => O(NlogN)
    * */
    public void mergeSort(int [] arr){
        int[] helper = new int[arr.length];//요소값을 임시 저장할 배열 생성
        mergeSort(arr, helper, 0, arr.length-1);
    }

    public void mergeSort(int[] arr, int[] helper, int left, int right){
        if(left < right){
            int center = (left+right)/2;
            mergeSort(arr, helper, left, center);
            mergeSort(arr, helper, center+1, right);
            merge(arr, helper, left, center, right);
        }
    }

    public void merge(int[] arr, int[] helper, int left, int center, int right){
        //병합할 요소들을 임시 배열에 복사
        for(int i=left; i<=right; i++){
            helper[i] = arr[i];
        }
        int curr = left; //배열에 요소를 넣을 위치
        int left1 = left; //첫번째(왼쪽) 배열의 시작위치
        int left2 = center+1; //두번째(오른쪽) 배열의 시작위치
        while(left1<=center && left2<=right){
            if(helper[left1] <= helper[left2]){
                arr[curr] = helper[left1];
                left1++;
            }else{
                arr[curr] = helper[left2];
                left2++;
            }
            curr++;
        }
        //비교 후 왼쪽 배열에 남은 요소를 남은 자리에 넣어준다.
        //(오른쪽 배열의 남은 요소는 이미 정렬된 상태에서 위치 변동이 없으므로 복사하지 않아도 된다)
        for(int i = left1; i <= center; i++){
            arr[curr] = helper[i];
            curr++;
        }
    }

    /*
    * 퀵정렬
    * 기준값(pivot)을 설정한 후 기준값보다 작은 요소는 왼쪽으로, 큰 요소는 오른 쪽으로 보낸다(swap)
    * 이동이 끝나면 기준위치의 왼쪽과 오른쪽에 대해 같은 작업을 반복한다.
    * */
    public void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }

    public void quickSort(int[] arr, int left, int right){
        int index = partition(arr,left,right);
        // index, index+1 로 분할했을 때는 중복값 때문에 무한루프에 빠지는데
        // 왜 이렇게 하면 그런 문제가 안 생길까?
        if(left < index-1) quickSort(arr, left, index-1);
        if(index < right) quickSort(arr, index, right);
    }

    public int partition(int[] arr,int left, int right){
        int pivot = arr[left+(right-left)/2]; // 왜 이렇게 구하지?
        //System.out.println(left+" "+ right+" "+pivot);
        while(left<=right){
            //교환할 요소가 나타날 때까지 탐색
            //이렇게 하면 pivot도 정렬에 포함시키게 된다. 그게 맞는건가?
            while(arr[left]<pivot) left++;
            while(arr[right]>pivot) right--;
            //중복된 값이 있을 경우 무한루프에 빠진다 -> 분할기점을 바꾸니 해결(116 라인 참조)
            if(left<=right) {
               // System.out.println("********"+ left+" "+ right);
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        //System.out.println(Arrays.toString(arr));
        return left;
    }

    public static void main(String[] args){
        Sort sort = new Sort();
     /*   int[] arr = AssortedMethods.randomArray(50, 1, 100);
        sort.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = AssortedMethods.randomArray(50, 1, 100);
        sort.selectionSort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = AssortedMethods.randomArray(20, 1, 100);
        System.out.println("unsorted: "+Arrays.toString(arr3));
        sort.mergeSort(arr3);
        System.out.println("sorted: "+Arrays.toString(arr3));
*/
        int[] arr4 = AssortedMethods.randomArray(20, 0, 6);
        System.out.println("unsorted: "+Arrays.toString(arr4));
        sort.quickSort(arr4);
        System.out.println("sorted: "+Arrays.toString(arr4));

    }
}


