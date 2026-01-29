/*
 * 백준 1916번 : 최소비용 구하기
 * https://www.acmicpc.net/problem/1916
 * 난이도 : 골드 5
 * 풀이 날짜 : 2026-01-29
 * 간단 설명 : 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N! (1000!)
 * 이유 : 한 도시에서 다른 도시로 가는 모든 경우의 수를 구해야 함
 *
 * 알고리즘 : 다익스트라
 * 자료구조 : ArrayList (그래프), PriorityQueue
 * 시간 복잡도 : O ((N + M)logN)
 * 풀이 방법 : 특정 장소에서 시작해서 특정 장소까지의 최소 비용을 구해야 함
 * 모든 비용이 양수고 가장 적은 값이니 다익스트라 사용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class BOJ1916 {
    static class Bus { // 버스 정보를 담은 클래스
        int toCity; // 도착지
        int cost; // 비용
        
        Bus(int toCity, int cost) {
            this.toCity = toCity;
            this.cost = cost;
        }
    } // bus class
    
    static int dijkstra(int N, int startCity, int endCity, ArrayList<Bus>[] city) { // 최소 비용을 구하는 함수
        int[] busCost = new int[N + 1]; // 비용 배열
        Arrays.fill(busCost, Integer.MAX_VALUE); // 비용 배열 초기화
        
        PriorityQueue<Bus> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost)); // 비용을 비교할 PQ
        pq.add(new Bus(startCity, 0)); // 시작 도시 add
        busCost[startCity] = 0; // 시작 도시 비용 초기화
        
        while (!pq.isEmpty()) {
            Bus curBus = pq.poll(); // 현재 버스 정보
            
            if (busCost[curBus.toCity] < curBus.cost) continue; // 기존의 비용이 현재 비용보다 적으면 continue
            
            for (Bus nxt : city[curBus.toCity]) {
                int nxtCity = nxt.toCity; // 다음 행선지
                int nxtCost = nxt.cost; // 다음 행선지로 가기 위한 비용
                
                if (busCost[nxtCity] > curBus.cost + nxtCost) { // 다음 행선지의 도착 비용이 현재 적혀있는 비용보다 적은 경우
                    busCost[nxtCity] = curBus.cost + nxtCost; // 비용 갱신
                    
                    pq.add(new Bus(nxtCity, busCost[nxtCity])); // 다음 행선지 add
                }
            }
        }
        
        return busCost[endCity]; // 최소 비용 반환
    } // dijkstra 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 도시의 개수
        int M = Integer.parseInt(br.readLine()); // 버스의 개수
        
        ArrayList<Bus>[] city = new ArrayList[N + 1]; // 도시 그래프 배열
        for (int i = 1; i <= N; i++) city[i] = new ArrayList<>(); // 도시 생성
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()); // 출발지
            int to = Integer.parseInt(st.nextToken()); // 도착지
            int cost = Integer.parseInt(st.nextToken()); // 비용
            
            city[from].add(new Bus(to, cost)); // 버스 정보 추가
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        int startCity = Integer.parseInt(st.nextToken()); // 출발 도시
        int endCity = Integer.parseInt(st.nextToken()); // 도착 도시
        
        // 버퍼 닫기
        br.close();
        
        int minCost = dijkstra(N, startCity, endCity, city); // 최소 비용
        
        // 결과값 출력하기
        System.out.print(minCost);
    } // main 종료
} // Main 종료