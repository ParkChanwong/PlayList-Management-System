package com.playlist;

import com.playlist.view.PlayListView;

/**
 * 플레이리스트 관리 프로그램의 실행 진입점이다.
 */
public class Main {
    /**
     * 메인 메뉴를 반복해서 표시하고 사용자가 선택한 기능을 실행한다.
     */
    public static void main(String[] args) {
        PlayListView playlistView = new PlayListView();
        playlistView.showMessage("플레이리스트 관리 프로그램 시작");

        while (true) {
            playlistView.mainMenu();
            String select = playlistView.readMenu("선택 : ");

            switch (select) {
                case "1" -> playlistView.addSong();
                case "2" -> playlistView.searchMainMenu();
                case "3" -> playlistView.updateSong();
                case "4" -> playlistView.deleteSong();
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
