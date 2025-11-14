/*
 * 백준 1914번 : 하노이 탑
 * https://www.acmicpc.net/problem/1914
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-11-15
 * 간단 설명 : 하노이 탑의 이동 횟수와 이동 순서를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ1914 {
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void hanoi(int N, int start, int mid, int to) { // 원판을 옮기는 함수
        // 종료 조건
        if (N == 1) {
            sb.append(start).append(" ").append(to).append("\n"); // 결과값 추가하기
            return;
        }
        
        hanoi(N - 1, start, to, mid); // N - 1개를 1에서 2로 이동
        sb.append(start).append(" ").append(to).append("\n"); // 1개를 1에서 3으로 이동
        hanoi(N - 1, mid, start, to); // N - 1개를 2에서 3으로 이동
    } // hanoi 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 체커의 수
        
        // 버퍼 닫기
        br.close();
        
        BigInteger K = BigInteger.valueOf(2).pow(N).subtract(BigInteger.ONE); // 옮긴 횟수
        sb.append(K).append("\n"); // 결과값 추가하기
        if (N <= 20) hanoi(N, 1, 2, 3); // 하노이 탑 옮기기
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료