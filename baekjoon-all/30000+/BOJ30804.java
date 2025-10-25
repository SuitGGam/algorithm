/*
 * 백준 30804번 : 과일 탕후루
 * https://www.acmicpc.net/problem/30804
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-10-26
 * 간단 설명 : 과일의 종류가 두 가지 이하로 남도록 만든 탕후루 중에 가장 많은 과일 개수
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ30804 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 과일의 개수
        int[] tanghulu = new int[N]; // 탕후루 배열
        int[] counting = new int[9 + 1]; // 과일 종류 카운팅 배열
        int remaindKind = 0; // 남아있는 과일 종류 개수
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) tanghulu[i] = Integer.parseInt(st.nextToken()); // 과일 종류 저장
        
        // 버퍼 닫기
        br.close();
        
        int left = 0, right = 0; // 왼쪽 포인터, 오른쪽 포인터
        int remainKind = 0, maxRemain = 0; // 남아있는 과일 종류의 수, 최대 남은 과일 개수
        while (right < N) {
            if (counting[tanghulu[right]] == 0) remainKind++; // 남아있는 과일 종류의 수 추가
            counting[tanghulu[right]]++; // 카운팅 증가
            right++; // 오른쪽 포인터 이동
            
            while (remainKind > 2) { // 남아있는 과일 종류의 수가 2가지를 초과할 때
                counting[tanghulu[left]]--; // 카운팅 감소
                if (counting[tanghulu[left]] == 0) remainKind--; // 남아있는 과일 종류의 수 감소
                left++; // 왼쪽 포인터 이동
            }
            
            maxRemain = Math.max(maxRemain, right - left); // 최대 남은 과일 개수 갱신
        }
        
        // 결과값 출력하기
        System.out.print(maxRemain);
    } // main 종료
} // Main 종료