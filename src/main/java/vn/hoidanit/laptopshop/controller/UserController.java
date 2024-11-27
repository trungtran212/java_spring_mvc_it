package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        // nếu trong file jsp xuất hiện {$trung} nghĩa là nó gọi hàm test
        model.addAttribute("trung", "test");

        List<User> arrUser = this.userService.getAllUsersByEmail("trungmuok123@gmail.com");

        System.out.println(arrUser);

        return "helloController";
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String requestMethodName(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/displayUser";
    }

    @RequestMapping(value = "/admin/user/view/{id}", method = RequestMethod.GET)
    public String getUserDetail(Model model, @PathVariable long id) {
        System.out.println(id);
        User user = this.userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        return "admin/user/userDetail";
    }

    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        // đưa 1 đối tượng newUser rỗng sang form modelAttribute tương ứng để phối hợp
        // với form trả về 1 newUser
        // đầy đủ trong đoạn code bên dưới
        model.addAttribute("newUser", new User()); // Vì chưa có người dùng nên truyền 1 newUser để nó tạo mới
        return "admin/user/create"; // switch to webapp
    }

    @RequestMapping(value = "/admin/user/create1", method = RequestMethod.POST) // action from form
    // ModelAttribute dùng để ánh xạ dữ liệu từ form cũng có modelAttribute tương
    // ứng và trả về User trung
    public String createUser(Model model, @ModelAttribute("newUser") User user) {
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String updateUser(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("newUser", user); // Đã có người dùng rồi nên truyền vào người dùng đó để sửa
        return "admin/user/update"; // switch to webapp
    }

    @PostMapping("/admin/user/update1")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User user) {
        User trung = this.userService.getUserById(user.getId());
        if (trung != null) {
            trung.setAddress(user.getAddress());
            trung.setFullName(user.getFullName());
            trung.setPhone(user.getPhone());
            this.userService.handleSaveUser(trung);
        }
        return "redirect:/admin/user"; // switch to webapp
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUser(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        User user = new User();
        user.setId(id);
        model.addAttribute("newUser", user);
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User trung) {
        this.userService.deleteAUser(trung.getId());
        return "redirect:/admin/user";
    }
}
