/*
 * 백준 26646번 : 알프스 케이블카
 * https://www.acmicpc.net/problem/26646
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-02-18
 * 간단 설명 : 알프스 산맥을 등산하기 위해 드는 노선 최소 설치 비용을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ26646 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 산의 수
        long[] height = new long[N]; // 산의 높이 배열
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) height[i] = Long.parseLong(st.nextToken()); // 산의 높이 저장

        // 버퍼 닫기
        br.close();

        long minCost = 0; // 최소 비용
        for (int i = 0; i < N - 1; i++) {
            long heightGap = Math.abs(height[i] - height[i + 1]); // 인근한 두 산의 높이 차이
            long distGap = height[i] + height[i + 1]; // 인근한 두 산의 거리 차이
            minCost += (heightGap * heightGap) + (distGap * distGap);
        }

        // 결과값 출력하기
        System.out.print(minCost);
    } // main 종료
} // Main 종료