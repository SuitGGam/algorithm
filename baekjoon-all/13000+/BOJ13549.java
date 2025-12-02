/*
 * 백준 13549번 : 숨바꼭질 3
 * https://www.acmicpc.net/problem/13549
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-12-03
 * 간단 설명 : 수빈이가 동생을 찾는데 가장 적게 걸리는 시간을 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class BOJ13549 {
    static final int RANGE = 100_000 + 1; // 점의 범위
    static int findYounger(int N, int K) { // 수빈이가 동생을 찾는 함수
        int[] time = new int[RANGE]; // 시간 배열
        for (int i = 0; i < RANGE; i++) time[i] = Integer.MAX_VALUE; // 시간 초기화
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1])); // 현재 위치와 시간을 담을 우선순위 큐
        time[N] = 0; // 시작점 시간 초기화
        minHeap.add(new int[] { N, 0 }); // 시작점 add
        
        while (!minHeap.isEmpty()) {
            int[] curInfo = minHeap.poll(); // 현재 정보
            int curLoc = curInfo[0]; // 현재 위치
            int curTime = curInfo[1]; // 현재 걸린 시간
            
            if (time[curLoc] < curTime) continue; // 기존 시간보다 현재 시간이 더 크면 continue
            
            // 1. X * 2
            int nxt1 = curLoc * 2; // 다음 위치
            if (nxt1 >= 0 && nxt1 < RANGE && curTime < time[nxt1]) {
                time[nxt1] = curTime; // 시간 갱신
                minHeap.add(new int[] { nxt1, time[nxt1] }); // 새로운 위치, 시간 큐에 add
            }
            
            // 2. X + 1
            int nxt2 = curLoc + 1; // 다음 위치
            if (nxt2 >= 0 && nxt2 < RANGE && curTime + 1 < time[nxt2]) {
                time[nxt2] = curTime + 1; // 시간 갱신
                minHeap.add(new int[] { nxt2, time[nxt2] }); // 새로운 위치, 시간 큐에 add
            }
            
            // 3. X - 1
            int nxt3 = curLoc - 1; // 다음 위치
            if (nxt3 >= 0 && nxt3 < RANGE && curTime + 1 < time[nxt3]) {
                time[nxt3] = curTime + 1; // 시간 갱신
                minHeap.add(new int[] { nxt3, time[nxt3] }); // 새로운 위치, 시간 큐에 add
            }
        }
        
        return time[K]; // 가장 빠른 시간 반환
    } // findYounger 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        int K = Integer.parseInt(st.nextToken()); // 동생의 위치
        
        // 버퍼 닫기
        br.close();
        
        int fastTime = findYounger(N, K); // 동생을 찾는 가장 빠른 시간
        
        // 결과값 출력하기
        System.out.print(fastTime);
    } // main 종료
} // Main 종료