/*
 * 백준 24061번 : 알고리즘 수업 - 병합 정렬 2
 * https://www.acmicpc.net/problem/24061
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-09-17
 * 간단 설명 : Top-Down 병합 정렬 중에 배열 A가 K번 정렬됐을 때 배열을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ24061 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int K; // 저장 횟수
    
    static int[] sorted; // 합치는 과정에서 원소를 담을 임시 배열
    static void merge_sort(int[] arr) {
        sorted = new int[arr.length]; // 배열 크기 지정
        merge_sort(arr, 0, arr.length - 1); // merge_sort 호출
        sorted = null; // 임시 배열 초기화
    } // merge_sort 종료
    
    static void merge_sort(int[] arr, int left, int right) { // Top-Down 방식
        // 종료 조건 - 부분 배열이 1개인 경우
        if (left == right) return;
        
        int mid = (left + right) / 2; // 절반 위치
        
        merge_sort(arr, left, mid); // 절반의 왼쪽 부분
        merge_sort(arr, mid + 1, right); // 절반의 오른쪽 부분
        merge(arr, left, mid, right); // 병합
    } // merge_sort 종료
    
    static int storeCnt; // 저장된 횟수
    static StringBuilder sb = new StringBuilder();
    static void merge(int[] arr, int left, int mid, int right) { // 병합하는 함수
        int l = left; // 절반의 왼쪽 부분 시작점
        int r = mid + 1; // 절반의 오른쪽 부분 시작점
        int idx = left; // 채워넣을 배열의 인덱스
        
        while (l <= mid && r <= right) {
            // 왼쪽 부분의 첫 번째 원소가 오른쪽 부분 첫 번째 원소보다 작거나 같은 경우
            if (arr[l] <= arr[r]) {
                sorted[idx] = arr[l];
                idx++;
                l++;
            } else { // 오른쪽 부분의 첫 번째 원소가 왼쪽 부분 첫 번째 원소보다 작거나 같은 경우
                sorted[idx] = arr[r];
                idx++;
                r++;
            }
        }
        
        // 왼쪽 부분에 남은 원소가 없는 경우 오른쪽 부분으로 채우기
        if (l > mid) {
            while (r <= right) {
                sorted[idx] = arr[r];
                idx++;
                r++;
            }
        } else { // 오른쪽 부분에 남은 원소가 없는 경우 왼쪽 부분으로 채우기
            while (l <= mid) {
                sorted[idx] = arr[l];
                idx++;
                l++;
            }
        }
        
        // 정렬된 배열 옮겨주기
        for (int i = left; i <= right; i++) {
            arr[i] = sorted[i];
            storeCnt++; // 저장 횟수 증가
            if (storeCnt == K) { // 결과값 저장하기
                for (int j = 0; j < arr.length; j++) {
                    sb.append(arr[j]).append(" ");
                }
            }
        }
    } // merge 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 배열의 크기
        K = Integer.parseInt(st.nextToken()); // 저장 횟수
        
        int[] arr = new int[N]; // 원소를 담을 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken()); // 원소 저장
        
        // 버퍼 닫기
        br.close();
        
        storeCnt = 0; // 저장 횟수 초기화
        merge_sort(arr);
        
        // 결과값 출력하기
        if (storeCnt < K) System.out.print(-1);
        else System.out.print(sb);
    } // main 종료
} // Main 종료