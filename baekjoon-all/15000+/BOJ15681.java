/*
 * 백준 15681번 : 트리와 쿼리
 * https://www.acmicpc.net/problem/15681
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-07-20
 * 간단 설명 : 특정 노드를 루트 노드로 한 서브트리에 대한 정점의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Stack;
import java.util.ArrayList;

public class BOJ15681 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N, R, Q; // 정점의 수, 루트의 번호, 쿼리의 수
    
    static ArrayList<Integer>[] tree; // 트리
    static int[] subTreeSize; // 서브트리 노드의 수를 담을 배열
    
    // 서브트리 개수 구하는 dfs
    static void dfs(int root) {
        // 각 노드에 1을 넣은 상태로 세팅
        // 리프 노드부터 루트 노드까지 올라오면서
        // 자식 노드의 값을 부모 노드에 더해줌
        // 탐색 방법은 후위 순회
        Stack<Integer> stack = new Stack<>(); // dfs용 스택
        Stack<Integer> postOrder = new Stack<>(); // 후위 순회 순서용 스택
        
        stack.push(root); // 루트 노드 push
        int[] par = new int[N + 1]; // 부모 노드가 누구인지 담을 배열
        boolean[] visited = new boolean[N + 1]; // 방문 배열
        
        // dfs로 전위 순회하면서
        // 후위 순회 순서 구하기
        while (!stack.isEmpty()) {
            int cur = stack.pop(); // 현재 노드
            postOrder.push(cur);   // 후위 순회 순서 추가
            visited[cur] = true;   // 현재 노드 방문 처리
            
            for (int nxt : tree[cur]) {
                if (!visited[nxt]) {
                    stack.push(nxt); // 자식 노드 push
                    par[nxt] = cur;  // 부모 노드 지정
                }
            }
        }
        
        // 후위 순회를 하면서
        // 자식 노드의 합을 부모 노드에 더함
        while (!postOrder.isEmpty()) {
            int cur = postOrder.pop(); // 현재 노드
            
            for (int nxt : tree[cur]) {
                if (nxt != par[cur]) {
                    subTreeSize[cur] += subTreeSize[nxt];
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 정점의 수
        R = Integer.parseInt(st.nextToken()); // 루트의 번호
        Q = Integer.parseInt(st.nextToken()); // 쿼리의 수
        
        // 정점 생성
        tree = new ArrayList[N + 1]; // 트리 크기 지정
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        
        // 정점 연결
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int U = Integer.parseInt(st.nextToken()); // 정점 U
            int V = Integer.parseInt(st.nextToken()); // 정점 V
            
            tree[U].add(V); // 정점 연결 1
            tree[V].add(U); // 정점 연결 2
        }
        
        // 서브트리 개수 구하기
        subTreeSize = new int[N + 1]; // 서브트리 노드의 수 배열 크기 지정
        Arrays.fill(subTreeSize, 1); // 각 노드에 노드의 수 1 넣어주기
        dfs(R);
        
        // 쿼리 실행
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine()); // 기준 정점
            sb.append(subTreeSize[u]).append("\n");
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료