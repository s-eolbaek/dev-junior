package string;

import java.util.Scanner;

public class C103{
/*
    1.3. URL화 : 문자열에 들어있는 모든 공백을 '%20'으로 바꿔주는 메서드를 작성하라.

    조건 1) 최종적으로 모든 문자를 다 담을 만큼 충분한 공간이 이미 확보되어 있다.
    조건 2) 문자열의 최종 길이(실제 길이)가 함께 주어진다.
    조건 3) 자바로 구현한다면 문자 배열을 이용하라.
 */

    public static String toURL(char[] str, int len){

        int space = 0; //공백 개수 저장
        int index = 0; //url로 변환할 때 문자 복사에 사용할 index

        for(int i=0; i<len; i++){
            if(str[i]==' ') space++;
        }

        index = len + space*2; //url로 변환했을 때의 길이(공백 하나당 2칸씩 길어진다)
        if(index < str.length) str[index] = '\0';
        index = index - 1;
        for(int i=len-1; i>=0; i--){ //뒤에서부터 수정해야 기존 값을 덮어쓰지 않는다.
            if(str[i]==' '){
                str[index] = '0';
                str[index-1] = '2';
                str[index-2] = '%';
                index = index - 3;
            }else{
                str[index] = str[i];
                index--;
            }
        }
        String url = "";
        for(int i=0; i<str.length; i++){
            if(str[i] == '\0') break;
            url += str[i];
        }
        return url;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int len = sc.nextInt();

        char[] arr = new char[1000];
        for(int i=0; i<len; i++){
            arr[i] = s.charAt(i);
        }
        System.out.println(toURL(arr, len));


    }


}