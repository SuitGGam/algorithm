/*
 * 백준 1244번 : 스위치 켜고 끄기
 * https://www.acmicpc.net/problem/1244
 * 난이도 : 실버 4
 * 풀이 날짜 : 2026-01-06
 * 간단 설명 : 처음 스위치 상태에서 남학생과 여학생이 바꾼 최종 스위치 상태를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N * M (100 x 100)
 * 이유 : 남학생과 여학생 모두 매번 최대 N까지 탐색할 수 있음
 *
 * 알고리즘 : 시뮬레이션
 * 자료구조 : Array
 * 시간 복잡도 : O (N * M)
 * 풀이 방법 : 스위치의 상태를 반대로 바꾸는 시뮬레이션임
 * 남학생은 for문으로 받은 번호부터 배수에 해당하는 부분을 반대로 바꾸고
 * 여학생은 투 포인터를 활용해서 양쪽의 대칭을 확인해서 구간을 확대 확정하고 반대로 바꿈
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1244 {
    static int N; // 스위치의 개수
    static int[] switchState; // 스위치 상태 배열
    
    static void multiOpposite(int num) { // 배수 자리의 스위치를 반대로 바꾸는 함수
        for (int i = num; i <= N; i += num) switchState[i] = 1 - switchState[i]; // 배수인 곳 반대로 바꾸기
    } // multiOpposite 종료
    
    static void rangeOpposite(int num) { // 상태가 대칭인 구간의 스위치를 반대로 바꾸는 함수
        int left  = num - 1; // 받은 숫자의 왼쪽
        int right = num + 1; // 받은 숫자의 오른쪽
        while (left > 0 && right <= N) {
            if (switchState[left] == switchState[right]) { // 대칭인 경우
                left--;  // 왼쪽으로 한 칸 이동
                right++; // 오른쪽으로 한 칸 이동
            } else break; // 대칭이 아닌 경우
        }
        
        for (int i = left + 1; i <= right - 1; i++) switchState[i] = 1 - switchState[i]; // 대칭인 구간 반대로 바꾸기
    } // rangeOpposite 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine()); // 스위치의 개수
        switchState = new int[N + 1]; // 스위치 상태 배열
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) switchState[i] = Integer.parseInt(st.nextToken()); // 스위치 상태 저장
        
        int student = Integer.parseInt(br.readLine()); // 학생의 수
        for (int i = 0; i < student; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            char gender = st.nextToken().charAt(0); // 성별
            int num = Integer.parseInt(st.nextToken()); // 받은 번호
            
            if (gender == '1') multiOpposite(num); // 남학생인 경우
            else rangeOpposite(num); // 여학생인 경우
        }
        
        // 버퍼 닫기
        br.close();
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 1; i <= N; i++) {
            sb.append(switchState[i]).append(" "); // 결과값 추가하기
            if (i % 20 == 0) sb.append("\n"); // 20개마다 개행 처리
        }
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료