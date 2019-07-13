public class C501 {
    /*
    *  32비트 수 N, M , 비트 위치 i, j
    *  N의 i~j 구간에 M을 삽입하는 메서드
    *  예를 들어 N = 110010001111 , M = 1101, i = 2, j = 5 이면
    *  결과는 N = 110010'1101'11
    *
    *  1. N의 i~j 위치를 0으로 초기화한다. ---> 마스크를 만들 때 좌우를 따로 만들어 합치면 수월함
    *  2. M을 i만큼 시프트한다.
    *  3. N과 M을 합친다.
    *
    * */
    public static int setBits(int n, int m, int i, int j){
        // 1. 0으로 초기화
        int allOnes = ~0; //  모든 비트가 1인 값을 만들어둔다.
        // j + 1 부터 length-1까지는 1, 나머지는 0 -> 111111000000 (자릿수가 0부터 시작하니까 1을 더해야함)
        // 32비트만큼 << 하면 시프트가 안되니 j 크기를 검증해야 함.
        int left = (j>=31) ? 0 : allOnes << (j+1);
        int right =  (1 << i)-1;// i -1 부터 0까지는 1, 나머지는 0 -> 000000000011 -> 100 - 1 = 11
        int mask = left | right;
        System.out.println(toFullBinaryString(left));
        int n_cleared = n & mask;

        // m을 시프트
        int m_shifted = m << i; // 110100

        return n_cleared | m_shifted;
    }

    // 정수를 32비트의 이진수 형식으로 출력
    public static String toFullBinaryString(int a) {
        String s = "";
        for (int i = 0; i < 32; i++) {
            Integer lsb = new Integer(a & 1); // 현재 자리의 값만 남김
            s = lsb.toString() + s;
            a = a >> 1;
        }
        return s;
    }

    public static void main(String args[]){
        int a = ~23423;
        System.out.println(toFullBinaryString(a));
        int b = 5;
        System.out.println(toFullBinaryString(b));
        int c = setBits(a, b, 29, 31);
        System.out.println(toFullBinaryString(c));
    }
}
