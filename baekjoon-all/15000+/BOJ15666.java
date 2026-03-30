/*
 * 백준 15666번 : N과 M (12)
 * https://www.acmicpc.net/problem/15666
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-03-30
 * 간단 설명 : N개의 자연수 중에 M개를 골라서 중복 조합을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ15666 {
    static int N, M; // 자연수의 수, 고를 자연수의 수
    static ArrayList<Integer> num; // 자연수 배열
    static int[] selected; // 고른 숫자 배열
    static StringBuilder sb = new StringBuilder(); // 중복 조합을 저장할 객체
    
    static void comb(int start, int depth) { // 중복 조합을 만드는 함수
        // 종료 조건
        if (depth == M) {
            for (int n : selected) {
                sb.append(n).append(" "); // 중복 조합 저장
            }
            
            sb.append("\n"); // 개행 처리
            return; // 종료
        }
        
        for (int i = start; i < num.size(); i++) {
            selected[depth] = num.get(i); // 자연수 선택
            comb(i, depth + 1); // 다음 숫자 고르기
        }
    } // comb 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 자연수의 수
        M = Integer.parseInt(st.nextToken()); // 고를 자연수의 수
        
        num = new ArrayList<>(); // 자연수 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken()); // 자연수
            if (!num.contains(n)) num.add(n); // 가지고 있지 않은 자연수면 add
        }
        
        // 버퍼 닫기
        br.close();
        
        Collections.sort(num); // 자연수 배열 정렬
        
        selected = new int[M]; // 고른 숫자 배열
        comb(0, 0); // 중복 조합 만들기
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료