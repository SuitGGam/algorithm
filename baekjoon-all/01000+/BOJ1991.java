/*
 * 백준 1991 : 트리 순회
 * https://www.acmicpc.net/problem/1991
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-07-16
 * 간단 설명 : 트리의 전위 순회, 중위 순회, 후위 순회 방법을 익히는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1991 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    // 트리 노드
    static class Node {
        char value; // 현재 노드
        Node left;  // 왼쪽 자식 노드
        Node right; // 오른쪽 자식 노드
        
        Node(char value) {
            this.value = value;
        }
    } // Node 종료
    
    // 결과값을 출력하기 위한 객체 생성
    static StringBuilder sb = new StringBuilder();
    
    // 전위 순회 : 부모 노드 -> 왼쪽 노드 -> 오른쪽 노드
    static void preorder(Node node) {
        if (node == null) return; // 종료 조건
        sb.append(node.value); // 노드 방문
        preorder(node.left);   // 왼쪽 노드 방문
        preorder(node.right);  // 오른족 노드 방문
    }
    
    // 중위 순회 : 왼쪽 노드 -> 부모 노드 -> 오른쪽 노드
    static void inorder(Node node) {
        if (node == null) return; // 종료 조건
        inorder(node.left);    // 왼쪽 노드 방문
        sb.append(node.value); // 노드 방문
        inorder(node.right);   // 오른족 노드 방문
    }
    
    // 후위 순회 : 왼쪽 노드 -> 오른쪽 노드 -> 부모 노드
    static void postorder(Node node) {
        if (node == null) return; // 종료 조건
        postorder(node.left);  // 왼쪽 노드 방문
        postorder(node.right); // 오른족 노드 방문
        sb.append(node.value); // 노드 방문
    }
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 노드의 개수
        Node[] tree = new Node[N]; // 트리 생성
        for (int i = 0; i < N; i++) { // 트리 만들기
            st = new StringTokenizer(br.readLine(), " ");
            char curr = st.nextToken().charAt(0);  // 현재 노드
            char left = st.nextToken().charAt(0);  // 왼쪽 자식 노드
            char right = st.nextToken().charAt(0); // 오른쪽 자식 노드
            
            if (tree[curr - 'A'] == null) {
                tree[curr - 'A'] = new Node(curr); // 현재 노드 생성
            }
            if (left != '.') {
                tree[left - 'A'] = new Node(left); // 왼쪽 자식 노드 생성
                tree[curr - 'A'].left = tree[left - 'A']; // 왼쪽 자식 값 넣기
            }
            if (right != '.') {
                tree[right - 'A'] = new Node(right); // 오른쪽 자식 노드 생성
                tree[curr - 'A'].right = tree[right - 'A']; // 오른쪽 자식 값 넣기
            }
        } // 트리 만들기 종료
        
        // 버퍼 닫기
        br.close();
        
        preorder(tree[0]);  // 전위 순회
        sb.append("\n"); // 개행 처리
        inorder(tree[0]);   // 중위 순회
        sb.append("\n"); // 개행 처리
        postorder(tree[0]); // 후위 순회
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료