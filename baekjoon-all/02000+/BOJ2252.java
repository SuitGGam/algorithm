/*
 * 백준 2252번 : 줄 세우기
 * https://www.acmicpc.net/problem/2252
 * 난이도 : 골드 3
 * 풀이 날짜 : 2025-08-24
 * 간단 설명 : 키가 큰 순서대로 줄을 세우는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class BOJ2252 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N; // 학생의 수
    static ArrayList<Integer>[] student; // 자신보다 작은 사람의 정보를 저장할 배열
    static int[] indegree; // 자신이 몇 명보다 작은지 정보를 저장할 배열
    
    static StringBuilder sb = new StringBuilder();
    static void lineUp() {
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // 줄을 세울 순서
        
        for (int i = 1; i <= N;i ++) {
            if (indegree[i] == 0) {
                queue.add(i); // 줄을 세워도 되는 순서면 큐에 추가
                indegree[i] = -1; // 줄을 세운 학생은 정보 제거
            }
        }
        
        while (!queue.isEmpty()) {
            int curOrder = queue.poll(); // 현재 줄을 세울 번호
            sb.append(curOrder).append(" "); // 결과값 추가하기
            
            // 자신보다 작은 학생들을 확인하면서 indegree 감소
            for (int i = 0; i < student[curOrder].size(); i++) {
                int curIndegree = student[curOrder].get(i); // 현재 작은 학생
                
                indegree[curIndegree]--; // indegree 감소
                if (indegree[curIndegree] == 0) queue.add(curIndegree); // indegree가 0이면 queue에 추가
            }
        }
    } // lineUp 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 학생의 수
        int M = Integer.parseInt(st.nextToken()); // 키를 비교한 수
        
        student = new ArrayList[N + 1]; // 자신보다 작은 사람의 정보를 저장할 배열의 크기 지정
        for (int i = 1; i <= N; i++) {
            student[i] = new ArrayList<>();
        }
        
        indegree = new int[N+ 1]; // 몇 명보다 작은지 정보를 저장할 배열의 크기 지정
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken()); // 자신의 번호
            int B = Integer.parseInt(st.nextToken()); // 자신보다 작은 학생의 번호
            
            student[A].add(B); // 상대적 큼 추가
            indegree[B]++; // 상대적 작음 추가
        }
        
        // 버퍼 닫기
        br.close();
        
        lineUp(); // 줄 세우기
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료