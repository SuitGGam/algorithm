/*
 * 백준 1236번 : 성 지키기
 * https://www.acmicpc.net/problem/1236
 * 난이도 : 브론즈 1
 * 풀이 날짜 : 2026-02-16
 * 간단 설명 : 모든 행과 열에 경비원이 있어야 할 때 필요한 최소 경비원이 몇 명인지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1236 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 성의 세로 크기
        int M = Integer.parseInt(st.nextToken()); // 성의 가로 크기

        char[][] castle = new char[N][M]; // 성 배열
        boolean[] guardX = new boolean[N]; // 가로 경비원 유무
        boolean[] guardY = new boolean[M]; // 세로 경비원 유무
        for (int x = 0; x < N; x++) {
            String info = br.readLine(); // 성의 정보
            for (int y = 0; y < M; y++) {
                castle[x][y] = info.charAt(y); // 성의 정보 저장
                if (castle[x][y] == 'X') {
                    guardX[x] = true; // 경비원이 있는 행
                    guardY[y] = true; // 경비원이 있는 열
                }
            }
        }

        // 버퍼 닫기
        br.close();

        int notXCnt = 0; // 경비원이 없는 행의 수
        for (int i = 0; i < N; i++) if (!guardX[i]) notXCnt++;
        int notYCnt = 0; // 경비원이 없는 열의 수
        for (int i = 0; i < M; i++) if (!guardY[i]) notYCnt++;

        // 결과값 출력하기
        System.out.print(Math.max(notXCnt, notYCnt));
    } // main 종료
} // Main 종료