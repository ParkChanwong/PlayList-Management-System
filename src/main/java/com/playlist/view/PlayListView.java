package com.playlist.view;

import java.util.Scanner;

public class PlayListView {
    private final Scanner sc = new Scanner(System.in);

    // 일반 메시지 출력 메서드
    public void showMessage(String message) {
        System.out.println(message);
    }

    // 오류 메시지 출력 메서드
    public void showError(String message) {
        System.out.println("[오류] " + message);
    }

    // 성공 메시지 출력 메서드
    public void showSuccess(String message) {
        System.out.println("[완료] " + message);
    }

    // 메인 메뉴 출력 메서드
    public void mainMenu() {
        System.out.println();
        System.out.println("======== 플레이리스트 관리 프로그램 ========");
        System.out.println("1. 노래 추가");
        System.out.println("2. 노래 조회");
        System.out.println("3. 노래 정보 수정");
        System.out.println("4. 노래 삭제");
        System.out.println("0. 프로그램 종료");
    }

    public void addMenu() {
        System.out.println();
        System.out.println("======== 노래 추가 ========");
        String title = readMenu("제목 : ");

    }

    // 입력 값이 빈값인지 확인 메서드
    public String readMenu(String prompt) {
        while (true) {
            System.out.print(prompt);
            String menu = sc.nextLine().trim();

            if (!menu.isEmpty()) {
                return menu;
            }

            showError("한 글자 이상 입력해주세요");
        }
    }

    public void exit() {
        sc.close();
    }

}
