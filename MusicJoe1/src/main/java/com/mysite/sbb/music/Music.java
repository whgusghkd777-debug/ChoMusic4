package com.mysite.sbb.music;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String title;

    @Column(length = 100)
    private String artist;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String url;

    private String filePath;

    private String thumbnailUrl;

    private LocalDateTime createDate;

    @ManyToOne
    private SiteUser author;

    @OneToMany(mappedBy = "music", cascade = CascadeType.REMOVE)
    private List<Answer> answerList = new ArrayList<>();

    @ManyToMany
    private Set<SiteUser> voter = new HashSet<>();  // ★ 여기 초기화

    // getter/setter (직접 작성 중이니 그대로)
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public LocalDateTime getCreateDate() { return createDate; }
    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }

    public SiteUser getAuthor() { return author; }
    public void setAuthor(SiteUser author) { this.author = author; }

    public List<Answer> getAnswerList() { return answerList; }
    public void setAnswerList(List<Answer> answerList) { this.answerList = answerList; }

    public Set<SiteUser> getVoter() { return voter; }
    public void setVoter(Set<SiteUser> voter) { this.voter = voter; }
}