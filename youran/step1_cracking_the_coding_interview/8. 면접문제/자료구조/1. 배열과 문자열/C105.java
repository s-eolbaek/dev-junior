// 하나 빼기
/*
*  두 문자열의 길이가 같으면 교체만 가능
*  두 문자열의 길이가 1 차이면 삽입 or 삭제 가능
*  두 문자열의 길이가 2 이상 차이나면 항상 false
*
*  각 조건에 따라 다른 절차로 확인.
*
*  나름 거의 근접하게 풀었는데 막판에 자신감을 잃어서 포기해버린 게 아쉽다.
*  일단 할 수 있는 데까지는 끝까지 해보고 고쳐나가면 된다는 걸 다시한번 기억하자.
*  초보면서 굳이 간결하고 세련되려고 끙끙대지 말고 구구절절 할 수 있는 것 다 동원해서 풀어보자.
* */

public class C105 {

    public static boolean replace(String s1, String s2){
        int cnt = 0; //boolean으로 대체할 수 있다
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)) cnt++;
            if(cnt>1) return false;
        }
        return true;
    }

    public static boolean insertOrDelete(String longer, String shoter){
        int index1 = 0;
        int index2 = 0;
        int cnt = 0;
        while(index1<longer.length() || index2 < shoter.length()){
            if(longer.charAt(index1) != shoter.charAt(index2)){
                if(index1 != index2) return false;
                //두 문자가 같지 않은데 index값도 같지 않다는 것은
                //이전에 이미 일치하지 않아서 index1이 증가했다는 의미이므로 false
                index1++;
            }else{
                index1++;
                index2++;
            }
        }
       return true;
    }

    // 1. 조건(교환 or 삽입삭제)에 따라 다르게 탐색하기
    //시간복잡도 O(N) (N: 짧은 쪽 문자열 길이. 문자열 길이 차이가 커도 짧은 쪽 탐색 끝나면 종료됨)
    //어차피 길이가 2 이상 차이나면 반드시 하나 이상의 문자를 삽입 or 삭제해야 하므로.항상 false 다.
    public static boolean editOnce(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == len2) return replace(s1, s2);
        else if( len1+1 == len2 ) return insertOrDelete(s1, s2);
        else if ( len1-1 == len2) return insertOrDelete(s2, s1);
        else return false;
    }

    
    // 교환 삽입 삭제 한번에 처리하기
    // 두 메서드를 합쳐보고 각 방법의 장단점을 생각해보자
    
    public static void main(String args[]){
        System.out.println(editOnce("pale", "pale"));
    }
}