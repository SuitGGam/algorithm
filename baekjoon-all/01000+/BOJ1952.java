/*
 * 백준 1952번 : 달팽이2
 * https://www.acmicpc.net/problem/1952
 * 난이도 : 브론즈 1
 * 풀이 날짜 : 2026-02-11
 * 간단 설명 : 달팽이 모양으로 선을 그릴 때 몇 번 꺾이는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1952 {
    // 우하좌상
    static int[] dx = {0, 1,  0, -1};
    static int[] dy = {1, 0, -1,  0};

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int M = Integer.parseInt(st.nextToken()); // 표의 세로 크기
        int N = Integer.parseInt(st.nextToken()); // 표의 가로 크기

        // 버퍼 닫기
        br.close();

        boolean[][] visited = new boolean[M][N]; // 표 배열
        int changeCnt = 0; // 선이 꺾어지는 횟수
        int remain = N * M - 1; // 남은 칸의 개수
        int curX = 0; // 현재 X 좌표
        int curY = 0; // 현재 Y 좌표
        visited[curX][curY] = true; // 시작점 방문 처리
        int dir = 0; // 진행 방향
        while (remain > 0) {
            int nx = curX + dx[dir];
            int ny = curY + dy[dir];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[nx][ny]) { // 진행이 불가능한 경우
                dir = (dir + 1) % 4; // 방향 전환
                changeCnt++; // 선이 꺾어지는 횟수 증가
            } else { // 진행이 가능한 경우
                visited[nx][ny] = true; // 방문 처리
                curX = nx; // 방향대로 진행
                curY = ny; // 방향대로 진행
                remain--; // 남은 칸 감소
            }
        }

        // 결과값 출력하기
        System.out.print(changeCnt);
    } // main 종료
} // Main 종료