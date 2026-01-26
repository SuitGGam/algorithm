/*
 * 백준 23829번 : 인문예술탐사주간
 * https://www.acmicpc.net/problem/23829
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-01-27
 * 간단 설명 : 각 사진에 대해 점수를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N * Q (100,000 x 100,000)
 * 이유 : 각 사진에 대해서 모든 나무와 거리를 비교해야 함
 *
 * 알고리즘 : 누적 합, 이분 탐색
 * 자료구조 : Array
 * 시간 복잡도 : O (Q * log N)
 * 풀이 방법 : 각 나무의 위치는 고정돼있음
 * 사진을 찍는 위치에 따라서 사진의 점수가 달라짐
 * 점수를 구하는 방식을 보면
 * (나무1 - 사진) + (나무2 - 사진) + ••• (나무N - 사진)의 식이 나옴
 * 식을 바꿔보면
 * 나무 위치의 총합 - (나무 개수 * 사진)이 나옴
 * 이분 탐색으로 사진을 찍은 위치를 구해서 좌, 우의 점수 합을 구하면 됨
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ23829 {
    static int N; // 나무의 개수
    static long[] tree; // 나무 위치 배열
    static long[] prefixSum; // 나무 위치 누적 합 배열
    
    static long score(int photo) { // 사진의 점수를 구하는 함수
        int s = 1; // 시작점
        int e = N; // 끝점
        int place = 0; // 사진을 찍은 곳이 위치한 인덱스
        
        while (s <= e) {
            int mid = (s + e) / 2; // 중간점
            if (tree[mid] <= photo) {
                place = mid; // 위치 갱신
                s = mid + 1; // 시작점 갱신
            } else e = mid - 1; // 끝점 갱신
        }
        
        long leftCnt = place; // 왼쪽에 있는 나무 개수
        long leftScore = (leftCnt * photo) - prefixSum[place]; // 왼쪽 나무에 대한 사진 점수
        
        long rightCnt = N - place; // 오른쪽에 있는 나무 개수
        long rightScore = prefixSum[N] - prefixSum[place] - (rightCnt * photo); // 오른쪽 나무에 대한 사진 점수
        
        return leftScore + rightScore; // 사진 점수 반환
    } // score 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 나무의 개수
        int Q = Integer.parseInt(st.nextToken()); // 사진의 개수
        
        tree = new long[N + 1]; // 나무 위치 배열
        prefixSum = new long[N + 1]; // 나무 위치 누적 합 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) tree[i] = Long.parseLong(st.nextToken()); // 나무 위치 저장
        Arrays.sort(tree); // 비내림차순 정렬
        for (int i = 1; i <= N; i++) prefixSum[i] = tree[i] + prefixSum[i - 1]; // 누적 합 만들기
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < Q; i++) {
            int photo = Integer.parseInt(br.readLine()); // 사진을 찍은 위치
            sb.append(score(photo)).append("\n"); // 결과값 추가하기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료