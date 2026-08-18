package com.playlist.view;

import com.playlist.model.Song;

import java.util.List;

public class ConsoleTable {
    private int displayWidth(String text) {
        int width = 0;

        for (char ch : text.toCharArray()) {
            // 완성형 한글은 화면에서 2칸으로 계산
            if (ch >= '가' && ch <= '힣') {
                width += 2;
            } else {
                width += 1;
            }
        }

        return width;
    }

    private String padRight(String text, int width) {
        int spaceCount = Math.max(0, width - displayWidth(text));
        return text + " ".repeat(spaceCount);
    }

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
