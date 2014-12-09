package study.gordon.titan.showcase.simple.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import study.gordon.titan.showcase.simple.entity.SimpleEntity;
import study.gordon.titan.showcase.simple.service.SimpleEntityService;

@Controller
@RequestMapping(value = "/simple")
public class SimpleEntityController {

    @Resource
    private SimpleEntityService simpleEntityService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, ServletRequest request) {
        List<SimpleEntity> simpleEntities = simpleEntityService.getAll();
        model.addAttribute("simpleEntities", simpleEntities);
        return "simple/simpleList";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("simple", new SimpleEntity());
        model.addAttribute("action", "create");
        return "simple/simpleForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(SimpleEntity simpleEntity, RedirectAttributes redirectAttributes) {
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
    public String update(@ModelAttribute("simple") SimpleEntity simpleEntity, RedirectAttributes redirectAttributes) {
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
    public void getTask(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("simple", simpleEntityService.findById(id));
        }
    }

}
