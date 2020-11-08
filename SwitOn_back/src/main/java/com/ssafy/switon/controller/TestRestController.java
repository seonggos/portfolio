package com.ssafy.switon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Alarm;
import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.Board;
import com.ssafy.switon.dto.Comment;
import com.ssafy.switon.dto.Join;
import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.ResultStrObject;
import com.ssafy.switon.dto.UpperCategory;
import com.ssafy.switon.service.AlarmService;
import com.ssafy.switon.service.ArticleService;
import com.ssafy.switon.service.BoardService;
import com.ssafy.switon.service.CategoryService;
import com.ssafy.switon.service.CommentService;
import com.ssafy.switon.service.JoinService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="ArticleRestController", description="(테스트용) 각종 CRUD 테스트용 컨트롤러... 테스트트트트트트트트")
public class TestRestController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	ArticleService articleService;
	
	@ApiOperation(value = "게시글 리스트 전체 반환", response = List.class)
	@GetMapping("/article/")
	public List<Article> showAll(){
		return articleService.searchAll();
	}
	
	@ApiOperation(value = "게시판 아이디로 게시글 리스트 조회", response = List.class)
	@GetMapping("/article/board/{boardId}")
	public List<Article> showBoardArticles(@PathVariable int boardId){
		return articleService.searchBoardArticles(boardId);
	}
	
	@ApiOperation(value = "유저 아이디로 게시글 리스트 조회, 후에 토큰으로 구현할것", response = List.class)
	@GetMapping("/article/user/{userId}")
	public List<Article> showUserArticles(@PathVariable int userId){
		return articleService.searchUserArticles(userId);
	}
	
	@ApiOperation(value = "유저 아이디로 게시글 조회, 후에 토큰으로 구현할 것", response = Article.class)
	@GetMapping("/article/{id}")
	public Article showArticle(@PathVariable int id){
		return articleService.search(id);
	}
	@ApiOperation(value = "게시글 수정, 후에 토큰으로 구현할 것")
	@PostMapping("/article/update")
	public Object editArticle(Article article) {
		if(articleService.modify(article)) {
			return new ResponseEntity<>(new ResultStrObject(article.getId()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "게시글 삭제, 후에 토큰으로 구현할 것")
	@PostMapping("/article/delete")
	public Object deleteArticle(int id) {
		if(articleService.delete(id)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@ApiOperation(value = "(테스트용) 게시판 리스트를 반환", response = List.class)
	@GetMapping("/board/")
	public List<Board> showAllBoards(){
		return boardService.searchAll();
	}
	
	@ApiOperation(value = "스터디의 게시판 리스트를 반환 테스트", response = List.class)
	@GetMapping("/board/{studyId}")
	public List<Board> showStudyBoards(@PathVariable("studyId") int studyId){
		return boardService.searchStudyBoards(studyId);
	}
	
	@ApiOperation(value = "(테스트용) 게시판을 생성")
	@PostMapping("/board/create")
	public Object create(Board board, HttpServletRequest request) {
		if(boardService.create(board)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "(테스트용) 게시판을 삭제")
	@GetMapping("/board/{id}/delete")
	public Object delete(@PathVariable("id") int id, HttpServletRequest request) {
		if(boardService.delete(id)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Autowired
	CategoryService categoryService;
	
	@ApiOperation(value = "대분류 정보 조회한다", response = List.class)
	@GetMapping("/category/up")
	public List<UpperCategory> searchUp() {
		return categoryService.searchUp();
	}
	
	@ApiOperation(value = "소분류 정보 조회한다", response = List.class)
	@GetMapping("/category/low")
	public List<LowerCategory> searchLow() {
		return categoryService.searchLow();
	}
	
	@ApiOperation(value = "대분류에 해당하는 소분류 조회한다", response = List.class)
	@GetMapping("/category/up_low/{up_id}")
	public List<LowerCategory> searchUp_Low(@PathVariable("up_id") int uppercategory_id) {
		return categoryService.searchUp_Low(uppercategory_id);
	}
	
	@Autowired
	JoinService joinService;
	
	@ApiOperation(value = "소모임 가입 정보 전체 조회한다", response = List.class)
	@GetMapping("/join/list")
	public List<Join> searchAllJoins() {
		return joinService.searchAll();
	}
	
	@ApiOperation(value = "소모임 가입 정보 상세 조회한다", response = Join.class)
	@GetMapping("/join/{id}")
	public Join searchJoin(@PathVariable("id") int id) {
		return joinService.search(id);
	}
	@Autowired
	CommentService commentService;
	
	@ApiOperation(value = "댓글 전체 조회한다", response = List.class)
	@GetMapping("/comment/list")
	public List<Comment> searchAllComments() {
		return commentService.searchAll();
	}
	
	@ApiOperation(value = "댓글 상세 조회한다", response = Comment.class)
	@GetMapping("/comment/{id}")
	public Comment searchComment(@PathVariable("id") int id) {
		return commentService.search(id);
	}
	
	@ApiOperation(value = "댓글 등록한다", response = String.class)
	@PostMapping("/comment/write")
	public String writeComment(Comment comment) {
		if(commentService.create(comment)) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@ApiOperation(value = "댓글 삭제한다", response = String.class)
	@GetMapping("/comment/delete/{id}")
	public String deleteComment(@PathVariable("id") int id) {
		if(commentService.delete(id)) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@ApiOperation(value = "댓글 수정한다", response = String.class)
	@PostMapping("/comment/update")
	public String updateComment(Comment comment) {
		if(commentService.update(comment)) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@Autowired
	AlarmService alarmService;
	
	@ApiOperation(value = "(테스트용) 알람 전체 목록을 반환한다", response = List.class)
	@GetMapping("/test/alarm/all")
	public List<Alarm> searchAll(){
		return alarmService.searchAll();
	}

}
