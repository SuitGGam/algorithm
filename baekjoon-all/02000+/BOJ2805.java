/*
 * 백준 2805번 : 나무 자르기
 * https://www.acmicpc.net/problem/2805
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-06-29
 * 간단 설명 : N개의 나무를 똑같은 높이로 잘랐을 때 원하는 양 이상의 나무를 구할 수 있는 최대 높이를 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ2805 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M; // 나무의 수, 필요한 나무의 길이
	static int[] tree; // 나무의 높이를 담을 배열
	
	// 최대 나무 높이를 찾는 함수
	static int findMaxHeight() {
		// 나무 높이 비내림차순 정렬
		// 높이가 높은 나무부터 확인해서 확인하는 범위 최소화
		Arrays.sort(tree);

		// 방법은 두 가지
		// 첫 번째는 완전 탐색
		// 높이가 제일 높은 나무부터 높이를 1씩 낮추면서 진행
		// 두 번째는 이진 탐색
		// 중간 나무 높이부터 해서 범위를 좁혀가며 진행
		// 여기선 이진 탐색으로 진행
		int startHeight = 0; // 시작 높이
		int endHeight = tree[N - 1]; // 최대 나무 높이
		int answer = -1; // 최대 나무 높이
		
		while (startHeight <= endHeight) {
			int mid = (startHeight + endHeight) / 2; // 중간 높이
			long tmpTreeLength = 0; // 임시 나무 길이
			
			// 나무 자르기
			for (int i = N - 1; i >= 0; i--) {
				if (tree[i] <= mid) break; // 자르려는 높이보다 
				tmpTreeLength += tree[i] - mid;
			}
			
			// 목표 길이보다 많거나 같으면 높이 높이기
			if (tmpTreeLength >= M) {
				answer = mid; // 나무 높이 갱신
				startHeight = mid + 1;
			}
			// 목표 길이보다 적으면 높이 낮추기
			else endHeight = mid - 1;
		}
		
		// 정답 반환
		return answer;
	} // findMaxHeight 종료
	
	public static void main(String[] args) throws IOException {
		st =  new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 나무의 수
		M = Integer.parseInt(st.nextToken()); // 필요한 나무의 길이
		
		tree = new int[N]; // 나무의 높이를 담을 배열의 크기 지정
		st =  new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken()); // 나무의 높이 저장
		}
		
		// 버퍼 닫기
		br.close();
		
		// 최대 나무 높이
		int maxHeight = findMaxHeight();
		
		// 결과값 출력하기
		System.out.print(maxHeight);
	} // main 종료
} // class 종료