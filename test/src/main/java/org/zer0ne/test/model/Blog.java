package org.zer0ne.test.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "news")
    private String news;
    @Column(name = "author")
    private int author;

    public Blog(String news, int id, int author) {
        this.news = news;
        this.id = id;
        this.author = author;
    }

    public Blog() {

    }
    public void setNews(String news) {
        this.news = news;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setAuthor(int author) {
        this.author = author;
    }
    public int getAuthor() {
        return author;
    }
    public String getNews() {
        return news;
    }
    public int getId() {
        return id;
    }
}
