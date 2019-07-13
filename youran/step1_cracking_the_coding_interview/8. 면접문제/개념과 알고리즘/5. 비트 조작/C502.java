package bit;

public class C502 {

    /*
    *  0과 1 사이의 실수가 double타입으로 주어지면 그 값을 2진수 형태의 문자열로 출력하기
    * */

    // 정수를 이진수로 만들때는 2로 나눠주듯이, 실수를 이진수로 만들 때는 소수점 뒷자리가 0이 될 때까지 2로 곱해준다.
    // 그리고 이는 곧 어떤 수를 왼쪽으로 1씩 시프트한 결과와 같다.

    public static String printBinary(double d){
        if(d >= 1 || d<= 0) return "ERROR";

        StringBuilder binary = new StringBuilder();
        binary.append(".");
        while( d > 0){
            if(binary.length() >= 32) return "ERROR"; //32 길이의 문자열로 표현할 수 없으면 에러 리턴
            double r = d*2;
            if(r >= 1) { // 2로 곱한 결과가 1보다 크면 소수점 바로 뒤에 1이 있다는 의미
                binary.append("1");
                d = r-1;
            } else{
                binary.append("0");
            }
        }
        return binary.toString();
    }


    // 1/2^n 과 비교해나가는 방법을 쓸 수도 있다 (0.5, 0.25 ...)
    public static String printBinary2(double d){
        if(d >= 1 || d<= 0) return "ERROR";

        StringBuilder binary = new StringBuilder();
        binary.append(".");
        double frac = 0.5;
        while( d > 0) {
            if (binary.length() >= 32) return "ERROR"; //32 길이의 문자열로 표현할 수 없으면 에러 리턴
            if (d >= frac) {
                binary.append("1");
                d -= frac;
            }else{
               binary.append("0");
            }
            frac /= 2;
        }
        return binary.toString();
    }

    public static void main(String[] args){
        System.out.println(printBinary2(.75));
    }
}
