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
    public String goWrite(Model model, @RequestParam(name = "id", required = false) Long id) {
        if (id != null) {
            model.addAttribute("getView", boardService.getView(id));
        }
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
            map.put("fail", "실패");
            e.printStackTrace();
            return map;
        }
    }

    @GetMapping("/boardList")
    public String getList(Model model) {
        model.addAttribute("boardList", boardService.getList());
        return "board/boardList";
    }

    @GetMapping("/boardView/{id}")
    public String getView(Model model, @PathVariable("id") Long id) {
        boardService.updateHit(id);
        model.addAttribute("boardView", boardService.getView(id));
        System.out.println("result : "+boardService.getView(id));
        return "board/boardView";
    }

    @ResponseBody
    @PostMapping("/boardView/delete")
    public Map<String, String> boardDelete(Long id) {
        Map<String, String> map = new HashMap<>();
        System.out.println("con id : " + id);
        try {
            boardService.delete(id);
            map.put("ok", "삭제 되었습니다.");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("fail", "실패");
            return map;
        }
    }

    @PostMapping("/boardUpdate")
    @ResponseBody
    public Map<String, String> boardUpdate(BoardDto dto) {
        Map<String, String> map = new HashMap<>();
        try {
            boardService.update(dto);
            map.put("ok", "수정 되었습니다.");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("fail", "실패");
            return map;
        }
    }

}
