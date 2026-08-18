package com.playlist;

import com.playlist.view.PlayListView;

public class Main {
    public static void main(String[] args) {
        PlayListView playlistView = new PlayListView();
        playlistView.showMessage("플레이리스트 관리 프로그램 시작");

        // 메인
        while (true) {
            playlistView.mainMenu();
            String select = playlistView.readMenu("선택 : ");

            switch (select) {
                case "1" -> playlistView.addMenu();
                case "2" -> System.out.println("sss");
                case "3" -> System.out.println("3333");
                case "4" -> System.out.println("4444");
                case "0" -> {
                    playlistView.showMessage("프로그램을 종료합니다.");
                    playlistView.exit();
                    return;
                }
                default -> playlistView.showError("메뉴에 있는 번호를 선택해주세요.");
            }
        }

    }
}
