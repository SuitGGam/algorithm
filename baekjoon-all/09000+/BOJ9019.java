/*
 * 백준 9019번 : DSLR
 * https://www.acmicpc.net/problem/9019
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-11-27
 * 간단 설명 : 정수 A를 정수 B로 변환하는데 필요한 최소 명령어를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ9019 {
    static char[] command = { 'D', 'S', 'L', 'R' };
    static String convert(int A, int B) {
        boolean[] visited = new boolean[9999 + 1]; // 방문 처리 배열
        ArrayDeque<String[]> queue = new ArrayDeque<>(); // 변환값과 명령어를 저장할 큐
        visited[A] = true; // 방문 처리
        queue.add(new String[] { String.valueOf(A), "" }); // 시작값 add
        String result = ""; // 최종 명령어 초기화
        while (!queue.isEmpty()) {
            String[] curN = queue.poll(); // 현재 레지스터 값
            
            for (int c = 0; c < 4; c++) {
                int n = 0; // 변환값 선언 및 초기화
                if (command[c] == 'D') n = D(curN[0]); // D인 경우
                else if (command[c] == 'S') n = S(curN[0]); // S인 경우
                else if (command[c] == 'L') n = L(curN[0]); // L인 경우
                else n = R(curN[0]); // R인 경우
                
                if (visited[n]) continue; // 방문한 곳이면 continue
                
                visited[n] = true; // 방문 처리
                String curCommand = curN[1] + command[c]; // 명령어 추가
                
                if (n == B) { result = curCommand; queue.clear(); }// 변환이 완료되면 최종 명령어 갱신 후 큐 비우기
                else queue.add(new String[] { String.valueOf(n), curCommand }); // 새로운 변환 값 add
            }
        }
        
        return result; // 명령어 반환
    } // convert 종료
    
    static final int MOD = 10_000; // 모듈러
    static int D(String N) { // 값을 두 배로 만드는 함수
        int n = Integer.parseInt(N); // 숫자 변환
        n = n * 2 % MOD; // 두 배로 만들기
        return n; // 변환값 반환
    } // D 종료
    
    static int S(String N) { // 값에서 1을 빼는 함수
        int n = Integer.parseInt(N); // 숫자 변환
        n -= 1; // 1 빼기
        if (n == -1) n = 9999; // 변환값이 -1이면 9999로 만들기
        return n; // 변환값 반환
    } // S 종료
    
    static int L(String N) { // 각 자릿수를 왼쪽으로 미는 함수
        int n = Integer.parseInt(N); // 숫자 변환
        n = n * 10 % 10000 + n / 1000; // 자릿수 왼쪽으로 밀기
        return n; // 변환값 반환
    } // L 종료
    
    static int R(String N) { // 각 자릿수를 오른쪽으로 미는 함수
        int n = Integer.parseInt(N); // 숫자 변환
        n = n / 10 + n % 10 * 1000;
        return n; // 변환값 반환
    } // R 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken()); // 레지스터 초기 값
            int B = Integer.parseInt(st.nextToken()); // 레지스터 최종 값
            
            sb.append(convert(A, B)).append("\n"); // 결과값 추가하기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료
