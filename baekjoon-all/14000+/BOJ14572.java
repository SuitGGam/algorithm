/*
 * 백준 14572번 : 스터디 그룹
 * https://www.acmicpc.net/problem/14572
 * 난이도 : 플래티넘 5
 * 풀이 날짜 : 2025-07-24
 * 간단 설명 : 스터디 그룹 부분 집합의 최대 효율성을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;

public class BOJ14572 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N, K, curMember;  // 학생의 수, 알고리즘의 수, 현재 멤버 수
    static int maxEfficiency; // 최대 효율성
    static int left, right;   // 왼쪽 포인터, 오른쪽 포인터
    
    static ArrayList<Integer>[] studentInfo; // 학생의 정보(알고리즘의 수, 실력, 알고 있는 알고리즘의 종류)를 담을 배열
    
    static int algoType; // 그룹원이 알고 있는 알고리즘 종류 개수
    static int allKnowAlgo; // 그룹원 모두가 알고 있는 알고리즘의 개수
    static int[] counting;  // 현재 그룹원의 알고 있는 알고리즘 종류
    
    static void vaildGroup() {
        allKnowAlgo = 0; // 모두가 알고 있는 알고리즘 초기화
        // 모두가 알고 있는 알고리즘 세기
        for (int i = 1; i <= K; i++) if (counting[i] == curMember) allKnowAlgo++;
        
        // 현재 그룹의 효율성
        int nowEfficiency = (algoType - allKnowAlgo) * curMember;
        
        // 최대 효율성 갱신
        if (maxEfficiency < nowEfficiency) maxEfficiency = nowEfficiency;
        
        right++; // 오른쪽 포인터 오른쪽으로 한 칸 옮기기
        curMember++; // 현재 그룹원 추가
        // 종료 조건
        
        if (right == N) return;
        
        // 알고리즘 관련 변수 갱신
        allKnowAlgo = 0; // 모두가 알고 있는 알고리즘 초기화
        for (int i = 1; i < studentInfo[right].size(); i++) {
            int algo = studentInfo[right].get(i); // 알고리즘 종류
            if (counting[algo]++ == 0) algoType++; // 알고리즘 카운팅 및 종류 세기
        }
    }
    
    static void invalidGroup() {
        left++; // 왼쪽 포인터 오른쪽으로 한 칸 옮기기
        curMember--; // 현재 그룹원 감소
        
        // 알고리즘 관련 변수 갱신
        for (int i = 1; i < studentInfo[left - 1].size(); i++) {
            int algo = studentInfo[left - 1].get(i); // 알고리즘 종류
            if (counting[algo]-- == 1) algoType--; // 알고리즘 카운팅 및 종류 세기
        }
    }
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 학생의 수
        K = Integer.parseInt(st.nextToken()); // 알고리즘의 수
        int D = Integer.parseInt(st.nextToken()); // 최대 실력 차이
        
        // 학생의 정보(알고리즘의 수, 실력, 알고 있는 알고리즘의 종류)를 담을 배열
        studentInfo = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            studentInfo[i] = new ArrayList<Integer>();
        }
        
        counting = new int[K + 1]; // 그룹원이 알고 있는 알고리즘 종류
        for (int i = 0; i < N; i++) { // 학생 정보 저장
            st = new StringTokenizer(br.readLine(), " ");
            int M = Integer.parseInt(st.nextToken()); // 해당 학생이 알고 있는 알고리즘의 수
            studentInfo[i].add(Integer.parseInt(st.nextToken())); // 해당 학생의 실력
            
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) { // 학생이 알고 있는 알고리즘 종류 저장
                studentInfo[i].add(Integer.parseInt(st.nextToken())); // 알고리즘 종류
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        // 실력 기준으로 비내림차순 정렬
        Arrays.sort(studentInfo, Comparator.comparingInt(a -> a.get(0)));
        
        algoType = allKnowAlgo = 0; // 알고리즘 관련 변수 초기화
        maxEfficiency = 0; // 최대 효율성
        left  = 0; // 왼쪽 포인터
        right = 0; // 오른쪽 포인터
        curMember = right - left + 1; // 현재 그룹원 수
        
        // 혼자일 때 기준 잡아놓기
        // 알고리즘 관련 변수 갱신
        for (int i = 1; i < studentInfo[right].size(); i++) {
            int algo = studentInfo[right].get(i); // 알고리즘 종류
            counting[algo]++; // 알고리즘 카운팅
            algoType++; // 알고리즘 종류 세기
            allKnowAlgo++; // 모두 알고 있는 알고리즘 증가
        }
        
        while (right < N) {
            // 해당 그룹의 실력 차이가 D 이하인 경우
            if (studentInfo[right].get(0) - studentInfo[left].get(0) <= D) vaildGroup();
                
                // 해당 그룹의 실력 차이가 D 초과인 경우
            else invalidGroup();
        }
        
        // 결과값 출력하기
        System.out.print(maxEfficiency);
    } // main 종료
} // Main 종료