/*
 * 백준 14502번 : 연구소
 * https://www.acmicpc.net/problem/14502
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-04-11
 * 간단 설명 : 최대로 지킬 수 있는 안전 영역 크기를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class BOJ14502 {
    static class Node { // 좌표를 담는 class
        int x; // x 좌표
        int y; // y 좌표

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    } // Node 종료

    static int N, M; // 연구소의 세로 크기, 연구소의 가로 크기
    static int[][] lab; // 연구소 배열
    static ArrayList<Node> blank, virus; // 바이러스 좌표 배열, 빈 칸 좌표 배열
    static int safetySize; // 안전 영역 크기

    // 탐색 배열 - 상우하좌
    static int[] dx = {-1, 0, 1,  0};
    static int[] dy = { 0, 1, 0, -1};
    static int[][] tmpLab; // 임시 연구소 배열

    static void spread() { // 바이러스를 퍼뜨리는 함수
        ArrayDeque<Node> queue = new ArrayDeque<>(); // 바이러스를 퍼뜨릴 Queue
        for (int i = 0; i < virus.size(); i++) {
            int x = virus.get(i).x; // 바이러스 x 좌표
            int y = virus.get(i).y; // 바이러스 y 좌표

            queue.add(new Node(x, y)); // 바이러스 add
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll(); // 현재 바이러스 위치

            for (int d = 0; d < 4; d++) {
                // 다음 좌표
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 연구소를 벗어나면 continue

                if (tmpLab[nx][ny] == 0) {
                    tmpLab[nx][ny] = 2; // 바이러스 퍼뜨리기
                    queue.add(new Node(nx, ny)); // 다음 좌표 add
                }
            }
        }

        int cnt = 0; // 안전 영역의 크기
        for (int x = 0; x < N; x++) {
            for (int y = 0; y< M; y++) if (tmpLab[x][y] == 0) cnt++;
        }

        safetySize = Math.max(safetySize, cnt); // 안전 영역 크기 갱신
    } // spread 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 연구소의 세로 크기
        M = Integer.parseInt(st.nextToken()); // 연구소의 가로 크기

        blank = new ArrayList<>(); // 빈 칸 좌표 배열
        virus = new ArrayList<>(); // 바이러스 좌표 배열

        lab = new int[N][M]; // 연구소 배열
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 0; y < M; y++) {
                lab[x][y] = Integer.parseInt(st.nextToken()); // 연구소 정보
                if (lab[x][y] == 0) blank.add(new Node(x, y)); // 빈 칸 좌표 add
                else if (lab[x][y] == 2) virus.add(new Node(x, y)); // 바이러스 좌표 add
            }
        }

        // 버퍼 닫기
        br.close();

        tmpLab = new int[N][M]; // 임시 연구소 배열
        int size = blank.size(); // 빈 칸의 개수
        for (int i = 0; i < size - 2; i++) {
            for (int j = i + 1; j < size - 1; j++) {
                for (int k = j + 1; k < size; k++) {
                    for (int x = 0; x < N; x++) tmpLab[x] = lab[x].clone(); // 연구소 원상 복구

                    // 빈 칸 벽으로 바꾸기
                    tmpLab[blank.get(i).x][blank.get(i).y] = 1;
                    tmpLab[blank.get(j).x][blank.get(j).y] = 1;
                    tmpLab[blank.get(k).x][blank.get(k).y] = 1;

                    spread();
                }
            }
        }

        // 결과값 출력하기
        System.out.print(safetySize);
    } // main 종료
} // Main 종료