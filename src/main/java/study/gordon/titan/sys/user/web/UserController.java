package study.gordon.titan.sys.user.web;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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

import study.gordon.titan.sys.user.entity.User;
import study.gordon.titan.sys.user.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final int PAGE_SIZE = 5;

    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model,
            ServletRequest request) {

        Page<User> page = userService.findAll(pageNumber, PAGE_SIZE, (Sort)null);
        model.addAttribute("pageResult", page);
        return "user/userList";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("action", "create");
        return "user/userForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(Model model, @ModelAttribute("user") @Valid User user, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (hasError(user, result)) {
            model.addAttribute("action", "create");
            return "user/userForm";
        }
        userService.create(user);
        redirectAttributes.addFlashAttribute("message", "创建成功");
        return "redirect:/user";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("action", "update");
        return "user/userForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("user") @Valid User user, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (hasError(user, result)) {
            model.addAttribute("action", "update");
            return "user/userForm";
        }
        userService.update(user);
        redirectAttributes.addFlashAttribute("message", "更新成功");
        return "redirect:/user";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        userService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/user";
    }

    @ModelAttribute
    public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("user", userService.findById(id));
        }
    }

    private boolean hasError(User user, BindingResult result) {
        Assert.notNull(user);
        return result.hasErrors();
    }
}
