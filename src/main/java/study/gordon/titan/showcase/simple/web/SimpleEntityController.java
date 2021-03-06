package study.gordon.titan.showcase.simple.web;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import study.gordon.titan.common.entity.QueryResult;
import study.gordon.titan.showcase.simple.entity.SimpleEntity;
import study.gordon.titan.showcase.simple.service.SimpleEntityService;

@Controller
@RequestMapping(value = "/simple")
public class SimpleEntityController {

    private static final int PAGE_SIZE = 5;

    @Resource
    private SimpleEntityService simpleEntityService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model,
            ServletRequest request) {

        QueryResult<SimpleEntity> qr = simpleEntityService.getScrollData(PAGE_SIZE * (pageNumber - 1), PAGE_SIZE);
        qr.setPage(pageNumber);
        qr.setPageSize(PAGE_SIZE);
        model.addAttribute("queryResult", qr);
        return "simple/simpleList";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("simple", new SimpleEntity());
        model.addAttribute("action", "create");
        return "simple/simpleForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(Model model, @ModelAttribute("simple") @Valid SimpleEntity simpleEntity, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (hasError(simpleEntity, result)) {
            model.addAttribute("action", "create");
            return "simple/simpleForm";
        }
        simpleEntityService.create(simpleEntity);
        redirectAttributes.addFlashAttribute("message", "创建成功");
        return "redirect:/simple";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("simple", simpleEntityService.findById(id));
        model.addAttribute("action", "update");
        return "simple/simpleForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("simple") @Valid SimpleEntity simpleEntity, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (hasError(simpleEntity, result)) {
            model.addAttribute("action", "update");
            return "simple/simpleForm";
        }
        simpleEntityService.update(simpleEntity);
        redirectAttributes.addFlashAttribute("message", "更新成功");
        return "redirect:/simple";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        simpleEntityService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/simple";
    }

    @ModelAttribute
    public void getSimpleEntity(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("simple", simpleEntityService.findById(id));
        }
    }

    private boolean hasError(SimpleEntity simpleEntity, BindingResult result) {
        Assert.notNull(simpleEntity);
        return result.hasErrors();
    }
}
