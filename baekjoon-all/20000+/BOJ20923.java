/*
 * 백준 20923번 : 숫자 할리갈리 게임
 * https://www.acmicpc.net/problem/20923
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-04-10
 * 간단 설명 : 할리갈리 게임의 승자를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ20923 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 카드의 수
        int M = Integer.parseInt(st.nextToken()); // 게임 진행 수

        ArrayDeque<Integer> doDeck = new ArrayDeque<>(); // 도도의 덱 Deque
        ArrayDeque<Integer> suDeck = new ArrayDeque<>(); // 수연의 덱 Deque

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int dodo   = Integer.parseInt(st.nextToken()); // 도도의 카드
            int suyeon = Integer.parseInt(st.nextToken()); // 수연의 카드

            // 덱에 카드 쌓기
            doDeck.addLast(dodo);
            suDeck.addLast(suyeon);
        }

        // 버퍼 닫기
        br.close();

        String result = "dosu"; // 게임 결과
        ArrayDeque<Integer> doGround = new ArrayDeque<>(); // 도도의 그라운드 Queue
        ArrayDeque<Integer> suGround = new ArrayDeque<>(); // 수연의 그라운드 Queue
        for (int i = 0; i < M; i++) {
            if (i % 2 == 0) doGround.add(doDeck.pollLast()); // 도도의 차례
            else suGround.add(suDeck.pollLast()); // 수연의 차례

            if (doDeck.isEmpty() || suDeck.isEmpty()) break; // 둘 중 한 명의 덱이 비어있으면 종료

            int dTop = doGround.isEmpty() ? -1 : doGround.peekLast(); // 도도의 그라운드 맨 위 카드
            int sTop = suGround.isEmpty() ? -1 : suGround.peekLast(); // 수연의 그라운드 맨 위 카드

            if (dTop == 5 || sTop == 5) { // 도도가 종을 친 경우
                // 수연이의 카드부터 옮기기
                while (!suGround.isEmpty()) doDeck.addFirst(suGround.poll());
                while (!doGround.isEmpty()) doDeck.addFirst(doGround.poll());
            } else if (dTop != -1 && sTop != -1 && dTop + sTop == 5) { // 수연이가 종을 친 경우
                // 도도의 카드부터 옮기기
                while (!doGround.isEmpty()) suDeck.addFirst(doGround.poll());
                while (!suGround.isEmpty()) suDeck.addFirst(suGround.poll());
            }
        }

        if (doDeck.size() > suDeck.size()) result = "do";
        else if (doDeck.size() < suDeck.size()) result = "su";

        // 결과값 출력하기
        System.out.print(result);
    } // main 종료
} // Main 종료