/*
 * 백준 2529번 : 부등호
 * https://www.acmicpc.net/problem/2529
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-12-25
 * 간단 설명 : 부등호 관계를 만족하는 것 중에 최댓값과 최솟값을 찾는 문제
 */

/*
 * 최대 경우의 수 : 10! (3,628,800)
 * 이유 : 각 숫자에 대해서 중복 허용 X
 * 시간 복잡도 : O (10 P k+1)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2529 {
    static int k; // 부등호의 개수
    static char[] inequality; // 부등호 배열
    static boolean[] visited = new boolean[10]; // 숫자 사용 여부
    static ArrayList<String> result = new ArrayList<>(); // 조건에 맞는 정답 후보 배열
    
    static void permutation(int idx, String num) { // 순열을 만드는 함수
        // 종료 조건
        if (idx > k) { // 부등호 관계를 다 적립한 경우
            result.add(num); // 결과값 add
            return; // 종료
        }
        
        for (int i = 0; i <= 9; i++) {
            if (visited[i]) continue; // 이미 사용한 숫자면 continue
            
            if (idx == 0 || valid(num.charAt(idx - 1) - '0', i, inequality[idx - 1])) {
                visited[i] = true; // 숫자 사용 처리
                permutation(idx + 1, num + i); // 다음 숫자 찾기
                visited[i] = false; // 숫자 미사용 처리
            }
        }
    } // permutation 종료
    
    static boolean valid(int left, int right, char op) { // 부등호 유효성을 확인하는 함수
        if (op == '<') return left < right; // 부등호가 < 인 경우
        else return left > right; // 부등호가 > 인 경우
    } // valid 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        k = Integer.parseInt(br.readLine()); // 부등호의 개수
        
        st = new StringTokenizer(br.readLine());
        inequality = new char[k]; // 부등호 배열
        for (int i = 0; i < k; i++) inequality[i] = st.nextToken().charAt(0);
        
        // 버퍼 닫기
        br.close();
        
        permutation(0, ""); // 순열 만들기
        Collections.sort(result); // 결과 정렬
        
        // 결과값 출력하기
        System.out.print(result.get(result.size() - 1) + "\n" + result.get(0));
    } // main 종료
} // Main 종료