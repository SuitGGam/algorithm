/*
 * 백준 1929번 : 소수 구하기
 * https://www.acmicpc.net/problem/1929
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-08-05
 * 간단 설명 : M과 N 사이의 소수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1929 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(st.nextToken()); // 자연수 M
        int N = Integer.parseInt(st.nextToken()); // 자연수 N

        // 버퍼 닫기
        br.close();

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        boolean[] primeNumber = new boolean[N + 1]; // 소수 여부를 판단할 배열
        primeNumber[0] = primeNumber[1] = true;
        for (int i = 2; i <= N; i++) {
            if (!primeNumber[i]) { // 소수인 경우
                primeNumber[i] = true; // 소수 표기하기
                if (i >= M) sb.append(i).append("\n"); // 결과값 추가하기

                // i의 배수 모두 true 처리하기
                if (Integer.MAX_VALUE >= (long) i * i) { // int값 오버플로우 방지
                    for (int j = i * i; j <= N; j += i) {
                        primeNumber[j] = true;
                    }
                }
            }
        }

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료