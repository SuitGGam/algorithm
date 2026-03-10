/*
 * 백준 35306번 : 월간 향유회 시즌 종료
 * https://www.acmicpc.net/problem/35306
 * 난이도 : 브론즈 1
 * 풀이 날짜 : 2026-03-10
 * 간단 설명 : 다음 시즌 수장으로 지목할 수 있는 운영진의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ35306 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 운영진의 수
        int K = Integer.parseInt(st.nextToken()); // 평가 지표의 수

        int[][] rating = new int[N][K]; // 평가표 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < K; j++) rating[i][j] = Integer.parseInt(st.nextToken()); // 능력 저장
        }

        // 버퍼 닫기
        br.close();

        boolean[] recommended = new boolean[N]; // 지목 여부 처리 배열
        for (int j = 0; j < K; j++) {
            int idx = -1; // 지목할 운영진
            int max = -1; // 최대 능력치
            int cnt = 0; // 최대 능력치를 가진 운영진의 수
            for (int i = 0; i < N; i++) {
                if (max < rating[i][j]) { // 최대치인 경우
                    max = rating[i][j]; // 최대치 갱신
                    cnt = 1;
                    idx = i;
                } else if (max == rating[i][j]) cnt++; // 최대치와 같은 경우
            }

            if (cnt == 1) recommended[idx] = true; // 수장 지목 처리
        }

        int cnt = 0; // 지목할 수 있는 운영진의 수
        for (int i = 0; i < N; i++) if (recommended[i]) cnt++;

        // 결과값 출력하기
        System.out.print(N != 1 ? cnt : 1);
    } // main 종료
} // Main 종료