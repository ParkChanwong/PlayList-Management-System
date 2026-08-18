package com.playlist.view;

import com.playlist.controller.SongController;
import com.playlist.model.Genre;
import com.playlist.model.Song;

import java.util.List;
import java.util.Scanner;

public class PlayListView {
    private final Scanner sc = new Scanner(System.in);
    private final SongController songController = new SongController();
    ConsoleTable table = new ConsoleTable();

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

    public void addSong() {
        System.out.println();
        System.out.println("======== 노래 추가 ========");
        String title = readMenu("제목 : ");
        String artist = readMenu("아티스트 : ");
        Genre genre = readGenre("장르 : ");

        boolean isSave = checkAnswer("저장");

        if (isSave) {
            boolean isSuccess = songController.addSong(title, artist, genre);
            if (isSuccess) {
                showMessage("내 플레이리스트에 노래가 추가되었습니다.");
            } else {
                showError("같은 노래가 존재합니다. 다른 노래를 추가해주세요");
            }
        } else return;
    }

    public void searchMainMenu() {
        System.out.println();
        System.out.println("======== 플레이리스트 조회 ========");
        System.out.println("1. 전체 조회");
        System.out.println("2. 노래명으로 조회");
        System.out.println("3. 아티스트명으로 조회");
        System.out.println("4. 장르별 조회");
        System.out.println("9. 이전 메뉴로");

        searchSong();
    }

    public void searchSong() {
        int select = readInt("선택 : ");

        switch (select) {
            case 1 -> table.showSongTable(songController.searchAllSong());
            case 2 -> table.showSongTable(songController.searchTitleSong(readMenu("노래명 : ")));
            case 3 -> table.showSongTable(songController.searchArtistSong(readMenu("아티스트 : ")));
            case 4 -> table.showSongTable(songController.searchGenre(readGenre("장르 : ")));
            case 9 -> { return; }
            default -> showError("메뉴에 있는 번호를 선택해주세요.");
        }
    }

    public void deleteSong() {
        while (true) {
            System.out.println();
            System.out.println("======== 노래 삭제 ========");

            int select = readInt("삭제할 노래 ID (0: 취소) : ");

            if (select == 0) return;

            if (songController.searchById(select).isEmpty()) {
                System.out.println("삭제할 노래가 없습니다.");
                continue;
            }

            table.showSongTable(songController.searchById(select));
            System.out.println("정말 삭제하시겠습니까?");
            boolean isDelete = checkAnswer("삭제");

            if (isDelete) {
                songController.deleteSong(select);
                System.out.println("삭제되었습니다.");
            }

            return;
        }
    }

    public boolean checkAnswer(String promt) {
        System.out.println("1. " + promt + "        2. 취소");
        int select = readInt("선택 : ");

        if (select == 1) {
            return true;
        }

        return false;
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String num = sc.nextLine().trim();

            try {
                return Integer.parseInt(num);
            } catch (NumberFormatException e) {
                showError("숫자를 입력해주세요.");
            }
        }
    };

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

    public Genre readGenre(String prompt) {
        System.out.println(prompt);

        Genre[] genres = Genre.values();

        for (int i = 0; i < genres.length; i++) {
            System.out.println((i + 1) + ". " + genres[i].getDisplayName());
        }

        while (true) {
            int selectMenu = readInt("선택 : ");

            if (selectMenu < 1 || selectMenu > genres.length) {
                showError("1 ~ " + genres.length + " 사이의 번호를 입력해주세요.");
                continue;
            }

            return genres[selectMenu - 1];
        }
    }

    public void exit() {
        sc.close();
    }

}
