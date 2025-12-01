/*
 * 백준 25418번 : 정수 a를 k로 만들기
 * https://www.acmicpc.net/problem/25418
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-12-02
 * 간단 설명 : 정수 a를 k로 만드는데 필요한 최소 연산 횟수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ25418 {
    static final int MAX_RANGE = 1_000_000; // 최대 범위
    static int bfs(int A, int K) { // 연산1과 2를 bfs로 탐색하는 함수
        boolean[] visited = new boolean[MAX_RANGE + 1]; // 방문 처리 배열
        int[] cal = new int[MAX_RANGE + 1]; // 연산 횟수 배열
        for (int i = 1; i <= MAX_RANGE; i++) cal[i] = Integer.MAX_VALUE; // 연산 횟수 초기화
        
        ArrayDeque<int[]> queue = new ArrayDeque<>(); // 탐색할 숫자를 넣을 큐
        queue.add(new int[] { A, 0 }); // 시작점 add
        visited[A] = true; // 방문 처리
        while (!queue.isEmpty()) {
            int[] curArr = queue.poll(); // 현재 숫자와 연산 횟수
            int curNum = curArr[0]; // 현재 숫자
            int curCount = curArr[1]; // 현재 누적 연산 횟수
            
            // 연산 1 : A + 1
            int next1 = curNum + 1;
            if (next1 <= MAX_RANGE && !visited[next1]) {
                cal[next1] = curCount + 1; // 연산 횟수 갱신
                queue.add(new int[] { next1, curCount + 1 }); // 다음 숫자 add
                visited[next1] = true; // 방문 처리
            }
            
            // 연산 2 : A * 2
            int next2 = curNum * 2;
            if (next2 <= MAX_RANGE && !visited[next2]) {
                cal[next2] = curCount + 1; // 연산 횟수 갱신
                queue.add(new int[] { next2, curCount + 1 }); // 다음 숫자 add
                visited[next2] = true; // 방문 처리
            }
            
            if (visited[K]) break; // 최소 연산 횟수 찾으면 종료
        }
        
        return cal[K]; // 연산 횟수 반환
    } // bfs 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int A = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // 버퍼 닫기
        br.close();
        
        int minCal = bfs(A, K); // 최소 연산 횟수
        
        // 결과값 출력하기
        System.out.print(minCal);
    } // main 종료
} // Main 종료