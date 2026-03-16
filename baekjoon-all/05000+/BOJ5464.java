/*
 * 백준 5464번 : 주차장
 * https://www.acmicpc.net/problem/5464
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-03-17
 * 간단 설명 : 주차장 수입을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.PriorityQueue;

public class BOJ5464 {
    static class Lot { // 주차장 정보 class
        int num; // 주차장 번호
        int fee; // 주차장 단위 요금

        Lot(int num, int fee) {
            this.num = num;
            this.fee = fee;
        }
    } // Lot 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 주차 공간의 수
        int M = Integer.parseInt(st.nextToken()); // 차량의 수

        PriorityQueue<Lot> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.num, o2.num)); // 주차장 정보를 저장할 PriorityQueue
        for (int i = 1; i <= N; i++) {
            int fee = Integer.parseInt(br.readLine()); // 주차장 단위 요금
            pq.add(new Lot(i, fee)); // 주차장 정보 add
        }

        int[] carWeight = new int[M + 1]; // 자동차 무게 배열
        for (int i = 1; i <= M; i++) carWeight[i] = Integer.parseInt(br.readLine()); // 자동차 무게 저장

        ArrayDeque<Integer> que = new ArrayDeque<>(); // 차량 대기 상태를 나타낼 Queue
        HashMap<Integer, Lot> map = new HashMap<>(); // 주차 여부를 저장할 HashMap
        int income = 0; // 주차장 수입
        for (int i = 0; i < M * 2; i++) {
            int car = Integer.parseInt(br.readLine()); // 차량 번호
            if (car > 0) { // 들어오는 차량인 경우
                if (!pq.isEmpty()) { // 주차장이 자리가 있는 경우
                    Lot lot = pq.poll(); // 주차장 정보
                    map.put(car, lot); // 주차하기
                } else { // 주차장이 꽉 차있는 경우
                    que.add(car); // 주차 대기시키기
                }
            } else { // 나가는 차량인 경우
                car *= -1; // 차량 번호
                Lot lot = map.get(car); // 주차된 정보 get
                map.remove(car); // 주차장에서 나가기
                income += carWeight[car] * lot.fee; // 주차 요금 정산
                pq.add(lot); // 주차장 생김
                if (!que.isEmpty()) map.put(que.poll(), pq.poll()); // 대기 중인 차량 주차시키기
            }
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(income);
    } // main 종료
} // Main 종료