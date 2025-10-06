/*
 * 백준 34558번 : Prime Median
 * https://www.acmicpc.net/problem/34558
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-10-06
 * 간단 설명 : 임의의 구간에서 소수 중 중앙값이 무엇인지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ34558 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static final int LAST_NUMBER = 1_000_000; // 마지막 수
    
    static int[] counting;  // 소수 개수 카운팅 배열
    static boolean[] prime; // 소수 배열
    static void prime() { // 소수를 구하는 함수
        for (int i = 2; i <= LAST_NUMBER; i++) {
            if (!prime[i]) {
                for (int j = i * 2; j <= LAST_NUMBER; j += i) prime[j] = true; // 소수 아닌 수 제거
            }
        }
    } // prime 종료
    
    static void counting() { // 구간 소수 개수를 구하는 함수
        int prefixCnt = 0; // 누적 카운팅
        for (int i = 2; i <= LAST_NUMBER; i++) {
            if (!prime[i]) prefixCnt++; // 소수 카운팅
            counting[i] = prefixCnt; // 카운팅 배열에 누적 개수 저장
        }
    } // counting 종료
    
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void findMedian(int start, int end) {
        int primeCnt = counting[end] - counting[start - 1]; // 닫힌 구간 소수의 개수
        
        if (primeCnt % 2 == 0) sb.append(-1).append("\n"); // 소수의 개수가 0개거나 짝수인 경우 결과값 추가하기
        else { // 소수의 개수가 홀수인 경우
            int target = (primeCnt / 2 + 1) + counting[start - 1]; // 전체에서 몇 번째 소수인지
            int left = start; // 왼쪽 포인터
            int right = end;  // 오른쪽 포인터
            int answer = -1;  // 정답
            while (left <= right) {
                int mid = (left + right) / 2; // 중간 지점
                if (counting[mid] >= target) {
                    right  = mid - 1; // 오른쪽 포인터 당기기
                    answer = mid; // 정답 갱신
                } else {
                    left = mid + 1; // 왼쪽 포인터 당기기
                }
            }
            
            sb.append(answer).append("\n"); // 결과값 추가하기
        }
    } // findMedian 종료
    
    public static void main(String[] args) throws IOException {
        prime = new boolean[LAST_NUMBER + 1]; // 배열 크기 지정
        prime(); // 소수 구하기
        counting = new int[LAST_NUMBER + 1]; // 배열 크기 지정
        counting(); // 구간 소수 개수 구하기
        
        int N = Integer.parseInt(br.readLine()); // 닫힌 구간의 개수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()); // 시작 구간
            int   end = Integer.parseInt(st.nextToken()); // 종료 구간
            
            findMedian(start, end); // 구간 소수 중앙값 구하기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료