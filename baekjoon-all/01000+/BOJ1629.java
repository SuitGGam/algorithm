/*
 * 백준 1629번 : 곱셈
 * https://www.acmicpc.net/problem/1629
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-08-10
 * 간단 설명 : 모듈러 연산과 지수 법칙을 이용해 곱셈의 나머지를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long C; // 자연수 C
    static long pow(long A, long exponent) {
        // 종료 조건
        if (exponent == 1) return A % C;

        long tmp = pow(A, exponent / 2);

        if (exponent % 2 == 1) return (tmp * tmp % C) * A % C;

        return tmp * tmp % C;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        long A = Long.parseLong(st.nextToken()); // 자연수 A
        long B = Long.parseLong(st.nextToken()); // 자연수 B
        C = Long.parseLong(st.nextToken()); // 자연수 C

        // 버퍼 닫기
        br.close();

        long remain = pow(A, B);

        // 결과값 출력하기
        System.out.print(remain);
    } // main 종료
} // Main 종료