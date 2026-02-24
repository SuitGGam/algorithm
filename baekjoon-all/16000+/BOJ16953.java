/*
 * 백준 16953번 : A → B
 * https://www.acmicpc.net/problem/16953
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-02-24
 * 간단 설명 : A에서 B로 만드는데 필요한 최소 연산 횟수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ16953 {
    static class Node { // 연산 과정을 담을 class
        long num; // 연산된 숫자
        int cnt; // 연산된 횟수

        Node(long num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long A = Long.parseLong(st.nextToken()); // 문자열의 길이
        long B = Long.parseLong(st.nextToken()); // 조건을 만족하는 쌍의 수

        // 버퍼 닫기
        br.close();

        int minCal = Integer.MAX_VALUE; // 최소 연산 횟수
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(A, 1)); // 시작점 add
        while (!queue.isEmpty()) {
            Node cur = queue.poll(); // 현재 Node

            if (cur.num == B) minCal = Math.min(minCal, cur.cnt); // 최소 연산 횟수 갱신

            long nxt1 = cur.num * 2; // 2를 곱한 숫자
            long nxt2 = cur.num * 10 + 1; // 가장 오른쪽에 1을 추가한 숫자
            int nxtCnt = cur.cnt + 1; // 연산 횟수

            // 다음 Node add
            if (nxt1 <= B) queue.add(new Node(nxt1, nxtCnt));
            if (nxt2 <= B) queue.add(new Node(nxt2, nxtCnt));
        }

        // 결과값 출력하기
        System.out.print(minCal == Integer.MAX_VALUE ? -1 : minCal);
    } // main 종료
} // Main 종료