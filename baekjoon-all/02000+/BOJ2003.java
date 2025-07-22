/*
 * 백준 2003번 : 수들의 합 2
 * https://www.acmicpc.net/problem/2003
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-07-22
 * 간단 설명 : 수열의 특정 구간의 합이 M이 되는 경우가 몇 개인지 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2003 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 수열의 수
        int M = Integer.parseInt(st.nextToken()); // 구하려는 수의 합
        
        int[] sequence = new int[N + 1]; // 누적합 비교를 위해 한 칸 비우기
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            sequence[i]  = Integer.parseInt(st.nextToken()); // 수 입력받기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 배열을 누적합으로 바꿔주기
        for (int i = 1; i <= N; i++) {
            sequence[i] += sequence[i - 1];
        }
        
        int correctCnt = 0; // 합이 일치하는 경우의 수
        int left  = 1; // 왼쪽 포인터
        int right = 1; // 오른쪽 포인터
        while (left <= N && right <= N) {
            // 포인터로 누적합에 대한 범위 위주로 탐색
            // 포인터가 하나라도 범위를 벗어나면 종료
            
            // 범위에 대한 합이 M과 일치하는 경우
            if (sequence[right] - sequence[left - 1] == M) {
                correctCnt++; // 경우의 수 1개 증가
                right++; // 오른쪽 포인터 한 칸 오른쪽으로 이동
            }
            // 범위에 대한 합이 M보다 큰 경우
            else if (sequence[right] - sequence[left - 1] > M) {
                // 범위의 합 줄여주기
                left++; // 왼쪽 포인터 한 칸 오른쪽으로 이동
            }
            // 범위에 대한 합이 M보다 작은 경우
            else {
                // 범위의 합 높여주기
                right++;
            }
        }
        
        // 결과값 출력하기
        System.out.print(correctCnt);
    } // main 종료
} // Main 종료