package aystzh.com.study.controller;

import aystzh.com.base.core.Result;
import aystzh.com.base.core.ResultGenerator;
import aystzh.com.study.entity.Author;
import aystzh.com.study.service.AuthorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/03/10.
*/
@RestController
@RequestMapping("/author")
public class AuthorController {
    @Resource
    private AuthorService authorService;

    @PostMapping
    public Result add(@RequestBody Author author) {
        authorService.save(author);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        authorService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody Author author) {
        authorService.update(author);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Author author = authorService.findById(id);
        return ResultGenerator.genSuccessResult(author);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Author> list = authorService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
