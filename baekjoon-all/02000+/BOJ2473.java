/*
 * 백준 2473번 : 세 용액
 * https://www.acmicpc.net/problem/2473
 * 난이도 : 골드 3
 * 풀이 날짜 : 2025-12-18
 * 간단 설명 : 세 용액을 골랐을 때 합이 가장 0에 가까운 용액을 고르는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ2473 {
    static int[] mix(int N, int[] solution) { // 용액을 섞는 함수
        int[] ans = new int[3]; // 정답 배열
        long sum = 3000000001L; // 세 용액의 합
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1; // 왼쪽 포인터
            int right = N - 1; // 오른쪽 포인터
            while (left < right) { // 투 포인터
                long tmpSum = (long) solution[i] + solution[left] + solution[right]; // 고른 세 가지 용액 합의 절댓값
                if (sum >= Math.abs(tmpSum)) { // 세 용액 갱신
                    sum = Math.abs(tmpSum);
                    ans[0] = solution[i];
                    ans[1] = solution[left];
                    ans[2] = solution[right];
                }
                
                if (tmpSum < 0) left++; // 왼쪽 포인터 옮기기
                else if (tmpSum > 0) right--; // 오른쪽 포인터 옮기기
                else return ans; // 0이면 최적이니 return
            }
        }
        
        return ans;
    } // mix 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 용액의 수
        st = new StringTokenizer(br.readLine(), " ");
        int[] solution = new int[N]; // 용액 배열
        for (int i = 0; i < N; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(solution);
        
        // 버퍼 닫기
        br.close();
        
        int[] ans = mix(N, solution);
        Arrays.sort(ans);
        
        // 결과값 출력하기
        System.out.print(ans[0] + " " + ans[1] + " " + ans[2]);
    } // main 종료
} // Main 종료