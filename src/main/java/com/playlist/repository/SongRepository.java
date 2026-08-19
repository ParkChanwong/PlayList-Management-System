package com.playlist.repository;

import com.playlist.model.Genre;
import com.playlist.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * 메모리에서 노래 목록을 관리하고 저장·조회·수정·삭제 기능을 제공한다.
 */
public class SongRepository {
    private final List<Song> songs = new ArrayList<>();
    private int nextId = 1;

    public SongRepository() { initializeSongs(); }

    /** Repository 생성 시 프로그램에서 사용할 기본 노래 목록을 등록한다. */
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

    /** 새 노래에 중복되지 않는 ID를 부여한 뒤 목록에 저장한다. */
    public void save(Song song) {
        song.setId(nextId++);
        songs.add(song);
    }

    /** 외부에서 목록 자체를 변경하지 못하도록 복사한 전체 목록을 반환한다. */
    public List<Song> findAll() {
        return new ArrayList<>(songs);
    }

    /** 제목에 검색어가 포함된 노래를 조회한다. */
    public List<Song> findByTitle(String title) {
        return songs.stream()
                .filter(song -> song.getTitle().contains(title))
                .toList();
    }

    /** 아티스트명에 검색어가 포함된 노래를 조회한다. */
    public List<Song> findByArtist(String artist) {
        return songs.stream()
                .filter(song -> song.getArtist().contains(artist))
                .toList();
    }

    /** 장르가 일치하는 노래를 조회한다. */
    public List<Song> findByGenre(Genre genre) {
        return songs.stream()
                .filter(song -> song.getGenre() == genre)
                .toList();
    }

    /** ID가 일치하는 노래를 조회한다. */
    public List<Song> findById(int id) {
        return songs.stream()
                .filter(song -> song.getId() == id)
                .toList();
    }

    /** ID가 일치하는 노래를 목록에서 삭제한다. */
    public void deleteSongById(int id) {
        songs.removeIf(song -> song.getId() == id);
    }

    /** ID가 일치하는 노래의 제목, 아티스트와 장르를 변경한다. */
    public void updateSongById(int id, String title, String artist, Genre genre) {
        List<Song> song = findById(id);

        song.get(0).setTitle(title);
        song.get(0).setArtist(artist);
        song.get(0).setGenre(genre);
    }
}
