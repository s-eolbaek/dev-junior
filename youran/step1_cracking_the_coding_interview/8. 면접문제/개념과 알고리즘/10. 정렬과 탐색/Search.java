
import CtCILibrary.AssortedMethods;
import sort.Sort;

import java.util.Arrays;

public class BinarySearch {

    public int binarySearch(int [] arr, int x){
        int low = 0;
        int mid;
        int high = arr.length-1;
        while(low <= high){
            mid = (low + high)/2;
            if(x < arr[mid]){
                high = mid-1;
            }else if(x > arr[mid]){
                low = mid+1;
            }else{
                return mid;
            }
        }
        return -1; //찾는 값 없음
    }

    public int binarySearch_Recursive(int [] arr, int x, int low, int high){
        if(low > high) return -1;
        int mid = (low + high)/2;
        if(x < arr[mid]){
            return binarySearch_Recursive(arr, x, low, mid-1);
        }else if(x > arr[mid]){
            return binarySearch_Recursive(arr, x, mid+1, high);
        }else{
            return mid;
        }
    }

    public static void main(String[] args){
        BinarySearch bs = new BinarySearch();
        Sort mySort = new Sort();
        int[] arr = AssortedMethods.randomArray(30, 1, 30);
        mySort.quickSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(bs.binarySearch(arr, 30));
        System.out.println(bs.binarySearch_Recursive(arr, 30, 0, arr.length-1));
    }
}


