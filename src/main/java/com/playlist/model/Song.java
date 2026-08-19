package com.playlist.model;

/**
 * 플레이리스트에 저장되는 노래의 ID, 제목, 아티스트와 장르를 보관한다.
 */
public class Song {
    private int id;
    private String title;
    private String artist;
    private Genre genre;

    /** 빈 노래 객체를 생성한다. */
    public Song() {}

    /** 제목, 아티스트와 장르를 입력받아 노래 객체를 생성한다. */
    public Song(String title, String artist, Genre genre) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }

    /** 노래의 고유 ID를 반환한다. */
    public int getId() {
        return id;
    }

    /** 노래의 고유 ID를 설정한다. */
    public void setId(int id) {
        this.id = id;
    }

    /** 노래 제목을 반환한다. */
    public String getTitle() {
        return title;
    }

    /** 노래 제목을 설정한다. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 아티스트명을 반환한다. */
    public String getArtist() {
        return artist;
    }

    /** 아티스트명을 설정한다. */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /** 노래 장르를 반환한다. */
    public Genre getGenre() {
        return genre;
    }

    /** 노래 장르를 설정한다. */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    /** 노래의 전체 필드 값을 문자열로 반환한다. */
    @Override
    public String toString() {
        return "Song [" +
                "id=" + id +
                ", title=" + title +
                ", artist=" + artist +
                ", genre=" + genre +
                "]";
    }
}
