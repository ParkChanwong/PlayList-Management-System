package com.playlist.model;

// 노래 장르
public enum Genre {
    // 장르 Enum List Start
    POP("팝송"),
    ROCK("락"),
    HIP_HOP("힙합"),
    RNB("R&B"),
    BALLAD("발라드"),
    JAZZ("재즈"),
    CLASSICAL("클래식"),
    EDM("EDM"),
    INDIE("인디"),
    K_POP("K-팝"),
    J_POP("J-팝");
    // 장르 Enum End

    // Enum 인자를 담는 필드 생성
    private final String displayName;

    // 장르 생성자
    Genre(String displayName) {
        this.displayName = displayName;
    }

    // 장르 Getter
    public String getDisplayName() {
        return displayName;
    }
}
