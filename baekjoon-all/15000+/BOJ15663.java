/*
 * 백준 15663번 : N과 M (9)
 * https://www.acmicpc.net/problem/15663
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-02-11
 * 간단 설명 : N개의 자연 수 중에서 M개를 고른 수열을 모두 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ15663 {
    static int N, M; // 자연수의 개수, 고를 개수
    static int[] arr, selected; // 자연수 배열, 골라진 배열
    static boolean[] visited; // 방문 처리 배열
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체

    static void permutation(int depth) { // 순열을 만드는 함수
        // 종료 조건
        if (depth == M) {
            for (int num : selected) sb.append(num).append(" "); // 순열 추가하기
            sb.append("\n"); // 개행 처리
            return; // 종료
        }

        int prvUsed = 0; // 이미 사용한 숫자

        for (int i = 0; i < N; i++) {
            if (visited[i] || arr[i] == prvUsed) continue; // 이미 방문한 곳이거나 사용한 값이면 continue

            visited[i] = true; // 방문 처리
            selected[depth] = arr[i]; // 숫자 선택
            prvUsed = arr[i]; // 사용한 값 기록
            permutation(depth + 1); // 다음 숫자 고르기
            visited[i] = false; // 방문 처리 해제
        }
    } // permutation 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 자연수의 개수
        M = Integer.parseInt(st.nextToken()); // 고를 개수

        arr = new int[N]; // 자연수 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken()); // 자연수 저장

        // 버퍼 닫기
        br.close();

        Arrays.sort(arr); // 사전순 출력을 위한 정렬
        selected = new int[M]; // 골라진 배열
        visited = new boolean[N]; // 방문 처리 배열
        permutation(0); // 순열 만들기
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료
