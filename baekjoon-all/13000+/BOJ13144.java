/*
 * 백준 13144번 : List of Unique Numbers
 * https://www.acmicpc.net/problem/13144
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-09-04
 * 간단 설명 : 중복된 숫자가 없는 연속된 수열의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13144 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 수열의 길이
        
        st = new StringTokenizer(br.readLine(), " ");
        int[] sequence = new int[N]; // 수열을 담을 배열
        for (int i = 0 ; i < N; i++) sequence[i] = Integer.parseInt(st.nextToken()); // 수열 저장
        
        // 버퍼 닫기
        br.close();
        
        long cnt = 0; // 경우의 수 개수
        int[] counting = new int[100000 + 1]; // 카운팅 배열
        int left = 0;  // 왼쪽 포인터
        int right = 0; // 오른쪽 포인터
        while (right < N) {
            int num = sequence[right]; // 현재 보고 있는 숫자
            counting[num]++; // 카운팅 증가
            
            while (counting[num] > 1) { // 중복이 생긴 경우
                counting[sequence[left]]--;
                left++; // 왼쪽 포인터 이동
            }
            
            cnt += right - left + 1; // 중복 없는 구간 만큼 경우의 수 추가
            right++; // 오른쪽 포인터 이동
        }
        
        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료