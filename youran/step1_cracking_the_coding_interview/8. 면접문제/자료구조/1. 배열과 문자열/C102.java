import java.util.Arrays;

/*
* 2. 순열확인
*
*  문자열 두 개가 주어졌을 때 이 둘이 서로 순열관계에 있는지 확인
*
*  1. 순열관계: 요소의 개수(길이)와 값이 같고 순서만 다른 관계
*  2. 문자열은 아스키코드 형식이며 대소문자,공백 구분한다고 가정
*
* */
public class C102 {

    //1. 탐색: 시간복잡도 O(N), 공간복잡도 O(1) ---> 내가 풀어본 것
    public static boolean isPermutation(String str1, String str2){
        int len = str1.length();
        if(len != str2.length()) return false; //길이가 다르면 순열관계가 성립되지 않음
        int arr[] = new int[128];
        int arr2[]  = new int[128];
        for(int i=0; i<len; i++){
            arr[str1.charAt(i)]+= 1;
            arr2[str2.charAt(i)]+= 1;
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i] != arr2[i]) return false;
        }
        return true;
    }

    //1-1. 최적화 : 길이와 요소가 같으므로 하나의 배열로 판별
    public static boolean isPermutation2(String str1, String str2){
        int len = str1.length();
        if(len != str2.length()) return false;
        int arr[] = new int[128];

        for(int i=0; i<len; i++){
            arr[str1.charAt(i)]+= 1;
        }
        for(int i=0; i<len; i++){
            char c = str2.charAt(i);
            arr[c]--; //0이 되어야 함
            if(arr[c]<0) return false;
        }
        return true;
    }

    //2. 정렬 후 비교: 시간복잡도 O(nlogn)이지만 깔끔하고 실용적
    public static String sort(String s){
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
    public static boolean isPermutation3(String str1, String str2){
        if(str1.length() != str2.length()) return false;
        return sort(str1).equals(sort(str2));
    }

    public static void main(String args[]){
        System.out.println(isPermutation("norang","ragnon"));
        System.out.println(isPermutation("norang","norann"));

        System.out.println(isPermutation2("norang","ragnon"));
        System.out.println(isPermutation2("norang","norann"));

        System.out.println(isPermutation3("norang","ragnon"));
        System.out.println(isPermutation3("norang","norann"));
    }
}
