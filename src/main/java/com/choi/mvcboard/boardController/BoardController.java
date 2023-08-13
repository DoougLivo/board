package com.choi.mvcboard.boardController;

import com.choi.mvcboard.boardDomain.BoardDto;
import com.choi.mvcboard.boardService.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/insert")
    @ResponseBody
    public Map<String, String> boardWrite(BoardDto dto) {
        Map<String, String> map = new HashMap<>();
        try {
            boardService.insert(dto);
            map.put("ok", "등록 되었습니다.");
            return map;
        } catch (Exception e) {
            map.put("fail", "fail");
            e.printStackTrace();
            return map;
        }
    }

    @GetMapping("/boardList")
    public String getList(Model model) {
        model.addAttribute("boardList", boardService.getList());
        return "board/boardList";
    }
}
