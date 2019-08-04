
import CtCILibrary.AssortedMethods;

import java.util.Arrays;
/*
 3. 마술인덱스
 정렬된 배열에서 인덱스와 실제값이 같은 '마술 인덱스' 찾기 
*/
public class C803 {
    //가장 먼저 생각해볼 수 있는 방법은 단순히 배열을 순회하며 탐색하기
    public static int magicSlow(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == i) {
                return i;
            }
        }
        return -1;
    }

    //'정렬된' 배열이고, 값에 중복이 없는 경우, 만약 a[3] = 3 이라면 a[2]는 3보다 작아야 하고, a[4] 는 3보다 커야 한다.
    // 따라서 임의의 인덱스값을 뽑아서 인덱스보다 값이 크면 오른쪽은 더 탐색할 필요가 없으므로(당연히 인덱스보다 값이 클 것이므로)
    // 아직 가능성이 있는 왼쪽만 탐색하면 된다. 반대로 인덱스보다 값이 작으면 오른쪽을 탐색해야 한다.
    // 결국 이진탐색 알고리즘과 같은 논리
    public static int findMagicIndex(int left, int right, int[] arr){
        if(left > right) return -1;// 전부 탐색했으나 마술인덱스가 존재하지 않음
        int c = (left + right) / 2;

        if(c == arr[c]) return c;
        else if (c < arr[c]) return findMagicIndex(left, c-1, arr);
        else return findMagicIndex(c+1,right, arr);
    }

    //중복을 허용하지 않는다면??





    /* Creates an array that is distinct and sorted */
    public static int[] getDistinctSortedArray(int size) {
        int[] array = AssortedMethods.randomArray(size, -1 * size, size);
        Arrays.sort(array);
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i-1]) {
                array[i]++;
            } else if (array[i] < array[i - 1]) {
                array[i] = array[i-1] + 1;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int size = AssortedMethods.randomIntInRange(5, 20);
            int[] array = getDistinctSortedArray(size);
            int v2 = findMagicIndex(0, array.length-1,array);
            if (v2 == -1 && magicSlow(array) != -1) {
                int v1 = magicSlow(array);
                System.out.println("Incorrect value: index = -1, actual = " + v1 + " " + i);
                System.out.println(AssortedMethods.arrayToString(array));
                break;
            } else if (v2 > -1 && array[v2] != v2) {
                System.out.println("Incorrect values: index= " + v2 + ", value " + array[v2]);
                System.out.println(AssortedMethods.arrayToString(array));
                break;
            }
            System.out.println("result: "+v2);
            System.out.println(AssortedMethods.arrayToString(array));
        }
    }

}
