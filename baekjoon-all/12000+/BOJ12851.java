/*
 * 백준 12851번 : 숨바꼭질 2
 * https://www.acmicpc.net/problem/12851
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-12-10
 * 간단 설명 : 수빈이가 동생을 찾는 가장 빠른 시간과 방법의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ12851 {
    static final int RANGE = 100_000 + 1; // 위치의 범위
    static int fastTime, fastWay; // 가장 빠른 시간, 방법의 수
    static void bfs(int N, int K) { // 수빈이가 동생을 찾는 함수
        int[] time = new int[RANGE]; // 시간 배열
        int[] cnt = new int[RANGE]; // 방법의 수 배열
        
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // 위치를 담을 큐
        queue.add(N); // 시작점 add
        cnt[N] = 1; // 방법의 수 초기화
        
        while (!queue.isEmpty()) {
            int cur = queue.poll(); // 현재 위치
            
            int[] nextLoc = { cur - 1, cur + 1, cur * 2}; // 다음 위치
            for (int nxt : nextLoc) {
                if (nxt < 0 || nxt >= RANGE) continue; // 범위를 벗어나면 continue
                
                int nxtTime = time[cur] + 1; // 다음 위치까지 걸리는 시간
                
                if (time[nxt] == 0) { // 처음 방문하는 경우
                    time[nxt] = nxtTime; // 시간 갱신
                    cnt[nxt] = cnt[cur]; // 방법의 수 갱신
                    queue.add(nxt); // 다음 지점 add
                } else if (time[nxt] == nxtTime) { // 같은 시간으로 방문하는 경우
                    cnt[nxt] += cnt[cur]; // 방법의 수 누적 갱신
                }
            }
        }
        
        fastTime = time[K]; // 가장 빠른 시간 갱신
        fastWay = cnt[K]; // 방법의 수 갱신
    } // bfs 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        int K = Integer.parseInt(st.nextToken()); // 동생의 위치
        
        // 버퍼 닫기
        br.close();
        
        if (N >= K) { // 수빈이가 동생보다 앞서있는 경우
            System.out.print((N - K) + "\n" + 1);
            return;
        }
        
        fastTime = Integer.MAX_VALUE; // 가장 빠른 시간 초기화
        fastWay = 0; // 방법의 수 초기화
        bfs(N, K);
        
        // 결과값 출력하기
        System.out.print(fastTime + "\n" + fastWay);
    } // main 종료
} // Main 종료