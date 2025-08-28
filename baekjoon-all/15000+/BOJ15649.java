/*
 * 백준 15649번 : N과 M (1)
 * https://www.acmicpc.net/problem/15649
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-08-28
 * 간단 설명 : 순열을 만드는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15649 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N, M;          // 자연수의 개수, 고르는 개수
    static int[] selected;    // 고른 숫자를 저장할 배열
    static boolean[] visited; // 방문 여부를 저장할 배열
    
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void permutation(int depth) { // 순열을 만드는 함수
        // 종료 조건
        if (depth > M) { // 결과값 추가하기
            for (int i = 1; i <= M; i++) sb.append(selected[i]).append(" ");
            sb.append("\n"); // 개행 처리
            return; // 종료하기
        }
        
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true; // 방문 처리
                selected[depth] = i; // 숫자 선택
                permutation(depth + 1); // 다음 숫자 고르기
                visited[i] = false; // 방문 해제
            }
        }
    } // permutation 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 자연수의 개수
        M = Integer.parseInt(st.nextToken()); // 고르는 개수
        
        // 버퍼 닫기
        br.close();
        
        selected = new int[M + 1];     // 배열 크기 지정
        visited  = new boolean[N + 1]; // 배열 크기 지정
        permutation(1); // 순열 만들기
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료