/*
 * 백준 4881번 : 자리수의 제곱
 * https://www.acmicpc.net/problem/4881
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-09-13
 * 간단 설명 : 자리수의 제곱 합을 구해서 두 수열의 길이의 합의 최솟값을 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedHashSet;

public class BOJ4881 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static LinkedHashSet<String> findCycle(String number) {
        LinkedHashSet<String> set = new LinkedHashSet<>(); // 수열을 저장하기 위한 set
        
        set.add(number); // 첫 숫자 추가
        while (true) {
            int sum = 0; // 각 숫자들 제곱의 합
            
            for (int i = 0; i < number.length(); i++) {
                // 각 자리의 숫자 제곱의 합 더하기
                int n = number.charAt(i) - 48; // 각 자리 수
                sum += n * n; // 각 자리의 제곱 더하기
            }
            
            number = String.valueOf(sum); // 다음 수열
            if (set.contains(number)) break; // 종료 조건
            else set.add(number); // 수열에 추가
        }
        
        return set; // Set 반환
    } // findCycle 종료
    
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void checkSameNumber(String[] A, String[] B) {
        boolean valid = false; // 겹치는 숫자 판독 여부
        int minLength = Integer.MAX_VALUE; // 길이 합의 최솟값
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i].equals(B[j])) {
                    valid = true; // valid 유효 처리
                    if (minLength > i + j + 2) minLength = i + j + 2; // 최솟값 갱신
                }
            }
        }
        
        if (valid) sb.append(A[0]).append(" ").append(B[0]).append(" ").append(minLength).append("\n"); // 겹치는 숫자가 있는 경우
        else sb.append(A[0]).append(" ").append(B[0]).append(" ").append(0).append("\n"); // 겹치는 숫자가 없는 경우
    } // checkSameNumber 종료
    
    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            String A = st.nextToken(); // 양의 정수 A
            String B = st.nextToken(); // 양의 정수 B
            
            // 종료 조건
            if (A.equals("0") && B.equals("0")) break;
            
            String[] arrA = findCycle(A).toArray(new String[0]); // 사이클 전까지의 A배열
            String[] arrB = findCycle(B).toArray(new String[0]); // 사이클 전까지의 B배열
            
            checkSameNumber(arrA, arrB); // 같은 숫자 찾기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료