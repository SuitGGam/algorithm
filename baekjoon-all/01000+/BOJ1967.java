/*
 * 백준 1967번 : 트리의 지름
 * https://www.acmicpc.net/problem/1967
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-10-05
 * 간단 설명 : 임의의 두 정점 사이의 거리 중 가장 긴 거리를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ1967 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static class Node { // Node 함수
        int v; // 정점 번호
        int value; // 가중치
        
        Node(int v, int value) {
            this.v = v;
            this.value = value;
        }
    } // Node 종료
    
    static int maxDistance;  // 트리의 지름
    static int farthestNode; // 제일 먼 노드
    static ArrayList<Node>[] tree; // 트리 배열
    static boolean[] visited; // 방문 처리 배열
    static void dfs(int node, int value) { // dfs 함수
        visited[node] = true; // 방문 처리
        if (maxDistance < value) {
            maxDistance = value; // 트리의 지름 갱신
            farthestNode = node; // 가장 먼 노드 갱신
        }
        
        for (Node n : tree[node]) {
            if (!visited[n.v]) { // 방문하지 않은 정점인 경우
                dfs(n.v, value + n.value);
            }
        }
    } // dfs 종료
    
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine()); // 노드의 개수
        tree = new ArrayList[n + 1]; // 배열 크기 지정
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>(); // 정점 생성
        
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()); // 부모 노드
            int b = Integer.parseInt(st.nextToken()); // 자식 노드
            int value = Integer.parseInt(st.nextToken()); // 가중치
            
            tree[a].add(new Node(b, value)); // 트리 정보 추가
            tree[b].add(new Node(a, value)); // 트리 정보 추가
        }
        
        // 버퍼 닫기
        br.close();
        
        // 가장 먼 노드 찾기
        maxDistance  = 0; // 트리의 지름 초기화
        farthestNode = 0; // 가장 먼 노드 초기화
        visited = new boolean[n + 1]; // 배열 크기 지정
        dfs(1, 0);
        
        // 트리의 지름 구하기
        if (n > 1) { // 노드가 1개가 아닌 경우
            visited = new boolean[n + 1]; // 방문 처리 배열 초기화
            dfs(farthestNode, 0);
        }
        
        // 결과값 출력하기
        if (n == 1) maxDistance = 0;
        System.out.print(maxDistance);
    } // main 종료
} // Main 종료