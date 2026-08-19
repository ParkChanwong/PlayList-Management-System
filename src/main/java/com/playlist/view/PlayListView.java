package com.playlist.view;

import com.playlist.controller.SongController;
import com.playlist.controller.SongUpdateResult;
import com.playlist.model.Genre;
import com.playlist.model.Song;

import java.util.List;
import java.util.Scanner;

/**
 * 사용자에게 메뉴를 보여주고 입력을 받아 Controller에 기능 수행을 요청한다.
 */
public class PlayListView {
    private final Scanner sc = new Scanner(System.in);
    private final SongController songController = new SongController();
    private final ConsoleTable table = new ConsoleTable();

    /** 일반 안내 메시지를 출력한다. */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /** 오류 메시지에 오류 표시를 붙여 출력한다. */
    public void showError(String message) {
        System.out.println("[오류] " + message);
    }

    /** 성공 메시지에 완료 표시를 붙여 출력한다. */
    public void showSuccess(String message) {
        System.out.println("[완료] " + message);
    }

    /** 플레이리스트 관리 프로그램의 메인 메뉴를 출력한다. */
    public void mainMenu() {
        System.out.println();
        System.out.println("======== 플레이리스트 관리 프로그램 ========");
        System.out.println("1. 노래 추가");
        System.out.println("2. 노래 조회");
        System.out.println("3. 노래 정보 수정");
        System.out.println("4. 노래 삭제");
        System.out.println("0. 프로그램 종료");
    }

    /** 노래 정보를 입력받고 사용자 확인 후 플레이리스트에 추가한다. */
    public void addSong() {
        System.out.println();
        System.out.println("======== 노래 추가 ========");
        String title = readMenu("제목 : ");
        String artist = readMenu("아티스트 : ");
        Genre genre = readGenre("장르 : ");

        boolean isSave = checkAnswer("저장");

        if (!isSave) return;

        boolean isSuccess = songController.addSong(title, artist, genre);

        if (isSuccess) {
            showSuccess("내 플레이리스트에 노래가 추가되었습니다.");
        } else {
            showError("내 플레이리스트에 등록된 노래입니다.");
        }
    }

    /** 노래 조회 방법을 선택할 수 있는 하위 메뉴를 표시한다. */
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

    /** 선택한 조건으로 노래를 조회하고 결과를 표로 출력한다. */
    public void searchSong() {
        while (true) {
            int select = readInt("선택 : ");

            switch (select) {
                case 1 -> table.showSongTable(songController.searchAllSong());
                case 2 -> table.showSongTable(songController.searchTitleSong(readMenu("노래명 : ")));
                case 3 -> table.showSongTable(songController.searchArtistSong(readMenu("아티스트 : ")));
                case 4 -> table.showSongTable(songController.searchGenre(readGenre("장르 : ")));
                case 9 -> { return; }
                default -> {
                    showError("메뉴에 있는 번호를 선택해주세요.");
                    continue;
                }
            }

            return;
        }
    }

    /** 삭제할 노래를 ID로 확인하고 사용자 동의를 받은 뒤 삭제한다. */
    public void deleteSong() {
        while (true) {
            System.out.println();
            System.out.println("======== 노래 삭제 ========");

            int select = readInt("삭제할 노래 ID (0: 취소) : ");

            if (select == 0) {
                showMessage("노래 삭제가 취소되었습니다.");
                return;
            }

            List<Song> song = songController.searchById(select);

            if (song.isEmpty()) {
                showError("삭제할 노래를 찾지 못했습니다.");
                continue;
            }

            table.showSongTable(song);

            showMessage("정말 삭제하시겠습니까?");
            boolean isDelete = checkAnswer("삭제");

            if (isDelete) {
                songController.deleteSong(select);
                showSuccess("삭제되었습니다.");
            }

            return;
        }
    }

    /**
     * 수정할 노래와 새 정보를 입력받고 수정 결과에 맞는 메시지를 출력한다.
     * 변경 사항이 없거나 다른 노래와 중복되면 수정 화면을 다시 표시한다.
     */
    public void updateSong() {
        while (true) {
            System.out.println();
            System.out.println("======== 노래 수정 ========");

            int select = readInt("수정할 노래 ID (0: 취소) : ");

            if (select == 0) {
                showMessage("노래 수정이 취소되었습니다.");
                return;
            }

            List<Song> song = songController.searchById(select);

            if (song.isEmpty()) {
                showMessage("수정할 노래가 없습니다.");
                continue;
            }

            table.showSongTable(song);

            String title = readMenu("노래명 : ");
            String artist = readMenu("아티스트 : ");
            Genre genre = readGenre("장르 : ");

            showMessage("정말 수정하시겠습니까?");
            boolean isUpdate = checkAnswer("수정");

            if (!isUpdate) return;

            SongUpdateResult result = songController.updateSong(select, title, artist, genre);

            switch (result) {
                case UPDATED -> { showSuccess("수정이 완료되었습니다."); }
                case UNCHANGED -> {
                    showMessage("변경된 내용이 없습니다.");
                    continue;
                }
                case DUPLICATE -> {
                    showError("내 플레이리스트에 등록된 노래입니다.");
                    continue;
                }
            }

            return;
        }
    }

    /** 저장·수정·삭제 작업의 실행 여부를 1 또는 2로 확인한다. */
    public boolean checkAnswer(String prompt) {
        while (true) {
            showMessage("1. " + prompt + "        2. 취소");
            int select = readInt("선택 : ");

            if (select == 1) {
                return true;
            } else if (select == 2) {
                showMessage("작업이 취소되었습니다.");
                return false;
            } else {
                showError(prompt + " 또는 취소를 선택해주세요");
            }
        }
    }

    /** 정수가 입력될 때까지 반복해서 입력받는다. */
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
    }

    /** 공백을 제외한 한 글자 이상의 문자열이 입력될 때까지 반복해서 입력받는다. */
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

    /** 장르 목록을 표시하고 유효한 번호에 해당하는 장르를 반환한다. */
    public Genre readGenre(String prompt) {
        showMessage(prompt);

        Genre[] genres = Genre.values();

        for (int i = 0; i < genres.length; i++) {
            showMessage((i + 1) + ". " + genres[i].getDisplayName());
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

    /** 프로그램 종료 시 입력에 사용한 Scanner를 닫는다. */
    public void exit() {
        sc.close();
    }

}
