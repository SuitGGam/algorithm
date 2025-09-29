/*
 * 백준 9375번 : 패션왕 신해빈
 * https://www.acmicpc.net/problem/9375
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-09-30
 * 간단 설명 : 해빈이가 알몸이 아닌 상태로 의상을 입을 수 있는 경우의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class BOJ9375 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        int tc = Integer.parseInt(br.readLine()); // 테스트 케이스의 수
        for (int i = 0; i < tc; i++) {
            // Key : 의상 종류, value : 각 의상의 수
            HashMap<String, Integer> hashMap = new HashMap<>();
            
            int clothes = Integer.parseInt(br.readLine()); // 의상의 수
            for (int j = 0; j < clothes; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                st.nextToken(); // 의상의 이름
                String type = st.nextToken(); // 의상의 유형
                
                if (hashMap.get(type) == null) hashMap.put(type, 1); // 의상의 유형이 없는 경우 추가
                else hashMap.put(type, hashMap.get(type) + 1); // 의상의 유형이 있는 경우 개수 증가
            }
            
            // 의상의 종류가 1개인 경우
            if (hashMap.size() == 1) sb.append((hashMap.values()).toArray(new Integer[0])[0]).append("\n");
                // 의상의 종류가 여러 개인 경우
            else {
                Integer[] arr = hashMap.values().toArray(new Integer[0]); // 각 의상 유형의 개수 배열
                Integer cases = 1; // 경우의 수
                for (int j = 0; j < arr.length; j++) {
                    cases *= arr[j] + 1;
                }
                
                sb.append(cases - 1).append("\n"); // 결과값 추가하기
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료