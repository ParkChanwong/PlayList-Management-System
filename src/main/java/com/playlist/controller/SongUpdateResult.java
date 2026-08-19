package com.playlist.controller;

/**
 * 노래 수정 요청의 처리 결과를 나타낸다.
 */
public enum SongUpdateResult {
    UPDATED,   // 수정 완료
    UNCHANGED, // 기존 정보와 동일
    DUPLICATE, // 다른 노래와 중복
}
