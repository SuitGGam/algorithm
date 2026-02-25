/*
 * 백준 3661번 : 생일 선물
 * https://www.acmicpc.net/problem/3661
 * 난이도 : 골드 3
 * 풀이 날짜 : 2026-02-25
 * 간단 설명 : 친구의 생일 선물을 살 돈을 최대한 공평하게 나누는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {
    static class Friend { // 친구 정보 class
        int cost; // 최대로 낼 수 있는 금액
        int idx;  // 리스트 순서

        Friend(int cost, int idx) {
            this.cost = cost;
            this.idx = idx;
        }
    } // Friend 종료

    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체

    static void divide(PriorityQueue<Friend> pq, int p, int n, int avg) { // 금액을 나누는 함수
        int[] result = new int[n + 1]; // 각자 낼 수 있는 금액 배열
        while (!pq.isEmpty()) {
            Friend cur = pq.poll(); // 친구 정보

            int curCost = cur.cost; // 낼 수 있는 최대 금액
            int curIdx = cur.idx; // 친구의 순서
            if (curCost <= avg) { // 낼 수 있는 금액이 평균보다 작거나 같은 경우
                result[curIdx] = curCost;
                p -= curCost;
            } else { // 낼 수 있는 금액이 평균보다 큰 경우
                int curAvg = p / (pq.size() + 1); // 현재 평균

                if (curCost <= curAvg) { // 현재 평균보다 낼 수 있는 금액이 적거나 같은 경우
                    result[curIdx] = curCost;
                    p -= curCost;
                } else { // 현재 평균보다 낼 수 있는 금액이 큰 경우
                    result[curIdx] = curAvg;
                    p -= curAvg;
                }
            }
        }

        // 결과값 추가하기
        for (int i = 1; i <= n; i++) sb.append(result[i]).append(" ");
        sb.append("\n");
    } // divide 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken()); // 선물의 가격
            int n = Integer.parseInt(st.nextToken()); // 친구의 수

            // 친구 정보를 담을 PriorityQueue
            PriorityQueue<Friend> pq = new PriorityQueue<>((o1, o2) -> {
                if (o1.cost != o2.cost) return Integer.compare(o1.cost, o2.cost);
                else return Integer.compare(o2.idx, o1.idx);
            });

            st = new StringTokenizer(br.readLine(), " ");
            int sum = 0; // 각자 낼 수 있는 비용의 합
            for (int i = 1; i <= n; i++) {
                int cost = Integer.parseInt(st.nextToken()); // 비용
                sum += cost; // 비용 합산
                pq.add(new Friend(cost, i)); // 친구 정보 add
            }

            if (sum < p) {sb.append("IMPOSSIBLE\n"); continue;} // 모두가 낼 수 있는 금액보다 선물의 가격이 높으면 continue
            divide(pq, p, n, p / n); // 금액 나누기
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료