/*
 * 백준 20157번 : 화살을 쏘자!
 * https://www.acmicpc.net/problem/20157
 * 난이도 : 골드 5
 * 풀이 날짜 : 2026-02-16
 * 간단 설명 : 화살을 한 방향으로 쐈을 때 얻을 수 있는 최대 점수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class BOJ20157 {
    static int gcd(int a, int b) { // 최대공약수를 구하는 함수
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }

        return a; // 최대공약수 반환
    } // gcd 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 풍선의 개수
        HashMap<String, Integer> map = new HashMap<>(); // 기약 분수를 담을 HashMap
        int maxScore = 0; // 최대 점수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()); // 풍선의 x좌표
            int y = Integer.parseInt(st.nextToken()); // 풍선의 y좌표

            int gcd = gcd(Math.abs(x), Math.abs(y)); // 최대공약수 찾기
            int dx = x / gcd; // 기약분수로 변환
            int dy = y / gcd; // 기약분수로 변환

            String key = dx + "/" + dy; // 고유 키
            map.put(key, map.getOrDefault(key, 0) + 1); // 고유 키에 대한 개수 갱신
            maxScore = Math.max(maxScore, map.get(key)); // 최대 점수 갱신
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(maxScore);
    } // main 종료
} // Main 종료