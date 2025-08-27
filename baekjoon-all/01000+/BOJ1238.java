/*
 * 백준 1238번 : 파티
 * https://www.acmicpc.net/problem/1238
 * 난이도 : 골드 3
 * 풀이 날짜 : 2025-08-27
 * 간단 설명 : 각 마을에서 X 마을에 갔다가 다시 집으로 오는데 가장 많이 걸린 시간을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ1238 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N, X; // 학생의 수, 파티를 여는 마을의 번호
    static ArrayList<Node>[] village; // 마을 도로에 대한 정보를 담을 배열
    static ArrayList<Node>[] reverseVillage; // 마을 도로에 대한 정보를 반대로 담을 배열
    
    static class Node { // 도로 정보를 담을 Node
        int E; // 다른 마을로 가는 도로
        int T; // 소요 시간
        
        Node(int E, int T) {
            this.E = E;
            this.T = T;
        }
    } // Node 종료
    
    static int[] time;       // 총 소요 시간을 담을 배열
    static int[] goXTime;    // X로 가는 소요 시간을 담을 배열
    static int[] goHomeTime; // X에서 집에 오는 소요 시간을 담을 배열
    
    static void goX() { // X로 가는 시간 구하는 함수
        PriorityQueue<Node> minHeap = new PriorityQueue<>((o1, o2) ->
                Integer.compare(o1.T, o2.T)); // 최솟값을 저장할 PQ
        
        goXTime[X] = 0;
        minHeap.add(new Node(X, 0));
        
        while (!minHeap.isEmpty()) {
            Node curNode = minHeap.poll(); // 현재 마을
            
            // 현재 노드의 시간이 기존에 기록된 것보다 크다면 continue
            if (goXTime[curNode.E] < curNode.T) continue;
            
            for (int i = 0; i < reverseVillage[curNode.E].size(); i++) {
                Node nxtNode = reverseVillage[curNode.E].get(i); // 다음 마을
                
                if (goXTime[nxtNode.E] > goXTime[curNode.E] + nxtNode.T) {
                    goXTime[nxtNode.E] = goXTime[curNode.E] + nxtNode.T; // 소요 시간 갱신
                    
                    minHeap.add(new Node(nxtNode.E, goXTime[nxtNode.E])); // 다음 마을 PQ에 삽입
                }
            }
        }
        
        // 시간 합산하기
        for (int i = 1; i <= N; i++) time[i] += goXTime[i];
    } // goX 종료
    
    static void goHome() { // X에서 집으로 오는 시간 구하는 함수
        PriorityQueue<Node> minHeap = new PriorityQueue<>((o1, o2) ->
                Integer.compare(o1.T, o2.T)); // 최솟값을 저장할 PQ
        
        goHomeTime[X] = 0;
        minHeap.add(new Node(X, 0));
        
        while (!minHeap.isEmpty()) {
            Node curNode = minHeap.poll(); // 현재 마을
            
            // 현재 노드의 시간이 기존에 기록된 것보다 크다면 continue
            if (goHomeTime[curNode.E] < curNode.T) continue;
            
            for (int i = 0; i < village[curNode.E].size(); i++) {
                Node nxtNode = village[curNode.E].get(i); // 다음 마을
                
                if (goHomeTime[nxtNode.E] > goHomeTime[curNode.E] + nxtNode.T) {
                    goHomeTime[nxtNode.E] = goHomeTime[curNode.E] + nxtNode.T; // 소요 시간 갱신
                    
                    minHeap.add(new Node(nxtNode.E, goHomeTime[nxtNode.E])); // 다음 마을 PQ에 삽입
                }
            }
        }
        
        // 시간 합산하기
        for (int i = 1; i <= N; i++) time[i] += goHomeTime[i];
    } // goHome 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 학생의 수
        int M = Integer.parseInt(st.nextToken()); // 단방향 도로의 개수
        X = Integer.parseInt(st.nextToken()); // 파티를 여는 마을의 번호
        
        village        = new ArrayList[N + 1]; // 배열 크기 지정
        reverseVillage = new ArrayList[N + 1]; // 배열 크기 지정
        for (int i = 1; i <= N; i++) { // 배열 생성
            village[i]        = new ArrayList<>();
            reverseVillage[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int V = Integer.parseInt(st.nextToken()); // 마을
            int E = Integer.parseInt(st.nextToken()); // 다른 마을로 가는 도로
            int T = Integer.parseInt(st.nextToken()); // 소요 시간
            
            village[V].add(new Node(E, T)); // 도로 정보 추가
            reverseVillage[E].add(new Node(V, T)); // 도로 정보 추가
        }
        
        // 버퍼 닫기
        br.close();
        
        time       = new int[N + 1]; // 배열 크기 지정
        goXTime    = new int[N + 1]; // 배열 크기 지정
        goHomeTime = new int[N + 1]; // 배열 크기 지정
        
        for (int i = 1; i <= N; i++) { // 소요 시간 초기화
            goXTime[i]    = Integer.MAX_VALUE;
            goHomeTime[i] = Integer.MAX_VALUE;
        }
        
        goX();    // X로 출발
        goHome(); // X에서 출발
        
        int maxTime = 0; // 최대 소요 시간
        for (int i = 1; i <= N; i++) {
            if (maxTime < time[i]) maxTime = time[i]; // 최대 소요 시간 찾기
        }
        
        // 결과값 출력하기
        System.out.print(maxTime);
    } // main 종료
} // Main 종료