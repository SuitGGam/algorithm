/*
 * 백준 11725 : 트리의 부모 찾기
 * https://www.acmicpc.net/problem/11725
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-07-17
 * 간단 설명 : 트리의 부모 노드를 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ11725 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N; // 노드의 개수
    static ArrayList<Integer>[] tree; // 트리
    
    // 트리를 순회하는 DFS 함수
    static int[] parent; // 부모 노드 번호를 저장할 배열
    static void dfs(int cur, int par) {
        for (int nxt : tree[cur]) {
            if (nxt != par) { // 부모로 이동하지 않기
                parent[nxt] = cur; // 자식의 부모는 현재 노드 값
                dfs(nxt, cur); // 자식으로 이동
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine()); // 노드의 개수
        tree = new ArrayList[N + 1]; // 트리 생성
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < N - 1; i++) { // 트리 만들기
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken()); // 정점 u
            int v = Integer.parseInt(st.nextToken()); // 정점 v
            
            // 정점 연결
            tree[u].add(v);
            tree[v].add(u);
        } // 트리 만들기 종료
        
        // 버퍼 닫기
        br.close();
        
        parent = new int[N + 1]; // 부모 노드 번호를 저장할 배열 크기 지정
        dfs(1, 0); // 트리 순회
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장하기 위한 객체
        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append("\n");
        }
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료