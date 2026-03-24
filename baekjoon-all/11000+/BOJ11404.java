/*
 * 백준 11404번 : 플로이드
 * https://www.acmicpc.net/problem/11404
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-03-24
 * 간단 설명 : 각 도시로 가는 최소 비용을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ11404 {
    static final int INF = 100_000_000; // 초기화용 큰 값

    static int n; // 도시의 수
    static int[][] city; // 도시 배열
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체

    static void floydWarshall() { // 각 도시 간의 최소 이동 비용을 구하는 함수
        // 최소 비용 구하기
        for (int k = 1; k <= n; k++) { // 경유지
            for (int i = 1; i <= n; i++) { // 출발지 
                if (city[i][k] == INF) continue; // i에서 k까지 못 가면 continue

                for (int j = 1; j <= n; j++) { // 도착지
                    if (city[i][j] > city[i][k] + city[k][j]) city[i][j] = city[i][k] + city[k][j]; // 최소 비용 갱신
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(city[i][j] == INF ? 0 : city[i][j]).append(" "); // 결과값 추가하기
            }

            sb.append("\n"); // 개행 처리
        }
    } // floydWarshall 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 도시의 수
        int m = Integer.parseInt(br.readLine()); // 버스의 수

        city = new int[n + 1][n + 1]; // 도시 배열

        // 최소 비용 배열 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(city[i], INF);
            city[i][i] = 0; // 도착지가 자기 자신인 경우
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()); // 시작 도시
            int end = Integer.parseInt(st.nextToken()); // 도착 도시
            int cost = Integer.parseInt(st.nextToken()); // 비용

            city[start][end] = Math.min(city[start][end], cost); // 버스 연결
        }

        // 버퍼 닫기
        br.close();

        floydWarshall(); // 최소 비용 구하기

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료