/*
 * 백준 11729번 : 하노이 탑 이동 순서
 * https://www.acmicpc.net/problem/11729
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-11-16
 * 간단 설명 : 하노이 탑의 이동 횟수와 이동 순서를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11729 {
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void hanoi(int N, int start, int mid, int to) { // 원판을 옮기는 함수
        // 종료 조건
        if (N == 1) {
            sb.append(start).append(" ").append(to).append("\n"); // 결과값 추가하기
            return;
        }
        
        hanoi(N - 1, start, to, mid); // N - 1개를 A에서 B로 이동
        sb.append(start).append(" ").append(to).append("\n"); // 1개를 A에서 C로 이동
        hanoi(N - 1, mid, start, to); // N - 1개를 B에서 C로 이동
    } // hanoi 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 원판의 개수
        
        // 버퍼 닫기
        br.close();
        
        int K = (int) Math.pow(2, N) - 1;
        hanoi(N, 1, 2, 3);
        
        // 결과값 출력하기
        System.out.print(K + "\n" + sb);
    } // main 종료
} // Main 종료