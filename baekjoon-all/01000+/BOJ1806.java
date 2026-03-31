/*
 * 백준 1806번 : 부분합
 * https://www.acmicpc.net/problem/1806
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-03-31
 * 간단 설명 : 부분합이 S 이상이 되는 부분 수열 길이 중 가장 짧은 길이를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 수열의 길이
        int S = Integer.parseInt(st.nextToken()); // 부분합의 크기

        st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[N + 1]; // 수열 누적합 배열
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken()) + arr[i - 1]; // 누적합 수열 저장 

        // 버퍼 닫기
        br.close();

        int minLen = N + 1; // 최소 길이
        int left  = 1; // 왼쪽 포인터
        int right = 1; // 오른쪽 포인터
        while (right <= N) {
            if (arr[right] - arr[left - 1] >= S) { // 부분합이 S 이상인 경우
                minLen = Math.min(minLen, right - left + 1); // 최소 길이 갱신
                left++; // 왼쪽 포인터 이동
            } else { // 부분합이 S 미만인 경우
                right++; // 오른쪽 포인터 이동
            }
        }

        // 결과값 출력하기
        System.out.print(minLen != N + 1 ? minLen : 0);
    } // main 종료
} // Main 종료