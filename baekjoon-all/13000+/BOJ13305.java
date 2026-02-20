/*
 * 백준 13305번 : 주유소
 * https://www.acmicpc.net/problem/13305
 * 난이도 : 실버 3
 * 풀이 날짜 : 2026-02-20
 * 간단 설명 : 첫 도시부터 마지막 도시까지 가는 최소 비용을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13305 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 도시의 수

        long[] dist = new long[N]; // 도시 사이의 거리 누적 합 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i < N; i++) {
            dist[i] = Long.parseLong(st.nextToken()) + dist[i - 1]; // 거리 누적 합 배열
        }

        long[] cost = new long[N + 1]; // 주유소의 가격
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) cost[i] = Long.parseLong(st.nextToken()); // 주유소 가격 저장

        // 버퍼 닫기
        br.close();

        int[] nxtLower = new int[N]; // 다음 주유소 가격이 낮은 곳의 인덱스
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (cost[i] >= cost[j]) {nxtLower[i] = j; break;} // 가격이 낮으면 갱신
            }

            if (nxtLower[i] == 0) nxtLower[i] = N; // 낮은 곳이 없으면 마지막 위치 기입
        }

        long minCost = 0; // 최소 비용
        for (int i = 1; i < N; i++) {
            minCost += cost[i] * (dist[nxtLower[i] - 1] - dist[i - 1]); // 거리만큼 주유하기
            i = nxtLower[i] - 1; // 계산한 자리는 스킵
        }

        // 결과값 출력하기
        System.out.print(minCost);
    } // main 종료
} // Main 종료