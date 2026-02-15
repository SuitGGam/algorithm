/*
 * 백준 18221번 : 교수님 저는 취업할래요
 * https://www.acmicpc.net/problem/18221
 * 난이도 : 실버 5
 * 풀이 날짜 : 2026-02-16
 * 간단 설명 : 교수님에게서 도망갈 수 있는지 없는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18221 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 자연수
        int[][] room = new int[N][N]; // 강의실 배열

        int proX = 0; // 교수님의 X좌표
        int proY = 0; // 교수님의 Y좌표
        int sungX = 0; // 성규의 X좌표
        int sungY = 0; // 성규의 Y좌표

        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 0; y < N; y++) {
                room[x][y] = Integer.parseInt(st.nextToken()); // 강의실 정보 저장

                if (room[x][y] == 2) {sungX = x; sungY = y;} // 성규 자리 좌표 저장
                if (room[x][y] == 5) {proX = x; proY = y;} // 교수님 자리 좌표 저장
            }
        }

        // 버퍼 닫기
        br.close();

        // 성규와 교수님으로 이루어진 직사각형
        int minX = Math.min(proX, sungX);
        int minY = Math.min(proY, sungY);
        int maxX = Math.max(proX, sungX);
        int maxY = Math.max(proY, sungY);

        // 성규와 교수님 사이의 거리
        int gapX = Math.abs(proX - sungX) * Math.abs(proX - sungX);
        int gapY = Math.abs(proY - sungY) * Math.abs(proY - sungY);

        int studentCnt = 0; // 성규와 교수님 사이에 앉은 학생 수
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) if (room[x][y] == 1) studentCnt++; // 학생 수 증가
        }

        int result = 0; // 도망칠 수 있는지 여부
        if (gapX + gapY >= 25 && studentCnt >= 3) result = 1;

        // 결과값 출력하기
        System.out.print(result);
    } // main 종료
} // Main 종료