package org.zer0ne.test.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zer0ne.test.model.Blog;
import org.zer0ne.test.repository.BlogRepository;

import java.util.List;
import java.util.Optional;


@Component
public class BlogDAO {
    @Autowired
    private BlogRepository blogRepository;
    public Blog createBlog(Blog blog){
        return blogRepository.save(blog);
    }
    public Blog getBlog(int id){
        return blogRepository.findById(id).orElse(null);
    }
    public List<Blog> getBlogs(){
        return blogRepository.findAll();
    }
    public Blog updateBlog(Blog blog){
        Blog oldBlog = null;
        Optional<Blog> optionalBlog = blogRepository.findById(blog.getId());
        if(optionalBlog.isPresent()){
            oldBlog = optionalBlog.get();
            oldBlog.setNews(blog.getNews());
            blogRepository.save(oldBlog);
        }
        else{
            return new Blog();
        }
        return oldBlog;
    }
    public Blog setAuthor(Blog blog, int id){
        blog.setAuthor(id);
        return blog;
    }
    public String deleteBlogById(int id) {
        blogRepository.deleteById(id);
        return "Blog got deleted";
    }
}
