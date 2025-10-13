/*
 * 백준 1697번 : 숨바꼭질
 * https://www.acmicpc.net/problem/1697
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-10-14
 * 간단 설명 : 수빈이가 동생을 찾을 수 있는 가장 빠른 시간을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ1697 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int K, fastestTime; // 동생의 위치, 가장 빠른 시간
    
    static class Node { // 위치 class
        int N; // 위치
        int time; // 걸린 시간
        
        Node(int N, int time) {
            this.N = N;
            this.time = time;
        }
    } // Location 종료
    
    static void bfs(int start) { // 최단 시간을 찾는 함수
        boolean[] visited = new boolean[100001]; // 방문 처리 배열
        ArrayDeque<Node> queue = new ArrayDeque<>(); // Node을 담을 큐
        queue.add(new Node(start, 0));
        
        while (!queue.isEmpty()) {
            Node curNode = queue.poll(); // 현재 노드
            int pos = curNode.N; // 현재 위치
            
            if (pos == K) { // 동생을 찾은 경우
                fastestTime = curNode.time; // 시간 갱신
                return;
            }
            
            if (!visited[pos]) { // 방문하지 않은 곳인 경우
                visited[pos] = true; // 방문 처리
                
                int[] move = {pos + 1, pos - 1, pos * 2}; // 이동 배열
                for (int i = 0; i < move.length; i++) {
                    int newPos = move[i]; // 새로운 위치
                    
                    if (newPos < 0 || newPos > 100000) continue; // 범위를 벗어나면 continue
                    
                    if (!visited[newPos]) { // 방문하지 않은 곳인 경우
                        queue.add(new Node(newPos, curNode.time + 1)); // 새로운 좌표 큐에 add
                    }
                }
            }
        }
    } // bfs 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        K = Integer.parseInt(st.nextToken()); // 동생의 위치
        
        // 버퍼 닫기
        br.close();
        
        fastestTime = Integer.MAX_VALUE; // 가장 빠른 시간 초기화
        bfs(N); // 최단 시간 찾기
        
        // 결과값 출력하기
        System.out.print(fastestTime);
    } // main 종료
} // Main 종료