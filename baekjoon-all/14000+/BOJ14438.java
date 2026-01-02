/*
 * 백준 14438번 : 수열과 쿼리 17
 * https://www.acmicpc.net/problem/14438
 * 난이도 : 골드 1
 * 풀이 날짜 : 2026-01-03
 * 간단 설명 : 주어진 수열 N에 대하여 특정 수를 변경하거나 구간의 최솟값을 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N * M (100,000 x 100,000)
 * 이유 : 구간의 최솟값을 구할 때 범위가 1부터 N까지면 전체를 계속 구해야 함
 *
 * 알고리즘 : 제곱근 분할
 * 자료구조 : Array
 * 시간 복잡도 : O (√N)
 * 풀이 방법 : 전체 구간을 N의 제곱근으로 나눠서 그룹을 나눔
 * 그리고 각 그룹에서 최솟값을 구하고 갱신 될 때마다 그룹의 최솟값을 갱신
 * 그러면 쿼리가 들어왔을 때 해당 그룹의 전체를 탐색하는 게 아니라
 * 그룹 내의 최솟값 1개와 걸쳐있는 곳 일부만 탐색해서 최솟값을 구할 수 있음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14438 {
    static int N, sqrt; // 수열의 크기, 수열 크기의 제곱근
    static int[] A; // 수열 배열
    static int[] rangeMin; // 구간 내의 최솟값 배열
    
    static void update(int idx, int v) { // 숫자를 바꾸는 함수
        // 바뀐 값과 그룹 내의 최솟값만 비교해서 그룹 내의 최솟값을 갱신하면 안 됨
        // 최솟값이 바뀐 그룹은 그룹 전체를 탐색해야 함
        // 예를 들어 그룹 내의 수열이 [1, 5, 10] 이라고 했을 때
        // 1이 20으로 바뀐 경우가 문제가 됨
        
        // 그룹의 끝 지점을 정하는 것도 중요
        // 만약 마지막 그룹이 sqrt만큼 있지 않다면
        // 배열을 벗어남
        A[idx] = v; // 숫자 변경
        int curGroup = idx / sqrt; // 해당 그룹
        rangeMin[curGroup] = Integer.MAX_VALUE; // 인덱스가 포함된 구간 최솟값 초기화
        int end = curGroup == rangeMin.length - 1 ? N - 1 : (curGroup + 1) * sqrt; // 해당 그룹의 끝 지점
        for (int i = curGroup * sqrt; i < end; i++) {
            if (rangeMin[curGroup] > A[i]) rangeMin[curGroup] = A[i]; // 그룹 내의 최솟값 갱신
        }
    } // update 종료
    
    static int query(int start, int end) { // 구간의 최솟값을 구하는 함수
        int min = Integer.MAX_VALUE; // 구간 내의 최솟값 초기화
        int sGroup = start / sqrt; // 시작 구간 그룹
        int eGroup =   end / sqrt; // 종료 구간 그룹
        
        if (sGroup == eGroup) { // 시작 그룹과 종료 그룹이 같은 경우
            for (int i = start; i <= end; i++) if (min > A[i]) min = A[i]; // 최솟값 갱신
        } else { // 시작 그룹과 종료 그룹이 다른 경우
            for (int i = start; i < (sGroup + 1) * sqrt; i++) if (min > A[i]) min = A[i]; // 시작 구간이 있는 그룹
            for (int i = sGroup + 1; i < eGroup; i++) if (min > rangeMin[i]) min = rangeMin[i]; // 중간에 있는 그룹
            for (int i = eGroup * sqrt; i <= end; i++) if (min > A[i]) min = A[i]; // 중료 구간이 있는 그룹
        }
        
        return min; // 최솟값 반환
    } // query 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); //  수열의 크기
        sqrt = (int) Math.sqrt(N); // 수열 크기의 제곱근
        A = new int[N]; // 수열 배열
        rangeMin = new int[N / sqrt + 1]; // 구간 내의 최솟값 배열
        for (int i = 0; i < rangeMin.length; i++) rangeMin[i] = Integer.MAX_VALUE; // 최솟값 배열 초기화
        
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken()); // 수 저장
            if (rangeMin[i / sqrt] > A[i]) rangeMin[i / sqrt] = A[i]; // 그룹 내의 최솟값 갱신
        }
        
        int M = Integer.parseInt(br.readLine()); // 쿼리의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            char op = st.nextToken().charAt(0); // 쿼리의 종류
            int idx = Integer.parseInt(st.nextToken()); // 바꿀 곳의 인덱스, 시작 구간
            int v   = Integer.parseInt(st.nextToken()); // 바꿀 숫자, 종료 구간
            
            if (op == '1') update(idx - 1, v); // 숫자 바꾸기
            else sb.append(query(idx - 1, v - 1)).append("\n"); // 구간의 최솟값 구하기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료