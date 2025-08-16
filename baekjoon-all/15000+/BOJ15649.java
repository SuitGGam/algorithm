/*
 * 백준 15649번 : N과 M (1)
 * https://www.acmicpc.net/problem/15649
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-08-16
 * 간단 설명 : 중복되는 수열을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15649 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 자연수 N
        M = Integer.parseInt(st.nextToken()); // 조합으로 고를 개수
        
        selected = new int[M]; // 조합 배열 크기 지정
        visited  = new boolean[N + 1]; // 방문 배열 크기 지정
        
        // 순열 찾기
        permutation(0);
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
    
    static int N, M; // 자연수 N, 조합 M개
    static int[] selected; // 조합으로 고른 숫자를 담을 배열
    static boolean [] visited; // 방문 확인 배열
    
    // 순열 찾는 함수
    static StringBuilder sb = new StringBuilder();
    static void permutation(int depth) {
        // 종료 조건
        if (depth == M) {
            // 결과값 추가하기
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n"); // 개행 처리
            return;
        }
        
        // 조합 고르기
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) { // 고르지 않은 조합인 경우
                visited[i] = true; // 방문 처리
                selected[depth] = i; // 조합 고르기
                permutation(depth + 1); // 다음 조합 고르기
                visited[i] = false; // 방문 처리 해제
            }
        }
    }
} // class 종료