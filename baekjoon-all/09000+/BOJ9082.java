/*
 * 백준 9082번 : 지뢰찾기
 * https://www.acmicpc.net/problem/9082
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-02-28
 * 간단 설명 : 최대로 놓을 수 있는 지뢰의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9082 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine()); // 배열의 크기

            int[] mineCnt = new int[N]; // 지뢰의 수 배열
            String info = br.readLine(); // 지뢰의 수 정보
            for (int i = 0; i < N; i++) mineCnt[i] = info.charAt(i) - '0';

            char[] mine = br.readLine().toCharArray(); // 지뢰 배열

            int maxCnt = 0; // 최대로 놓을 수 있는 지뢰의 수
            for (int i = 0; i < N; i++) {
                boolean canMine = true; // 지뢰를 설치할 수 있는지 여부

                for (int d = -1; d <= 1; d++) {
                    int ni = i + d;

                    if (ni < 0 || ni >= N) continue; // 배열을 벗어나면 continue

                    // 주변 숫자 중 0이 있으면 지뢰를 놓을 수 없음
                    if (mineCnt[ni] == 0) {canMine = false; break;}
                }

                if (canMine) {
                    maxCnt++; // 놓을 수 있는 지뢰 1개 증가

                    for (int d = -1; d <= 1; d++) {
                        int ni = i + d;

                        if (ni < 0 || ni >= N) continue; // 배열을 벗어나면 continue

                        mineCnt[ni]--; // 놓을 수 있는 지뢰 개수 감소
                    }
                }
            }

            sb.append(maxCnt).append("\n"); // 결과값 추가하기
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료