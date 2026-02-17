/*
 * 백준 1043번 : 거짓말
 * https://www.acmicpc.net/problem/1043
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-02-17
 * 간단 설명 : 과장된 이야기를 할 수 있는 파티의 최댓값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class BOJ1043 {
    static boolean[] isTruthKnown; // 진실을 아는 사람 배열
    static ArrayDeque<Integer> queue; // 진실 전파를 위한 Queue
    static ArrayList<Integer>[] people; // 사람 간의 관계를 나타낼 ArrayList

    static void bfs() { // 진실을 전파하는 함수
        while (!queue.isEmpty()) {
            int cur = queue.poll(); // 현재 진실을 아는 사람 번호
            for (int nxt : people[cur]) {
                if (!isTruthKnown[nxt]) {
                    isTruthKnown[nxt] = true; // 진실 전파
                    queue.add(nxt); // Queue에 add
                }
            }
        }
    } // bfs 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 사람의 수
        int M = Integer.parseInt(st.nextToken()); // 파티의 수

        st = new StringTokenizer(br.readLine(), " ");
        int truthCnt = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
        isTruthKnown = new boolean[N + 1]; // 진실을 아는 사람 배열
        queue = new ArrayDeque<>(); // 진실 전파를 위한 Queue

        for (int i = 0; i < truthCnt; i++) {
            int person = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 번호
            isTruthKnown[person] = true; // 진실을 아는 사람 처리
            queue.add(person); // queue에 add
        }

        people = new ArrayList[N + 1]; // 사람 간의 관계를 나타낼 ArrayList
        for (int i = 1; i <= N; i++) people[i] = new ArrayList<>(); // 사람 생성

        ArrayList<Integer>[] party = new ArrayList[M]; // 파티 배열
        for (int i = 0; i < M; i++) {
            party[i] = new ArrayList<>(); // 파티 생성
            st = new StringTokenizer(br.readLine(), " ");
            int participant = Integer.parseInt(st.nextToken()); // 파티 참가자 수
            for (int j = 0; j < participant; j++) party[i].add(Integer.parseInt(st.nextToken())); // 파티 참가자 add

            for (int p1 : party[i]) {
                for (int p2 : party[i]) if (p1 != p2) people[p1].add(p2); // 같은 파티 사람끼리 양방향 연결
            }
        }

        // 버퍼 닫기
        br.close();

        bfs(); // 진실 전파

        int lieCnt = 0; // 거짓말을 할 수 있는 파티의 수
        for (int i = 0; i < M; i++) {
            boolean canLie = true; // 거짓말 유효 여부
            for (int person : party[i]) {
                if (isTruthKnown[person]) canLie = false; // 거짓말 불가능
            }

            if (canLie) lieCnt++;
        }

        // 결과값 출력하기
        System.out.print(lieCnt);
    } // main 종료
} // Main 종료