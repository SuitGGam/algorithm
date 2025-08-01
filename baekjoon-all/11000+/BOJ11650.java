/*
 * 백준 11650번 : 좌표 정렬하기
 * https://www.acmicpc.net/problem/11650
 * 난이도 : 실버 5
 * 풀이 날짜 : 2025-08-02
 * 간단 설명 : 다중 조건 정렬을 하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ11650 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 회원의 수

        int[][] numbers = new int[N][2]; // x, y를 담기 위한 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            numbers[i][0] = Integer.parseInt(st.nextToken()); // x값 저장
            numbers[i][1] = Integer.parseInt(st.nextToken()); // y값 저장
        }

        // 버퍼 닫기
        br.close();

        // 정렬하기
        Arrays.sort(numbers, new Comparator<int[]>() {
            @Override
            public int compare(int[] n1, int[] n2) {
                if (n1[0] == n2[0]) {
                    return n1[1] - n2[1];
                } else {
                    return n1[0] - n2[0];
                }
            }
        });

        StringBuilder sb = new StringBuilder(); // 결과값을 저장하기 위한 객체
        for (int i = 0; i < N; i++) {
            sb.append(numbers[i][0]).append(" ").append(numbers[i][1]).append("\n");
        }

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료