/*
 * 백준 1780번 : 종이의 개수
 * https://www.acmicpc.net/problem/1780
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-08-11
 * 간단 설명 : 같은 숫자를 가진 종이가 몇 개인지 세는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1780 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int minusOne, zero, one; // -1, 0, 1의 개수
    static int[][] confetti; // 색종이 배열

    // 종이의 개수를 구하는 함수
    static void countConfetti(int row, int col, int len) {
        // 종료 조건
        if (len == 0) return;

        boolean same = true; // 색종이 내부의 숫자 같음 여부
        int number = confetti[row][col]; // 색종이 내부 첫 번째 숫자

        for (int x = row; x < row + len; x++) {
            for (int y = col; y < col + len; y++) {
                if (confetti[x][y] != number) {
                    same = false; // 같은 숫자가 아님
                    break;
                }
            }

            if (!same) break;
        }

        if (same) {
            if (number == -1) minusOne++;
            else if (number == 0) zero++;
            else one++;
        } else {
            int newLen = len / 3;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    countConfetti(row + newLen * i, col + newLen * j, newLen);
                }
            }
        }
    } // countConfetti 종료

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 배열의 크기

        confetti = new int[N][N]; // 색종이 배열 크기 지정
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 0; y < N; y++) {
                confetti[x][y] = Integer.parseInt(st.nextToken()); // 값 저장
            }
        }

        // 버퍼 닫기
        br.close();

        minusOne = zero = one = 0; // 초기화
        countConfetti(0, 0, N); // 종이 개수 구하기 시작

        // 결과값 출력하기
        System.out.print(minusOne + "\n" + zero + "\n" + one);
    } // main 종료
} // Main 종료