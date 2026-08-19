package com.playlist.view;

import com.playlist.model.Song;

import java.util.List;

/**
 * 노래 목록을 열 너비가 맞는 콘솔 표 형태로 출력한다.
 */
public class ConsoleTable {
    /** 한글이 영문보다 넓게 표시되는 점을 반영해 문자열의 출력 너비를 계산한다. */
    private int displayWidth(String text) {
        int width = 0;

        for (char ch : text.toCharArray()) {
            if (ch >= '가' && ch <= '힣') {
                width += 2;
            } else {
                width += 1;
            }
        }

        return width;
    }

    /** 문자열 오른쪽을 공백으로 채워 지정한 출력 너비를 맞춘다. */
    private String padRight(String text, int width) {
        int spaceCount = Math.max(0, width - displayWidth(text));
        return text + " ".repeat(spaceCount);
    }

    /** 조회된 노래 목록과 총 곡 수를 표 형태로 출력한다. */
    public void showSongTable(List<Song> songs) {
        if (songs.isEmpty()) {
            System.out.println("검색된 노래가 없습니다.");
            return;
        }

        String line = "-".repeat(72);

        System.out.println();
        System.out.println(line);

        System.out.println(
                padRight("ID", 3) +
                        padRight("노래 제목", 30) +
                        padRight("아티스트", 20) +
                        padRight("장르", 10)
        );

        System.out.println(line);

        for (Song song : songs) {
            System.out.println(
                    padRight(String.valueOf(song.getId()), 3) +
                            padRight(song.getTitle(), 30) +
                            padRight(song.getArtist(), 20) +
                            padRight(song.getGenre().getDisplayName(), 10)
            );
        }

        System.out.println(line);
        System.out.println("총 " + songs.size() + "곡");
    }
}
