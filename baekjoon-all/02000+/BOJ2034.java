/*
 * 백준 2034번 : 반음
 * https://www.acmicpc.net/problem/2034
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-11-05
 * 간단 설명 : 악보가 정확히 작동했을 때 시작음과 끝음을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2034 {
    static int[] sheet; // 악보 배열
    static int[] note = {2, 1, 2, 2, 1, 2, 2}; // 음 거리 배열
    
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void validSheet(int startNote) {
        int curNote = startNote; // 현재 음
        int lastNote = -1; // 마지막 음
        
        for (int i = 0; i < sheet.length; i++) {
            int remain = sheet[i]; // 남은 악보 음 차이
            // 음 차이가 0이면 음 유지
            if (remain > 0) { // 음이 올라가는 경우
                while (remain > 0) {
                    int gap = note[curNote]; // 음 차이
                    if (remain < gap) return; // 음 차이가 안 맞으면 종료
                    remain -= gap; // 음 차이가 같거나 크면 빼주기
                    curNote = (curNote + 1) % 7; // 다음 음 갱신
                }
            } else if (remain < 0) { // 음이 내려가는 경우
                while (remain < 0) {
                    int gap = note[(curNote + 6) % 7]; // 음 차이
                    if (-remain < gap) return; // 음 차이가 안 맞으면 종료
                    remain += gap; // 음 차이가 크거나 같으면 빼주기
                    curNote = (curNote + 6) % 7; // 다음 음 갱신
                }
            }
            
            if (i == sheet.length - 1) lastNote = curNote; // 마지막 음 기록
        }
        
        // 결과값 추가하기
        sb.append((char) (startNote + 'A')).append(" ").append((char) (lastNote + 'A')).append("\n");
    } // validSheet 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); // 악보의 길이
        sheet = new int[n]; // 배열 크기 지정
        for (int i = 0; i < n; i++) sheet[i] = Integer.parseInt(br.readLine()); // 악보 저장
        
        // 버퍼 닫기
        br.close();
        
        for (int i = 0; i < note.length; i++) validSheet(i);
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료
