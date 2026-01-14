/*
 * 백준 23758번 : 중앙값 제거
 * https://www.acmicpc.net/problem/23758
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-01-14
 * 간단 설명 : 중앙값을 몇 번 2로 나눠야 0이 되는지 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : 측정 불가능
 * 이유 : 중앙값을 나눌 때마다 정렬해야 함그래야 중앙값을 찾을 수 있음
 *
 * 알고리즘 : 그리디
 * 자료구조 : Array
 * 시간 복잡도 : O (N log N)
 * 풀이 방법 : (N + 1) / 2의 위치에 있는 숫자를 2로 나눔
 * 여기서 나눈 수는 작아지기 때문에 정렬 시에 (N + 1) / 2보다 뒤의 위치로 이동이 불가능함
 * 여기서 (N + 1) / 2개만 나눠주면 된다는 사실을 알 수 있음
 * 0이 처음 나오는 경우는 중앙값이 1이 됐을 때임
 * 중앙값이 1이라는 얘기는 그 앞에 모든 수도 1이라는 얘기임
 * 즉 (N + 1) / 2개에 대해서 정렬을 하고 1이 될 때까지 다 2로 나눠준 횟수를 구함
 * 그리고 1에서 2로 나누는 횟수 1을 추가하면 정답임
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ23758 {
    static int divideTwo(int num) { // 숫자가 1이 될 때까지 2로 나누는 횟수를 구하는 함수
        int cnt = 0; // 2로 나눈 횟수
        while (num > 1) { // 1이 될 때까지 2로 나누기
            num /= 2; // 2로 나누기
            cnt++; // 나눈 횟수 증가
        }
        
        return cnt; // 2로 나눈 횟수 반환
    } // divideTwo 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 수의 개수
        int[] arr = new int[N]; // 수의 배열
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken()); // 수 저장
        
        // 버퍼 닫기
        br.close();
        
        int target = (N + 1) / 2; // 중앙값까지의 범위
        int opCnt = 0; // 연산 횟수
        Arrays.sort(arr); // 배열 오름차순 정렬
        for (int i = 0; i < target; i++) {
            opCnt += divideTwo(arr[i]); // 2로 나눌 수 있는 횟수 구하기
        }
        
        // 결과값 출력하기
        System.out.print(opCnt + 1);
    } // main 종료
} // Main 종료