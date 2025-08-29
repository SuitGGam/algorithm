/*
 * 백준 15654번 : N과 M (5)
 * https://www.acmicpc.net/problem/15654
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-08-29
 * 간단 설명 : 주어지는 숫자로 순열을 만드는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ15654 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N, M; // 자연수의 개수, 고를 숫자의 개수
    static int[] number; // 입력된 숫자를 저장할 배열
    static int[] selected; // 고른 숫자를 저장할 배열
    static boolean[] visited; // 방문 처리를 할 배열
    
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void permutation(int depth) { // 순열을 만드는 함수
        // 종료 조건
        if (depth > M) {
            for (int i = 1; i <= M; i++) sb.append(selected[i]).append(" "); // 결과값 추가
            sb.append("\n"); // 개행 처리
            return; // 종료
        }
        
        for (int i = 1; i <= N; i++) { // 순열 만들기
            if (!visited[i]) {
                visited[i] = true; // 방문 처리
                selected[depth] = number[i]; // 숫자 고르기
                permutation(depth + 1); // 다음 숫자 고르기
                visited[i] = false; // 방문 처리 해제
            }
        }
    } // permutation // 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 자연수의 개수
        M = Integer.parseInt(st.nextToken()); // 고를 숫자의 개수
        
        number = new int[N + 1]; // 배열 크기 지정
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(number);
        
        // 버퍼 닫기
        br.close();
        
        selected = new int[M + 1];     // 배열 크기 지정
        visited  = new boolean[N + 1]; // 배열 크기 지정
        permutation(1);
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료