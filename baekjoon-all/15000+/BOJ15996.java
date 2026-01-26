/*
 * 백준 15996번 : 팩토리얼 나누기
 * https://www.acmicpc.net/problem/15996
 * 난이도 : 실버 3
 * 풀이 날짜 : 2026-01-25
 * 간단 설명 : 주어진 정수에 대해 A의 k 제곱으로 나누었을 때 나머지가 0이 되는 가장 큰 k를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : 불가능
 * 이유 : 최대 약 21억! 을 구하고 A로 계속 나눠야 하는데 컴퓨터로는 21억!의 값을 구하기가 어려움
 *
 * 알고리즘 : 수학
 * 자료구조 : X
 * 시간 복잡도 : O (logA N)
 * 풀이 방법 : 정수 N에 소수 A가 몇 번 곱해졌는지 보면 됨
 * N이 A보다 작아질 때까지 A로 계속 나누면서 몫만큼 더해주면 됨
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15996 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken()); // 정수
        int A = Integer.parseInt(st.nextToken()); // 소수
        
        // 버퍼 닫기
        br.close();
        
        int k = 0; // 소수의 지수
        while (N >= A) {
            k += N / A; // N을 A로 나눈 몫만큼 더해주기
            N /= A; // N을 A로 나누기
        }
        
        // 결과값 출력하기
        System.out.print(k);
    } // main 종료
} // Main 종료