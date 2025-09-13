/*
 * 백준 33869번 : 일기 암호화하기
 * https://www.acmicpc.net/problem/33869
 * 난이도 : 브론즈 2
 * 풀이 날짜 : 2025-09-13
 * 간단 설명 : 주어진 단어를 암호화에 맞게 변환해서 출력하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ33869 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static final int ALPHABET_NUMBER = 26; // 알파벳 개수
    
    public static void main(String[] args) throws IOException {
        String W = br.readLine(); // 암호화에 사용할 단어
        String S = br.readLine(); // 암호화할 문장
        
        // 버퍼 닫기
        br.close();
        
        boolean[] checkAlphabet = new boolean[ALPHABET_NUMBER]; // 알파벳 사용 유무 확인 배열
        char[] encryption = new char[ALPHABET_NUMBER]; // 암호화된 배열
        
        // 암호화에 사용할 단어를 이용한 암호화 배열 만들기
        int dup = 0; // 알파벳이 중복된 횟수
        for (int i = 0; i < W.length(); i++) {
            char ch = W.charAt(i);
            
            if (checkAlphabet[ch - 65]) {
                dup++; // 중복 횟수 증가
                continue; // 중복된 알파벳이면 continue
            }
            
            checkAlphabet[ch - 65] = true; // 사용 여부 변환
            encryption[i - dup] = ch; // 암호화 배열 구성하기
        }
        
        // 암호화 배열 남은 자리 채우기
        int alphabetIdx = 0; // 남은 알파벳 찾는 인덱스
        for (int i = W.length() - dup; i < ALPHABET_NUMBER; i++) {
            // 사용 안 된 알파벳 순서대로 찾기
            while (alphabetIdx < ALPHABET_NUMBER && checkAlphabet[alphabetIdx]) alphabetIdx++;
            
            encryption[i] = (char) (alphabetIdx + ALPHABET_NUMBER); // 암호화 배열 구성하기
            checkAlphabet[alphabetIdx] = true; // 사용 여부 변환
        }
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < S.length(); i++) {
            sb.append(encryption[S.charAt(i) - ALPHABET_NUMBER]); // 결과값 추가하기
        }
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료