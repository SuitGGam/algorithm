/*
 * 백준 21278번 : 호석이 두 마리 치킨
 * https://www.acmicpc.net/problem/21278
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-04-06
 * 간단 설명 : 치킨을 차리기 가장 좋은 건물 두 곳과 최소 거리의 합을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ21278 {
    static final int INF = 1_000_000; // 큰 거리값

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 건물의 수
        int M = Integer.parseInt(st.nextToken()); // 도로의 수

        int[][] dist = new int[N + 1][N + 1]; // 거리 배열

        // 거리 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // 자기 자신은 거리 0
        }

        // 도로 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken()); // 도시 1
            int B = Integer.parseInt(st.nextToken()); // 도시 2

            // 도시 연결
            dist[A][B] = 1;
            dist[B][A] = 1;
        }

        // 버퍼 닫기
        br.close();

        // 플로이드 워셜
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) dist[i][j] = dist[i][k] + dist[k][j]; // 최단 거리 갱신
                }
            }
        }

        int first  = 0; // 첫 번째 건물
        int second = 0; // 두 번째 건물
        int minSum = Integer.MAX_VALUE; // 최소 거리 합

        for (int i = 1; i <= N; i++) { // 첫 번째 건물
            for (int j = i + 1; j <= N; j++) { // 두 번째 건물
                int curSum = 0; // 현재 거리 합

                for (int k = 1; k <= N; k++) {
                    curSum += Math.min(dist[k][i], dist[k][j]); // 가까운 건물 선택
                }

                if (minSum > curSum * 2) {
                    minSum = curSum * 2; // 최소 거리 합 갱신
                    first  = i; // 첫 번째 건물 갱신
                    second = j; // 두 번째 건물 갱신
                }
            }
        }

        // 결과값 출력하기
        System.out.print(first + " " + second + " " + minSum);
    } // main 종료
} // Main 종료