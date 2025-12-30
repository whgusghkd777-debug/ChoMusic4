package com.mysite.sbb.music;

import com.mysite.sbb.music.dto.MusicListDto;
import com.mysite.sbb.music.dto.MusicRepository;
import com.mysite.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MusicService {

    private final MusicRepository musicRepository;

    // 음악 리스트 (일반 사용자용 - 최신순)
    public List<MusicListDto> getList() {
        List<Music> musicList = this.musicRepository.findAllByOrderByCreateDateDesc();
        return musicList.stream()
                .map(music -> new MusicListDto(
                    music.getId(), 
                    music.getTitle(), 
                    music.getArtist(), 
                    music.getThumbnailUrl(), 
                    music.getCreateDate(),
                    music.getVoter() != null ? music.getVoter().size() : 0
                ))
                .collect(Collectors.toList());
    }

    // 관리자용 전체 음악 리스트
    public List<Music> getListAdmin() {
        return musicRepository.findAll();
    }

    // 삭제 메서드
    public void delete(Integer id) {
        musicRepository.deleteById(id);
    }

    // 음악 등록
    public void create(String title, String artist, String url, String content, SiteUser author) {
        Music m = new Music();
        m.setTitle(title);
        m.setArtist(artist);
        m.setUrl(url);
        m.setContent(content);
        m.setAuthor(author);
        m.setCreateDate(LocalDateTime.now());

        // 유튜브 URL 있으면 썸네일 자동 추출
        if (url != null && url.contains("v=")) {
            try {
                String videoId = url.split("v=")[1].split("&")[0];
                m.setThumbnailUrl("https://img.youtube.com/vi/" + videoId + "/mqdefault.jpg");
            } catch (Exception e) {
                m.setThumbnailUrl(null);
            }
        }

        this.musicRepository.save(m);
    }

    // 상세 조회
    public Music getMusic(Integer id) {
        return this.musicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Music not found"));
    }

    // 저장 (좋아요 등에서 재사용 가능)
    public void save(Music music) {
        this.musicRepository.save(music);
    }
}