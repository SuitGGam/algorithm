/*
 * 백준 5639번 : 이진 검색 트리
 * https://www.acmicpc.net/problem/5639
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-07-19
 * 간단 설명 : 전위 순회로 입력받은 값을 후위 순회로 출력하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5639 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    // 노드
    static class Node {
        int value; // 현재 노드
        Node left;  // 왼쪽 자식 노드
        Node right; // 오른쪽 자식 노드
        
        // 노드 만들기
        Node(int value) {
            this.value = value;
            this.left  = null;
            this.right = null;
        }
        
        // 자식 노드 만들기
        void makeNode(int curValue) {
            // 왼쪽 자식 노드 만들기
            if (curValue < this.value) {
                if (this.left == null) this.left = new Node(curValue);
                else this.left.makeNode(curValue);
            }
            // 오른쪽 자식 노드 만들기
            else {
                if (this.right == null) this.right = new Node(curValue);
                else this.right.makeNode(curValue);
            }
        }
    } // Node 종료
    
    // 결과값을 출력하기 위한 객체 생성
    static StringBuilder sb = new StringBuilder();
    
    // 후위 순회 : 왼쪽 노드 -> 오른쪽 노드 -> 루트 노드
    static void postorder(Node node) {
        if (node == null) return; // 종료 조건
        postorder(node.left);  // 왼쪽 노드 방문
        postorder(node.right); // 오른족 노드 방문
        sb.append(node.value).append("\n"); // 노드 방문
    } // postorder 종료
    
    public static void main(String[] args) throws IOException {
        // 전위 순회 : 루트 노드 -> 왼쪽 노드 -> 오른쪽 노드
        // 첫 번째 입력은 루트 노드
        String node = br.readLine(); // 입력을 위한 변수
        Node tree = new Node(Integer.parseInt(node)); // 루트 노드 만들기
        while ((node = br.readLine()) != null) { // 입력이 없을 때까지 입력받기
            int curValue = Integer.parseInt(node); // 현재 노드 값
            
            tree.makeNode(curValue); // 노드 만들기
        }
        
        // 버퍼 닫기
        br.close();
        
        postorder(tree); // 후위 순회
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료