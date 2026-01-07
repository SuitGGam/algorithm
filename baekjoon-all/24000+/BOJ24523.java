/*
 * 백준 24523번 : 내 뒤에 나와 다른 수
 * https://www.acmicpc.net/problem/24523
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-01-08
 * 간단 설명 : 현재의 값과 다른 값이 나온 인덱스 중 가장 작은 값을 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N * (N + 1) / 2 (1,000,000 x 1,000,001 / 2)
 * 이유 : 자신의 뒤만 완전 탐색을 하니 첫 인덱스부터 탐색하는 수는
 * N - 1, N - 2, N - 3, •••, 1, 0 개를 탐색해야 함
 *
 * 알고리즘 : 투 포인터(?)
 * 자료구조 : Array
 * 시간 복잡도 : O (N)
 * 풀이 방법 : 매번 완전 탐색을 하려면 O (N^2)이므로 시간 초과임
 * 현재 인덱스가 i라면 i + 1 부터 N까지 보면 되는데 다른 게 있다면 바로 멈출 것이고
 * 없다면 다른 게 나오기 전까지 모든 값이 같은 값이라는 얘기임
 * 즉, N만큼만 동작하면 같은 것과 다른 것을 모두 구분할 수 있음
 * 같은 것들이 나온 곳은 전부 다른 것이 나왔을 때와 같은 걸로 처리하면 됨
 *
 * 알고리즘을 투 포인터라고 쓴 이유는 크게 분류는 없는 것 같지만
 * 현재의 위치를 가르키는 포인터 한 개와 다른 것이 나올 때까지 움직이는 포인터로
 * 총 2개가 있어서 투 포인터라고 기재했음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ24523 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 수열의 길이
        int[] arr = new int[N + 1]; // 수열 배열
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken()); // 수열 저장
        
        // 버퍼 닫기
        br.close();
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 1; i <= N; i++) {
            int same = 0; // 뒤에 오는 숫자 중에 현재 숫자와 같은 수열의 개수
            int idxJ = -1;
            for (int j = i + 1; j <= N; j++) {
                if (arr[i] == arr[j]) same++; // 같으면 same 개수 증가
                else { idxJ = j; break; } // 다르면 J 기록하고 멈추기
            }
            
            for (int j = 0; j < same + 1; j++) { // 같은 것의 개수 + 1개만큼
                sb.append(idxJ).append(" "); // 결과값 추가하기
            }
            
            i += same; // 같은 개수만큼 인덱스 증가
        }
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료