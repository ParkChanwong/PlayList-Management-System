package com.playlist.repository;

import com.playlist.model.Genre;
import com.playlist.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongRepository {
    private final List<Song> songs = new ArrayList<>();

    public SongRepository() { initializeSongs(); }

    private void initializeSongs() {
        save(new Song("Love Attack", "르센느", Genre.K_POP));
        save(new Song("LEMONADE", "에스파", Genre.K_POP));
        save(new Song("It′s Me", "아일릿", Genre.K_POP));
        save(new Song("만찬가", "태연", Genre.J_POP));
        save(new Song("사랑하게 될 거야", "한로로", Genre.INDIE));
        save(new Song("소문의 낙원", "AKMU", Genre.BALLAD));
        save(new Song("한 페이지가 될 수 있게", "DAY6", Genre.INDIE));
        save(new Song("생각을 멈추다 보면", "최유리", Genre.BALLAD));
        save(new Song("그때 헤어지면 돼", "로이킴", Genre.BALLAD));
        save(new Song("북두칠성", "로이킴", Genre.BALLAD));
        save(new Song("잘 지내자, 우리", "로이킴", Genre.BALLAD));
    }

    public void save(Song song) {
        song.setId(songs.size() + 1);
        songs.add(song);
    }

    public List<Song> findAll() {
        return new ArrayList<>(songs);
    }

    public List<Song> findByTitle(String title) {
        return findAll().stream().filter(song -> song.getTitle().contains(title)).toList();
    }

    public List<Song> findByArtist(String artist) {
        return findAll().stream().filter(song -> song.getArtist().contains(artist)).toList();
    }

    public List<Song> findByGenre(Genre genre) {
        return findAll().stream().filter(song -> song.getGenre().equals(genre)).toList();
    }
}
