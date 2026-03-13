/*
 * 백준 18311번 : 왕복
 * https://www.acmicpc.net/problem/18311
 * 난이도 : 실버 5
 * 풀이 날짜 : 2026-03-13
 * 간단 설명 : 이동 거리가 주어졌을 때 어느 코스를 지나고 있는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18311 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 코스의 수
        long K = Long.parseLong(st.nextToken()); // 지나고 있는 거리

        st = new StringTokenizer(br.readLine(), " ");
        int[] dist = new int[N + 1]; // 거리 배열
        for (int i = 1; i <= N; i++) dist[i] = Integer.parseInt(st.nextToken()); // 코스 거리 입력

        // 버퍼 닫기
        br.close();

        long[] courseDist = new long[N * 2 + 1]; // 코스별 거리 배열
        courseDist[1] = dist[1]; // 1코스 거리 설정
        for (int i = 2; i <= N; i++) courseDist[i] = courseDist[i - 1] + dist[i]; // 1 -> N 누적 합 코스 거리 설정
        for (int i = 1; i <= N; i++) courseDist[N + i] = courseDist[N - 1 + i] + dist[N + 1 - i]; // N -> 1 누적 합 코스 거리 설정

        int course = 0; // 지나고 있는 코스 번호
        for (int i = 1; i <= N; i++) if (K >= courseDist[i - 1] && K <= courseDist[i]) course = i;
        for (int i = 0; i < N; i++) if (K >= courseDist[N * 2 - 1 - i] && K <= courseDist[N * 2 - i]) course = i + 1;

        // 결과값 출력하기
        System.out.print(course);
    } // main 종료
} // Main 종료