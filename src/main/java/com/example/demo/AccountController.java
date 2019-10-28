import com.example.demo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Controller
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private LoginService loginService;

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/")
    public String index(Model model) {
        return "login_form";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLoginForm(){
        return "login_form";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String doLogin(@ModelAttribute(name = "login_Form") User user, Model model)
    {

        if(loginService.findUser(user.getUsername(),user.getPassword())!= null)
        {
            return "home";
        }
        else
        {
            model.addAttribute("invalidCredentials",true);
            return "login_form";
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegisterForm(Model model)
    {
        model.addAttribute("user",new User());

        return "registerform";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@Valid User userForm, BindingResult result){

        if(result.hasErrors()){
            return "register_form";
        }

        String firstname = userForm.getFirstName();
        String lastname = userForm.getLastName();
        String username = userForm.getUsername();
        String password = userForm.getPassword();


        loginService.registerUser(userForm);
        return "login_form";
    }

    ///
//    @RequestMapping("/")
//    public String index(Model model) {
//        model.addAttribute("customers", customerRepository.findAll());
//        return "homePg";
//    }

//    @RequestMapping("/customerlist")
//    public String departmentList(Model model) {
//        model.addAttribute("customers", customerRepository.findAll());
//        return "customerlist";
//
//    }
//
//    @PostMapping("/processcustomer")
//    public String processForm1(@Valid Customer customer,
//                               BindingResult result) {
//        if (result.hasErrors()) {
//            return "addNewcust";
//        }
//        customerRepository.save(customer);
//        return "redirect:/customerlist";
////    }
//    @RequestMapping("/balancelist")
//    public String employeeList(Model model){
//        model.addAttribute("accounts", accountRepository.findAll());
//        return "balancelist";
//    }
//
//    @GetMapping("/addbalance")
//    public String employeeForm(Model model){
//        model.addAttribute("account", new Account());
//        model.addAttribute("user", userRepository.findAll());
//        return "balanceform";
//    }
//    @PostMapping("/processbalance")
//    public String processForm2(@Valid Account account,
//                               BindingResult result){
//        if (result.hasErrors()){
//            return "balanceform";
//        }
//        accountRepository.save(account);
//        return "redirect:/balancelist";
//    }
//    @RequestMapping("/detailbalance/{id}")
//    public String showPerson(@PathVariable("id") int id, Model model)
//    {model.addAttribute("account", accountRepository.findAll());
//        return "showbalance";
//    }
//    @RequestMapping("/updatebalance/{id}")
//    public String updatePerson(@PathVariable("id") int id,Model model){
//        model.addAttribute("account", accountRepository.findById(id).get());
//        return "balanceform";
//    }
//    @RequestMapping("/deletebalance/{id}")
//    public String delPerson(@PathVariable("id") int id){
//        accountRepository.deleteById(id);
//        return "redirect:/";
//    }
//    @RequestMapping("/detailuser/{id}")
//    public String showPet(@PathVariable("id") int id, Model model)
//    {model.addAttribute("user", userRepository.findById(id).get());
//        return "showusers";
//    }
//    @RequestMapping("/updateuser/{id}")
//    public String updatePet(@PathVariable("id") int id,Model model){
//        model.addAttribute("account", accountRepository.findById(id).get());
//        model.addAttribute("users", userRepository.findAll());
//        return "registerform";
//    }
//    @RequestMapping("/deleteuser/{id}")
//    public String delPet(@PathVariable("id") int id){
//        userRepository.deleteById(id);
//        return "redirect:/";
//    }

    /////
//    }
//    @GetMapping("/newcustomer")
//    public String newcustomer(Model model) {
//        Customer customer = new Customer();
//        model.addAttribute("customer", customer);
//        return "addNewCust";
//    }
//
//    @PostMapping("/processNewCust")
//    public String processNewCust(@Valid Customer customer, BindingResult result) {
//        if (result.hasErrors()) {
//            return "addNewCust";
//        }
//
//        customerRepository.save(customer);
//        return "redirect:/homePg";
//    }

    @GetMapping("/addAccount/{id}")
    public String addAccount(@PathVariable("id") long id, Model model) {
        // id is customer id
        model.addAttribute("user", UserRepository.findById(id));

        Account account = new Account();
        model.addAttribute("account", account);

        return "addAccount";
    }

    @PostMapping("/processAccount/{id}")
    public String processAccount(@PathVariable("id") long id,
                                 @Valid Account account,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "addAccount";
        }

        User user = userRepository.findById(id);
        account.setUser(user);

        Set<Account> allaccounts = user.getAccounts();
        allaccounts.add(account);

        user.setAccounts(allaccounts);
        accountRepository.save(account);
        UserRepository.save(user);

        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detailAccount(@PathVariable("id") long id, Model model) {
        // id is customer id
        model.addAttribute("customer", userRepository.findById(id));

        return "detailAcctPg";
    }


    /*
    * @RequestMapping(value = "/{orderId}/items/{itemId}", method=RequestMethod.GET)
  @ResponseBody
  public String getItem(@PathVariable final String orderId, @PathVariable final String itemId) {
    return "Order ID: " + orderId + ", Item ID: " + itemId;
  }
  * /

    @GetMapping("/transaction/{id}")
    public String depoistAccount(@PathVariable("id") long id, Model model) {

     */

    @GetMapping("/transaction/{id}/{option}")
    public String depoistAccount(@PathVariable("id") long id,
                                 @PathVariable("option") long option,
                                 Model model) {
        // id is account id
        Account account = accountRepository.findById(id);
        Account testacct = new Account();

        model.addAttribute("account", account);

        testacct.setAcctno(account.getAcctno());
        testacct.setBalance(account.getBalance());
//        testacct.setChecking(account.isChecking());
        testacct.setId(account.getId());
        testacct.setUser(null);
        model.addAttribute("testacct", testacct);

        return "validateAcct";
    }

    @PostMapping("/validateAcct/{id}/{option}")  // id is account's id
    public String validateAccount(@PathVariable("id") long id,
                                  @PathVariable("option") long option,
                                  @Valid Account testacct, Model model,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "validateAcct";
        }

        String retstr;
        // check for password match
        Account realacct = accountRepository.findById(id);
        if (realacct.getPasswd().equalsIgnoreCase(testacct.getPasswd())) {
            // password matched

            if (option == 2) {
                retstr = "redirect:/statement/" + id;
            }
            else if (option == 3) {//  check for delete account
                retstr = "redirect:/deleteAcct/" + id;
            }
            else {
                model.addAttribute("account", realacct);

                Transaction transaction = new Transaction();
                transaction.setAcctno(realacct.getAcctno());
                transaction.setBalance(realacct.getBalance());
                model.addAttribute("transaction", transaction);
                retstr = "deposit";
            }
        } else
            retstr = "redirect:/";

        return retstr;

    }

    @PostMapping("/processtransaction/{id}")  // id is account's id
    public String processDeposit(@PathVariable("id") long id,
                                 @Valid Transaction transaction,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "deposit";
        }

        Account account = accountRepository.findById(id);
        double bal = account.getBalance();
        double amt = transaction.getAmount();
        if (transaction.isDeposit())
            bal += amt;
        else
            bal -= amt;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        transaction.setDate(dateFormat.format(date)); //2013/10/15 16:16:39
        transaction.setAccount(account);
        transaction.setBalance(bal);
        transactionRepository.save(transaction);

        account.setBalance(bal);
        accountRepository.save(account);

        return "redirect:/";

    }

    @GetMapping("/statement/{id}")
    public String listStmt(@PathVariable("id") long id, Model model) {
        // id is account id
        Account account = accountRepository.findById(id);

        Set<Transaction> transactions = transactionRepository.findAllByAcctno(account.getAcctno());
        model.addAttribute("transactions", transactions);
        return "listStatment";
    }

    @GetMapping("/updateuser/{id}")
    public String updateCustomer(@PathVariable("id") long id, Model model) {
        // id is customer id
        model.addAttribute("user", userRepository.findById(id));
        return "updateuser";
    }


    @GetMapping("/detailuser/{id}")
    public String detailCustomer(@PathVariable("id") long id, Model model) {
        // id is customer id
        model.addAttribute("user", userRepository.findById(id));
        return "detailuser";
    }

    @GetMapping("/deletecust/{id}")
    public String delCustomer(@PathVariable("id") long id, Model model) {
        // id is customer id
        String result;
        User user = userRepository.findById(id);
        if (user.accounts.size() == 0) {
            userRepository.delete(user);
            result = "redirect:/";
        }
        else {
            result = "redirect:/detail/" + id;
        }

        return result;
    }

    @GetMapping("/deleteAccount/{id}")
    public String delAccount(@PathVariable("id") long id, Model model) {
        // id is account id
        Account acct = accountRepository.findById(id);

        User user = acct.getUser();
        user.accounts.remove(acct);

        accountRepository.delete(acct);
        return "redirect:/";

    }

}
