/*
 * 백준 15702번 : 중간고사 채점
 * https://www.acmicpc.net/problem/15702
 * 난이도 : 실버 5
 * 풀이 날짜 : 2025-10-29
 * 간단 설명 : 중간고사 점수가 가장 높은 학생을 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15702 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] score = new int[N]; // 점수 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) score[i] = Integer.parseInt(st.nextToken()); // 점수 저장
        
        int maxNum = -1; // 최고 점수 응시자
        int maxSum = -1; // 최고 점수
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int curNum = Integer.parseInt(st.nextToken()); // 응시자 번호
            int tmpSum = 0; // 임시 점수
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("O")) tmpSum += score[j]; // 점수 추가
            }
            
            if (maxSum < tmpSum) {
                maxNum = curNum; // 응시자 번호 갱신
                maxSum = tmpSum; // 최고 점수 갱신
            } else if (maxSum == tmpSum && maxNum > curNum) maxNum = curNum; // 응시자 번호 갱신
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(maxNum + " " + maxSum);
    } // main 종료
} // Main 종료