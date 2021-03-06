package com.briup.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Article;
import com.briup.bean.Chapter;
import com.briup.bean.Comment;
import com.briup.bean.Reader;
import com.briup.dao.ArticleDao;
import com.briup.dao.ChapterDao;
import com.briup.dao.CommentDao;
import com.briup.dao.ReaderDao;
import com.briup.service.IArticleService;
import com.briup.service.IChapterService;
import com.briup.service.ICommentService;
import com.briup.service.IReaderService;
@Service
public class ReaderServiceImpl implements IReaderService{
	@Autowired
	private ReaderDao readerDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ChapterDao chapterDao;
	
	@Override
	public List<Reader> findAll() {
		List<Reader> readerList = readerDao.findAll();
		return readerList;
	}

	@Override
	public Reader findById(Integer id) throws Exception {
		Optional<Reader> opt = readerDao.findById(id);
		Reader reader = opt.isPresent()?opt.get():null;
		if(reader!=null)
			return reader;
		else 
			throw new Exception("该id在数据库中不存在！");
		
	}

	@Override
	public void saveOrUpdate(Reader reader) throws Exception {
		if(reader!=null) {
			Integer id = reader.getId();
			if(id==null) {
				readerDao.save(reader);
			}else {
				Reader reader_db = readerDao.findById(id).get();
				String username = reader.getUsername();
				String password = reader.getPassword();
				String email = reader.getEmail();
				if(username!=null)
					reader_db.setUsername(username);
				if(password!=null)
					reader_db.setPassword(password);
				if(email!=null)
					reader_db.setEmail(email);
				readerDao.save(reader_db);
				
			}
		}else
			throw new Exception("参数为空！");
		
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<Reader> opt = readerDao.findById(id);
		Reader reader = opt.isPresent()?opt.get():null;
		if(reader!=null) {
			readerDao.deleteById(id);
		}else
			throw new Exception("该id在数据库中不存在！");
		
	}

	@Override
	public void addToBookShelf(Integer reader_id, Integer article_id) {
		Reader reader = readerDao.findById(reader_id).get();
		Article article = articleDao.findById(article_id).get();
		List<Article> articleList = reader.getArticles();
		articleList.add(article);
		readerDao.save(reader);
	}

	@Override
	public void addBookMark(Integer reader_id, Integer chapter_id) {
		Reader reader = readerDao.findById(reader_id).get();
		Chapter chapter = chapterDao.findById(chapter_id).get();
		List<Chapter> chapterList = reader.getChapters();;
		chapterList.add(chapter);
		reader.setChapters(chapterList);
		readerDao.save(reader);
	}

	@Override
	public void commentaryArticle(Integer reader_id, Comment comment) {
		//根据id查出读者
		Reader reader = readerDao.findById(reader_id).get();
		//保存评论
		comment.setPublishDate(new Date());
		commentDao.save(comment);
		//添加关系
		List<Comment> commentList = reader.getComments();
		commentList.add(comment);
		reader.setComments(commentList);
		readerDao.save(reader);
		
	}
	 
	
	
	
}
