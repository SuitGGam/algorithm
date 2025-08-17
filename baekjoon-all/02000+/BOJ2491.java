/*
 * 백준 2491번 : 수열
 * https://www.acmicpc.net/problem/2491
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-08-17
 * 간단 설명 : 해당 수열의 부분 수열에서 계속 커지거나 계속 작아지는 구간 중 길이가 가장 긴 것을 찾아내는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2491 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine()); // 수열의 길이
        sequence = new int[N]; // 수열을 담을 배열 크기 지정
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < N; i++) { // 수열 입력 시작
            sequence[i] = Integer.parseInt(st.nextToken());
        } // 수열 입력 종료
        
        // 버퍼 닫기
        br.close();
        
        // 최대 길이 수열 구하기
        maxLength = 1; // 최대 길이 초기화
        increaseMaxLength(); // 증가하는 부분
        decreaseMaxLength(); // 감소하는 부분
        
        // 결과값 출력하기
        System.out.print(maxLength);
    } // main 종료
    
    static int N; // 수열의 길이
    static int maxLength; // 연속되는 수열의 최대 길이
    static int[] sequence; // 수열을 담을 배열
    
    // 증가하는 부분의 최대 길이를 찾는 수열
    static void increaseMaxLength() {
        int tmpMaxLength = 1; // 임시 최대 길이
        for (int i = 1; i < N; i++) {
            // 이전 값보다 크거나 같으면 증가
            if (sequence[i] >= sequence[i - 1]) {
                tmpMaxLength++;
                
                // 최대 길이 갱신
                if (maxLength < tmpMaxLength) maxLength = tmpMaxLength;
            }
            // 특정 구간에 포함돼있는 길이들은
            // 특정 구간 최대 길이보다 다 짧기 때문에
            // 모두 확인할 필요가 없음
            else tmpMaxLength = 1; // 임시 길이 값 초기화
        }
    }
    
    // 감소하는 부분의 최대 길이를 찾는 수열
    static void decreaseMaxLength() {
        int tmpMaxLength = 1; // 임시 최대 길이
        for (int i = 1; i < N; i++) {
            // 이전 값보다 작거나 같으면 증가
            if (sequence[i] <= sequence[i - 1]) {
                tmpMaxLength++;
                
                // 최대 길이 갱신
                if (maxLength < tmpMaxLength) maxLength = tmpMaxLength;
            }
            // 특정 구간에 포함돼있는 길이들은
            // 특정 구간 최대 길이보다 다 짧기 때문에
            // 모두 확인할 필요가 없음
            else tmpMaxLength = 1; // 임시 길이 값 초기화
        }
    }
} // class 종료