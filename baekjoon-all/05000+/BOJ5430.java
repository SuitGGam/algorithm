/*
 * 백준 5430번 : AC
 * https://www.acmicpc.net/problem/5430
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-11-24
 * 간단 설명 : 배열에 함수를 적용했을 때 최종 결과를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ5430 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int tc = 0; tc < T; tc++) {
            String func = br.readLine(); // 수행할 함수
            int n = Integer.parseInt(br.readLine()); // 수의 개수
            String arr = br.readLine(); // 배열
            arr = arr.substring(1, arr.length() - 1); // [, ] 없애기
            
            ArrayDeque<Integer> deque = new ArrayDeque<>(); // 수를 넣을 덱
            st = new StringTokenizer(arr, ",");
            for (int i = 0; i < n; i++) deque.add(Integer.parseInt(st.nextToken())); // 배열 덱에 add
            
            boolean forward = true; // 방향 변수 : true - 정방향, false - 역방향
            boolean error = false; // 에러 확인 변수
            for (int i = 0; i < func.length(); i++) {
                if (func.charAt(i) == 'R') { // 뒤집기 함수인 경우
                    forward = !forward; // 방향 바꾸기
                } else { // 버리기 함수인 경우
                    if (deque.isEmpty()) { error = true; break; } // error 발생
                    else if (forward) deque.pollFirst(); // 정방향인 경우 앞에서 제거
                    else deque.pollLast(); // 역방향인 경우 뒤에서 제거
                }
            }
            
            // 결과값 추가하기
            if (error) sb.append("error\n"); // 에러인 경우
            else if (deque.isEmpty()) sb.append("[]\n"); // 덱이 비어있는 경우
            else if (forward) { // 정방향인 경우
                sb.append("[");
                while (deque.size() > 1) sb.append(deque.pollFirst()).append(",");
                sb.append(deque.pollFirst()).append("]\n");
            } else { // 역방향인 경우
                sb.append("[");
                while (deque.size() > 1) sb.append(deque.pollLast()).append(",");
                sb.append(deque.pollLast()).append("]\n");
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료