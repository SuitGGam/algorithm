/*
 * 백준 24062번 : 알고리즘 수업 - 병합 정렬 3
 * https://www.acmicpc.net/problem/24062
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-09-18
 * 간단 설명 : Top-Down 병합 정렬 중에 배열 A가 배열 B와 같은 때가 있는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ24062 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N; // 배열의 크기
    static int lastCheckIdx = -1; // 마지막으로 다른 지점의 인덱스
    static int[] arrA, arrB, sorted; // 원본 배열, 중간에 비교할 배열, 합치는 과정에 사용할 임시 배열
    
    static void merge_sort(int left, int right) { // Top-Down 방식
        // 종료 조건 - 부분 배열이 1개인 경우
        if (left == right) return;
        
        int mid = (left + right) / 2; // 절반 위치
        
        merge_sort(left, mid); // 절반의 왼쪽 부분
        merge_sort(mid + 1, right); // 절반의 오른쪽 부분
        merge(left, mid, right); // 병합
    } // merge_sort 종료
    
    static void merge(int left, int mid, int right) { // 병합하는 함수
        int l = left; // 절반의 왼쪽 부분 시작점
        int r = mid + 1; // 절반의 오른쪽 부분 시작점
        int idx = left; // 채워넣을 배열의 인덱스
        
        while (l <= mid && r <= right) {
            // 왼쪽 부분의 첫 번째 원소가 오른쪽 부분 첫 번째 원소보다 작거나 같은 경우
            if (arrA[l] <= arrA[r]) sorted[idx++] = arrA[l++];
                // 오른쪽 부분의 첫 번째 원소가 왼쪽 부분 첫 번째 원소보다 작거나 같은 경우
            else sorted[idx++] = arrA[r++];
        }
        
        // 왼쪽 부분에 남은 원소가 없는 경우 오른쪽 부분으로 채우기
        if (l > mid) while (r <= right) sorted[idx++] = arrA[r++];
            // 오른쪽 부분에 남은 원소가 없는 경우 왼쪽 부분으로 채우기
        else while (l <= mid) sorted[idx++] = arrA[l++];
        
        // 정렬된 배열 옮겨주기
        for (int i = left; i <= right; i++) {
            arrA[i] = sorted[i]; // 원소 정렬
            if (sameCheck()) print(1); // 결과값 출력
        }
    } // merge 종료
    
    static boolean sameCheck() {
        for (int i = Math.max(0, lastCheckIdx); i < N; i++) {
            if (arrA[i] != arrB[i]) {
                lastCheckIdx = i - 1; // 인덱스 갱신
                return false; // 다르면 false return
            }
        }
        
        return true; // 같으면 true return
    } // sameCheck 종료
    
    static void print(int result) {
        System.out.print(result); // 결과값 출력
        System.exit(0); // 프로그램 종료
    } // print 종료
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine()); // 배열의 크기
        
        arrA = new int[N]; // 배열 크기 지정
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) arrA[i] = Integer.parseInt(st.nextToken()); // 원소 저장
        
        arrB = new int[N]; // 배열 크기 지정
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) arrB[i] = Integer.parseInt(st.nextToken()); // 원소 저장
        
        // 버퍼 닫기
        br.close();
        
        // 초기 상태에 같으면 바로 종료
        if (sameCheck()) print(1);
        
        sorted = new int[N]; // 배열 크기 지정
        merge_sort(0, N - 1); // 병합 정렬 시작
        
        // 결과값 출력하기
        System.out.print(0);
    } // main 종료
} // Main 종료