/*
 * 백준 18868번 : 멀티버스 Ⅰ
 * https://www.acmicpc.net/problem/18868
 * 난이도 : 브론즈 1
 * 풀이 날짜 : 2026-02-22
 * 간단 설명 : 균등한 우주의 순서쌍의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18868 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int M = Integer.parseInt(st.nextToken()); // 우주의 수
        int N = Integer.parseInt(st.nextToken()); // 행성의 수

        int[][] space = new int[M][N]; // 우주 배열
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) space[i][j] = Integer.parseInt(st.nextToken()); // 행성 크기 저장
        }

        // 버퍼 닫기
        br.close();

        int cnt = 0; // 균등한 우주의 쌍 개수
        for (int i = 0; i < M - 1; i++) {
            for (int j = i + 1; j < M; j++) {
                boolean valid = true; // 행성의 균등성
                for (int k = 0; k < N - 1; k++) {
                    for (int l = k + 1; l < N; l++) {
                        if (!(((space[i][k] < space[i][l]) && (space[j][k] < space[j][l])) ||
                                ((space[i][k] == space[i][l]) && (space[j][k] == space[j][l])) ||
                                ((space[i][k] > space[i][l]) && (space[j][k] > space[j][l])) )) valid = false; // 균등하지 않음
                    }
                }

                if (valid) cnt++; // 유효한 행성 개수 증가
            }
        }

        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료