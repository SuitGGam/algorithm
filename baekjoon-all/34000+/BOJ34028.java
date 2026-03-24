/*
 * 백준 34028번 : 우리의 다정한 계절 속에(Seasons of Memories)
 * https://www.acmicpc.net/problem/34028
 * 난이도 : 브론즈 1
 * 풀이 날짜 : 2026-03-24
 * 간단 설명 : 데뷔 이후로 지난 계절의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ34028 {
    static int[][] month = {{}, {1, 2}, {3, 4, 5}, {6, 7, 8}, {9, 10, 11}, {12}};

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int A = Integer.parseInt(st.nextToken()); // 연
        int B = Integer.parseInt(st.nextToken()); // 월
        Integer.parseInt(st.nextToken()); // 일

        // 버퍼 닫기
        br.close();

        int m = 0; // 달에 해당하는 지난 계절의 수
        for (int i = 1; i <= 5; i++) {
            for (int j = 0; j < month[i].length; j++) if (month[i][j] == B) m = i;
        }
        int season = (A - 2015) * 4 + m; // 지난 계절의 수

        // 결과값 출력하기
        System.out.print(season);
    } // main 종료
} // Main 종료