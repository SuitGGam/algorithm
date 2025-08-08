/*
 * 백준 17219번 : 비밀번호 찾기
 * https://www.acmicpc.net/problem/17219
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-08-08
 * 간단 설명 : 해당 사이트의 비밀번호를 찾아주는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ17219 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 저장된 사이트 주소의 수
        int M = Integer.parseInt(st.nextToken()); // 비밀번호를 찾으려는 사이트 주소의 수

        TreeMap<String, String> site = new TreeMap<>(); // 사이트와 비밀번호를 저장할 TreeMap
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            site.put(st.nextToken(), st.nextToken()); // 사이트, 비밀번호 저장
        }

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < M; i++) { // 사이트 비밀번호 찾기
            sb.append(site.get(br.readLine())).append("\n"); // 결과값 추가하기
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료