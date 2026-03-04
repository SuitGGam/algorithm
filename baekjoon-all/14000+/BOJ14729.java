/*
 * 백준 14729번 : 칠무해
 * https://www.acmicpc.net/problem/14729
 * 난이도 : 실버 5
 * 풀이 날짜 : 2026-03-04
 * 간단 설명 : 점수가 가장 낮은 7명의 성적을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ14729 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 학생의 수
        PriorityQueue<Double> pq = new PriorityQueue<>((o1, o2) -> {return Double.compare(o2, o1);});

        for (int i = 0; i < N; i++) {
            double grade = Double.parseDouble(br.readLine()); // 학생의 성적
            if (pq.size() < 7) pq.add(grade);
            else {
                if (pq.peek() > grade) {
                    pq.poll();
                    pq.add(grade);
                }
            }
        }

        // 버퍼 닫기
        br.close();

        double[] seven = new double[7];
        for (int i = 0; i < 7; i++) seven[i] = pq.poll();

        StringBuilder sb = new StringBuilder();
        for (int i = 6; i >= 0; i--) {
            sb.append(String.format("%.3f", seven[i])).append("\n");
        }

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료