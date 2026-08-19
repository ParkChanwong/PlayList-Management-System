package com.playlist.controller;

import com.playlist.model.Genre;
import com.playlist.model.Song;
import com.playlist.repository.SongRepository;

import java.util.List;

/**
 * View의 요청을 받아 노래 관련 규칙을 검사하고 Repository 기능을 호출한다.
 */
public class SongController {

    private final SongRepository songRepository = new SongRepository();

    /** 중복되지 않은 노래를 저장하고 성공 여부를 반환한다. */
    public boolean addSong(String title, String artist, Genre genre) {
        if (isDuplicateSong(title, artist, genre)) return false;

        Song song = new Song(title, artist, genre);
        songRepository.save(song);

        return true;
    }

    /** 플레이리스트의 모든 노래를 조회한다. */
    public List<Song> searchAllSong() { return songRepository.findAll(); }
    /** 제목에 검색어가 포함된 노래를 조회한다. */
    public List<Song> searchTitleSong(String title) { return songRepository.findByTitle(title); }
    /** 아티스트명에 검색어가 포함된 노래를 조회한다. */
    public List<Song> searchArtistSong(String artist) {return songRepository.findByArtist(artist); }
    /** 선택한 장르에 해당하는 노래를 조회한다. */
    public List<Song> searchGenre(Genre genre) { return songRepository.findByGenre(genre); }
    /** ID가 일치하는 노래를 조회한다. */
    public List<Song> searchById(int id) { return songRepository.findById(id); }

    /** ID가 일치하는 노래를 삭제한다. */
    public void deleteSong(int id) { songRepository.deleteSongById(id); }

    /**
     * 기존 정보와의 일치 여부와 다른 노래와의 중복 여부를 검사한 뒤 수정한다.
     */
    public SongUpdateResult updateSong(int id, String title, String artist, Genre genre) {
        boolean isUnchanged = searchById(id).stream().anyMatch(song -> song.getTitle().equals(title) && song.getArtist().equals(artist) && song.getGenre() == genre);

        if (isUnchanged) return SongUpdateResult.UNCHANGED;
        if (isDuplicateSong(title, artist, genre)) return SongUpdateResult.DUPLICATE;

        songRepository.updateSongById(id, title, artist, genre);
        return SongUpdateResult.UPDATED;
    }

    /** 제목, 아티스트, 장르가 모두 같은 노래가 존재하는지 확인한다. */
    private boolean isDuplicateSong(String title, String artist, Genre genre) {
        boolean isSameSong = searchAllSong().stream().anyMatch(song -> song.getTitle().equals(title) && song.getArtist().equals(artist) && song.getGenre() == genre);

        if (isSameSong) {
            return true;
        }

        return false;
    }
}
