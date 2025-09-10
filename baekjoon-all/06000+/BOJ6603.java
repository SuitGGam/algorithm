/*
 * 백준 6603번 : 로또
 * https://www.acmicpc.net/problem/6603
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-09-11
 * 간단 설명 : 로또 조합을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6603 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int k; // 숫자의 개수
    static int[] S; // 집합 배열
    static int[] selected = new int[6]; // 고른 원소 배열
    
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void combination(int depth, int start) { // 조합을 구하는 함수
        // 종료 조건
        if (depth == 6) {
            for (int i = 0; i < 6; i++) sb.append(selected[i]).append(" "); // 출력 문자 저장
            sb.append("\n"); // 개행 처리
            return; // 종료
        }
        
        for (int i = start; i < k; i++) {
            selected[depth] = S[i]; // 숫자 고르기
            combination(depth + 1, i + 1); // 다음 숫자 고르기
        }
    } // combination 종료
    
    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            k = Integer.parseInt(st.nextToken()); // 숫자의 개수
            
            // 테스트 케이스 종료 조건
            if (k == 0) break;
            
            S = new int[k]; // 배열 크기 지정
            for (int i = 0; i < k; i++) S[i] = Integer.parseInt(st.nextToken()); // 원소 저장
            
            combination(0, 0); // 조합 고르기
            sb.append("\n"); // 개행 처리
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료