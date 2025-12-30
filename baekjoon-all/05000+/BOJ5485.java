/*
 * 백준 5485번 : 평균값 수열
 * https://www.acmicpc.net/problem/5485
 * 난이도 : 골드 2
 * 풀이 날짜 : 2025-12-31
 * 간단 설명 : 평균값으로 만들어진 m수열을 만들 수 있는 비감소 수열의 개수를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : mi의 최대 범위(1,000,000,000)
 * 첫 번째 원소만 정하면 그 뒤는 쭉 정할 수 있지만
 * 첫 번째 원소만 정하는 경우의 수만 최대 20억임
 * 시간 복잡도 : O (N)
 * 이유 : 현재 원소의 뒤 원소만 보면서 범위를 갱신하면 됨
 * 알고리즘 : 수학
 * 자료구조 : Array
 * 풀이 방법 : 첫 번째 원소만 정하면 그 수열은 완성되는 성질을 이용
 * 그럼 현재 원소의 최소, 최대 범위가 어딘지 구해야 함
 * 최소 범위는 이전 원소보다 같거나 커야 하고
 * 최대 범위는 다음 원소보다 작거나 같아야 함
 * 그래서 최소, 최대 범위만 갱신하면서 문제를 풀면 됨
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5485 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        
        // 버퍼 닫기
        br.close();
        
        int minRange = 2 * arr[0] - arr[1]; // 최소 범위
        int maxRange = arr[0]; // 최대 범위
        for (int i = 0; i < N - 1; i++) {
            int nxtMaxRange = arr[i] * 2 - minRange; // 다음 최대 범위
            
            minRange = arr[i] * 2 - maxRange; // 최소 범위 갱신
            maxRange = Math.min(nxtMaxRange, arr[i + 1]); // 최대 범위 갱신
            
            if (minRange > maxRange) break; // 최소 범위가 최대 범위보다 크다면 break
        }
        
        // 결과값 출력하기
        System.out.print(Math.max(maxRange - minRange + 1, 0));
    } // main 종료
} // Main 종료