package com.choi.mvcboard.boardController;

import com.choi.mvcboard.boardDomain.BoardDto;
import com.choi.mvcboard.boardService.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/")
    public String getHome() {
        return "board/home";
    }

    @GetMapping("/boardWrite")
    public String goWrite() {
        return "board/boardWrite";
    }

    @PutMapping("/write/insert")
    public void boardWrite(BoardDto dto) {
        boardService.insert(dto);
    }

    @GetMapping("/boardList")
    public String getList(Model model) {
        model.addAttribute("boardList", boardService.getList());
        return "board/boardList";
    }
}
