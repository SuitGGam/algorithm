/*
 * 백준 2467번 : 용액
 * https://www.acmicpc.net/problem/2467
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-12-18
 * 간단 설명 : 두 용액을 골랐을 때 합이 가장 0에 가까운 용액을 고르는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ2467 {
    static int[] mix(int N, int[] solution) { // 두 용액을 섞는 함수
        int[] ans = new int[2]; // 용액 배열
        int sum = Integer.MAX_VALUE; // 두 용액의 합
        int start = 0; // 왼쪽 범위
        int end = N - 1; // 오른쪽 범위
        while (start < end) {
            int tmpSum = solution[start] + solution[end]; // 임시 두 용액의 합
            
            if (sum > Math.abs(tmpSum)) { // 두 용액 갱신
                sum = Math.abs(tmpSum);
                ans[0] = solution[start];
                ans[1] = solution[end];
            }
            
            // 포인터 갱신
            if (tmpSum < 0) start++;
            else if (tmpSum > 0) end--;
            else return ans;
        }
        
        return ans;
    } // mix 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 용액의 수
        int[] solution = new int[N]; // 용액 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) solution[i] = Integer.parseInt(st.nextToken()); // 용액 저장
        
        // 버퍼 닫기
        br.close();
        
        Arrays.sort(solution); // 용액 비내림차순 정렬
        int[] ans = mix(N, solution); // 정답 용액 두 가지
        Arrays.sort(ans); // 정답 용액 비내림차순 정렬
        
        // 결과값 출력하기
        System.out.print(ans[0] + " " + ans[1]);
    } // main 종료
} // Main 종료