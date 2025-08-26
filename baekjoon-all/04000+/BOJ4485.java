/*
 * 백준 4485번 : 녹색 옷 입은 애가 젤다지?
 * https://www.acmicpc.net/problem/4485
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-08-26
 * 간단 설명 : 시작점부터 끝지점까지의 최소로 잃는 루피양을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class BOJ4485 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N; // 동굴의 크기
    static int[][] cave; // 동굴 정보를 담을 배열
    
    static int[][] blackRupee; // 최소 도둑루피를 저장할 배열
    static void blackRupeeReset() { // 도둑루피값을 초기화할 함수
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                blackRupee[x][y] = Integer.MAX_VALUE; // 최댓값으로 초기화
            }
        }
    } // blackRupeeReset 종료
    
    static class Node { // x, y 좌표와 도둑루피를 저장할 클래스
        int xNode, yNode, cost;
        
        Node (int xNode, int yNode, int cost) {
            this.xNode = xNode; // x좌표
            this.yNode = yNode; // y좌표
            this.cost  = cost;  // 도둑루피
        }
    } // Node 종료
    
    static int[] dx = {-1, 0, 1,  0}; // 상우하좌
    static int[] dy = { 0, 1, 0, -1}; // 상우하좌
    static int dijkstra() { // 최소 도둑루피를 구하는 함수
        PriorityQueue<Node> minHeap = new PriorityQueue<>((o1, o2) ->
                Integer.compare(o1.cost, o2.cost)); // 최소 도둑루피를 위한 PQ
        
        blackRupee[0][0] = cave[0][0]; // 시작점 도둑루피 설정
        minHeap.add(new Node(0, 0, cave[0][0]));
        while (!minHeap.isEmpty()) {
            Node curNode = minHeap.poll(); // 현재 노드
            
            // 현재 노드의 비용이 blackRupee 배열보다 값이 크다면 continue
            if (blackRupee[curNode.xNode][curNode.yNode] < curNode.cost) continue;
            
            // 상우하좌 탐색
            for (int d = 0; d < 4; d++) {
                int nx = curNode.xNode + dx[d];
                int ny = curNode.yNode + dy[d];
                
                // 동굴을 벗어나면 continue
                if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) continue;
                
                // 값이 더 작으면 갱신하기
                if (blackRupee[nx][ny] > curNode.cost + cave[nx][ny]) {
                    blackRupee[nx][ny] = curNode.cost + cave[nx][ny];
                    
                    // 갱신되면 PQ에 넣기
                    minHeap.add(new Node(nx, ny, blackRupee[nx][ny]));
                }
            }
        }
        
        // 결과값 return
        return blackRupee[N - 1][N - 1];
    } // dijkstra 종료
    
    public static void main(String[] args) throws IOException {
        int problemNumber = 1;
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        while (true) {
            N = Integer.parseInt(br.readLine()); // 동굴의 크기
            
            // 종료 조건
            if (N == 0) break;
            
            cave = new int[N][N]; // 동굴 배열의 크기 지정
            
            for (int x = 0; x < N; x++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int y = 0; y < N; y++) {
                    cave[x][y] = Integer.parseInt(st.nextToken()); // 도둑루피 크기 저장
                }
            }
            
            blackRupee = new int[N][N]; // 최소 도둑루피 배열 크기 지정
            blackRupeeReset(); // 최소 도둑루피 배열 초기화
            
            // 결과값 추가하기
            sb.append("Problem ").append(problemNumber).append(": ").append(dijkstra()).append("\n");
            
            problemNumber++; // 문제 번호 증가
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료