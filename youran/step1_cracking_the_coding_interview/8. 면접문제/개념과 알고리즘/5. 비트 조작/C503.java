package bit;

import java.util.ArrayList;

public class C503 {
    /*
    *  비트 뒤집기
    *  주어진 정수에서 비트 하나를 1로 바꿀 수 있을 때,
    *  연속으로 1이 나올 수 있는 가장 긴 길이를 구하기
    *
    * */

    // 1. 연속된 0 또는 1의 집합을 하나의 수열로 보고, 각 수열의 길이를 배열에 저장해 비교하는 방법
    public static int longestSequence(int n){
        if(n == -1) return Integer.BYTES;  // --> ?? 왜 -1일때 32인지 모르겠음
        ArrayList<Integer> seq = getAlternatingSequence(n);
        return findLongestSequence(seq);
    }

    public static ArrayList<Integer> getAlternatingSequence(int n){
        ArrayList<Integer> seq = new ArrayList<>();

        int searchingFor = 0; // 0부터 시작
        int counter = 0;

        for(int i=0; i<Integer.BYTES * 8; i++){ // Integer 타입은 4바이트 * 8 -> 32 비트 (1바이트 = 8비트)
            if( (n & 1) != searchingFor){ // 새로운 수열 시작
                seq.add(counter);
                searchingFor = n&1;
                counter = 0;
            }
            counter++;
            n >>>= 1; // 1씩 시프트 ( 맨 오른쪽, 0자리부터 점검) : >>> 논리 우측 시프트(최상위비트에 0)
        }
        return seq;
    }

    public static int findLongestSequence(ArrayList<Integer> seq){
        int maxSeq = 1; // 비트 하나를 1로 바꿀 수 있으므로 최소 한 개의 1은 존재함

        for(int i=0; i<seq.size(); i+=2){ // 0수열과 1수열이 번갈아 나오므로 0 수열만 골라서 좌우의 1수열을 더한다
            int zerSeq = seq.get(i);
            int onesSeqRight = i-1 >= 0 ? seq.get(i-1) : 0;
            int oneSeqLeft = i+1 < seq.size() ? seq.get(i+1) : 0;

            int thisSeq = 0;
            if(zerSeq == 1){ // 0이 한 개이므로 1로 바꾸어 좌우 1수열과 연결시킬 수 있다
                thisSeq = oneSeqLeft + 1 + onesSeqRight;
            } else if(zerSeq > 1) { // 0 수열은 무시하고 좌우 수열 중 더 긴 수열을 찾아 1을 더해준다(0비트 하나를 바꾼 값)
                thisSeq = Math.max(oneSeqLeft, onesSeqRight)+1;
            } else if(zerSeq == 0){
                thisSeq = Math.max(oneSeqLeft, onesSeqRight);
            }
            maxSeq = Math.max(thisSeq, maxSeq);

        }
        return maxSeq;
    }

    // 최적 알고리즘
    /*
    *  전체 수열 길이가 b일 때 위 알고리즘은 시간,공간 복잡도 모두 O(b)
    *  수열을 최소 한번씩은 탐색해야 하므로 시간을 줄일 수는 없지만
    *  공간은 절약할 수 있다. 모든 수열의 길이를 저장해둘 필요는 없기 때문.
    * */
    int flipBit(int n){
        if(~n == 0) return Integer.BYTES * 8; //모든 비트가 다 1이면 가장 긴 수열

        int currentLength = 0;
        int prevLength = 0;
        int maxLength = 1;

        while(n != 0){
            if((n&1) == 1){ // 현재 비트가 1
                currentLength ++;
            } else if( (n&1) == 0){ //현재 비트가 0
                // n&2 = n&10 -> 다음 비트를 확인. 다음 비트가 1이면 현재 수열의 길이를 prevLength에 저장해둔다.
                prevLength = (n&2) == 0? 0 : currentLength;
                currentLength = 0;
            }
            maxLength = Math.max(prevLength + currentLength + 1, maxLength);
            n >>>= 1;
        }
        return maxLength;
    }

    public static void main(String[] args){
        int n = 1775;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(longestSequence(n));
        System.out.println(longestSequence(n));
    }
}
