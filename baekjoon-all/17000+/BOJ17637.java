/*
 * 백준 17637번 : Count Squares
 * https://www.acmicpc.net/problem/17637
 * 난이도 : 실버 3
 * 풀이 날짜 : 2026-02-24
 * 간단 설명 : 세로선과 가로선으로 만들어진 사각형 중 정사각형의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ17637 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int h = Integer.parseInt(st.nextToken()); // 가로선의 개수
        int v = Integer.parseInt(st.nextToken()); // 세로선의 개수

        int[] horizon = new int[h + 1]; // 가로선 좌표 배열
        int[] prefixHSum = new int[h + 1]; // 가로선 차이 누적 합 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= h; i++) {
            horizon[i] = Integer.parseInt(st.nextToken()); // 가로선 좌표
            prefixHSum[i] = prefixHSum[i - 1] + (horizon[i] - horizon[i - 1]); // 차이 누적 합
        }

        int[] vertical = new int[v + 1]; // 세로선 좌표 배열
        int[] prefixVSum = new int[v + 1]; // 세로선 차이 누적 합 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= v; i++) {
            vertical[i] = Integer.parseInt(st.nextToken()); // 세로선 좌표
            prefixVSum[i] = prefixVSum[i - 1] + (vertical[i] - vertical[i - 1]); // 차이 누적 합
        }

        // 버퍼 닫기
        br.close();

        TreeMap<Integer, Integer> hMap = new TreeMap<>(); // 가로선 TreeMap
        for (int i = 1; i <= h - 1; i++) {
            for (int j = i + 1; j <= h; j++) {
                int gap = Math.abs(prefixHSum[i] - prefixHSum[j]); // 가로선끼리의 차이
                hMap.put(gap, hMap.getOrDefault(gap, 0) + 1); // 차이 개수 증가
            }
        }

        TreeMap<Integer, Integer> vMap = new TreeMap<>(); // 세로선 TreeMap
        for (int i = 1; i <= v - 1; i++) {
            for (int j = i + 1; j <= v; j++) {
                int gap = Math.abs(prefixVSum[i] - prefixVSum[j]); // 세로선끼리의 차이
                vMap.put(gap, vMap.getOrDefault(gap, 0) + 1); // 차이 개수 증가
            }
        }

        int squareCnt = 0; // 정사각형의 개수
        for (Map.Entry<Integer, Integer> hEntry : hMap.entrySet()) {
            if (vMap.containsKey(hEntry.getKey())) { // 같은 길이가 있는 경우
                squareCnt += hEntry.getValue() * vMap.get(hEntry.getKey()); // 서로 개수 곱해주기
            }
        }

        // 결과값 출력하기
        System.out.print(squareCnt);
    } // main 종료
} // Main 종료