/*
 * 백준 10814번 : 나이순 정렬
 * https://www.acmicpc.net/problem/10814
 * 난이도 : 실버 5
 * 풀이 날짜 : 2025-08-01
 * 간단 설명 : 다중 조건 정렬을 하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ10814 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 회원의 수

        String[][] members = new String[N][2]; // 회원을 담기 위한 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            members[i][0] = st.nextToken(); // 회원 나이 저장
            members[i][1] = st.nextToken(); // 회원 이름 저장
        }

        // 버퍼 닫기
        br.close();

        // 정렬하기
        Arrays.sort(members, new Comparator<String[]>() {
            @Override
            public int compare(String[] s1, String[] s2) {
                return Integer.compare(Integer.parseInt(s1[0]), Integer.parseInt(s2[0]));
            }
        });

        StringBuilder sb = new StringBuilder(); // 결과값을 저장하기 위한 객체
        for (int i = 0; i < N; i++) {
            sb.append(members[i][0]).append(" ").append(members[i][1]).append("\n");
        }

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료