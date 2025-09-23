/*
 * 백준 31860번 : 열심히 일하는 중
 * https://www.acmicpc.net/problem/31860
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-09-24
 * 간단 설명 : 일을 다 하는데 걸리는 날의 수와 각 날의 만족도를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Comparator;

public class BOJ31860 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int M, K, doDays; // 일을 처리했을 때 감소하는 중요도, 일의 완료 기준, 일을 다 하기 위해 걸리는 날의 수
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // 일을 담을 최대 힙
    static StringBuilder sb = new StringBuilder();
    static void toDo() { // 할 일을 처리하는 함수
        int yesterdaySatisfaction = 0; // 전날 만족도
        while (!maxHeap.isEmpty()) {
            int todayToDo = maxHeap.poll(); // 오늘 해야 할 일의 중요도
            
            yesterdaySatisfaction = yesterdaySatisfaction / 2 + todayToDo; // 전날 만족도 갱신
            sb.append(yesterdaySatisfaction).append("\n"); // 전날 만족도 결과값 추가하기
            todayToDo -= M; // 오늘 한 일 중요도 감소
            
            if (todayToDo > K) maxHeap.add(todayToDo); // 완료가 안 된 일이면 다시 maxHeap에 저장
            doDays++; // 일을 하는데 걸린 날의 수 증가
        }
    } // toDo 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 해야 할 일의 개수
        M = Integer.parseInt(st.nextToken()); // 일을 처리했을 때 감소하는 중요도
        K = Integer.parseInt(st.nextToken()); // 일의 완료 기준
        
        for (int i = 0; i < N; i++) {
            maxHeap.add(Integer.parseInt(br.readLine())); // 할 일의 중요도 저장
        }
        
        // 버퍼 닫기
        br.close();
        
        doDays = 0; // 일을 다 하기 위해 걸리는 날의 수 초기화
        toDo(); // 할 일 처리하기
        
        // 결과값 출력하기
        System.out.println(doDays);
        System.out.print(sb);
    } // main 종료
} // Main 종료