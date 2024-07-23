package com.chainsys.creditcard.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chainsys.creditcard.dao.AccountRecordsDAO;
import com.chainsys.creditcard.dao.ApprovalRecordsDAO;
import com.chainsys.creditcard.dao.CardRecordsDAO;
import com.chainsys.creditcard.dao.EmploymentRecordsDAO;
import com.chainsys.creditcard.dao.MailImpl;
import com.chainsys.creditcard.dao.NumberGenerationDAO;
import com.chainsys.creditcard.dao.NumberGenerationImpl;
import com.chainsys.creditcard.dao.TransactionRecordsDAO;
import com.chainsys.creditcard.dao.UserRecordsDAO;
import com.chainsys.creditcard.model.Account;
import com.chainsys.creditcard.model.CreditCard;
import com.chainsys.creditcard.model.Employment;
import com.chainsys.creditcard.model.Transactions;
import com.chainsys.creditcard.model.User;
import com.chainsys.creditcard.validation.Validation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CreditCardController {

	@Autowired
	UserRecordsDAO userRecordsDAO;
	@Autowired
	AccountRecordsDAO accountRecordsDAO;
	@Autowired
	EmploymentRecordsDAO employmentRecordsDAO;
	@Autowired
	NumberGenerationDAO numberGenerationDAO;
	@Autowired
	CardRecordsDAO cardRecordsDAO;
	@Autowired
	ApprovalRecordsDAO approvalRecordsDAO;
	@Autowired
	TransactionRecordsDAO transactionRecordsDAO;
	@Autowired
	User user;
	@Autowired
	Account account;
	@Autowired
	CreditCard creditCard;
	@Autowired
	Employment employment;
	@Autowired
	Transactions transactions;
	@Autowired
	Validation validation;

	@RequestMapping("/home")
	public String mainPage() {

		return "mainPage";

	}

	@RequestMapping("/signPage")
	public String signupPage() {

		System.out.println("Im HERE");
		return "signup";

	}

	@RequestMapping("/logPage")
	public String logPage() {

		System.out.println("Im HERE in login");
		return "login";

	}

	@RequestMapping("/pinPage")
	public String pinPage() {

		System.out.println("Im HERE in Pin");
		return "setPin";

	}

	@RequestMapping("/cardPage")
	public String cardPage() {

		System.out.println("Im HERE in card");
		return "cardPage";

	}

	@RequestMapping("/cardFormPage")
	public String cardFormPage() {

		System.out.println("Im HERE in cardFormPage");
		return "cardForms";

	}

	@RequestMapping("/cibilPage")
	public String cibilPage() {

		System.out.println("Im HERE in cibil");
		return "cibil";

	}

	@RequestMapping("/shopPage")
	public String shopPage() {

		System.out.println("Im HERE in shop");
		return "shop"; //

	}

	@RequestMapping("/creditPointsPage")
	public String creditPointsPage() {

//		User list = userRecordsDAO.readProfile(user.getMail());
//
//		session.setAttribute("values", list);
		System.out.println("Im HERE in creditPoints");
		return "creditPoints";

	}

	@RequestMapping("/statementPage")
	public String statementPage() {

		System.out.println("Im HERE in statement");
		return "statementForm";

	}

	@RequestMapping("/customerDetailsPage")
	public String customerDetailsPage(Model model) {

		String aadhaarProofImage = null;
		String panProofImage = null;
		List<User> info = userRecordsDAO.read();
		for (User userDetails : info) {
			byte[] aadhaarProof = userDetails.getAadhaarProof();
			byte[] panProof = userDetails.getPanProof();

			aadhaarProofImage = Base64.getEncoder().encodeToString(aadhaarProof);
			panProofImage = Base64.getEncoder().encodeToString(panProof);

		}

		model.addAttribute("details", userRecordsDAO.read());
		model.addAttribute("aadhaarImage", aadhaarProofImage);
		model.addAttribute("panImage", panProofImage);

		System.out.println("Im HERE in customerDetails");
		return "customerDetails";

	}

	@RequestMapping("/cardApprovalPage")
	public String cardApprovalPage(Model model, HttpSession session) {

		String incomeProofImage = null;

		List<CreditCard> info = cardRecordsDAO.read();

		for (CreditCard cardDetails : info) {
			creditCard.setId(cardDetails.getId());
			List<Employment> list = employmentRecordsDAO.read(creditCard);

			for (Employment values : list) {
				byte[] incomeProof = values.getIncomeProof();
				incomeProofImage = Base64.getEncoder().encodeToString(incomeProof);
			}
		}
		model.addAttribute("cardRecords", info);
		model.addAttribute("incomeProof", incomeProofImage);

		System.out.println("Im HERE in cardApprovalPage");
		return "creditCardApproval";

	}

	@PostMapping("/cardapproval")

	public String cardApproval(@RequestParam("action") String action, @RequestParam("id") int id,
			@RequestParam("card") String cardNumber) throws MessagingException {

		switch (action) {

		case ("accept"):

//		int id = Integer.parseInt(request.getParameter("id"));
//	String cardNumber=request.getParameter("card");

			creditCard.setId(id);
			creditCard.setCardNumber(cardNumber);
			approvalRecordsDAO.approve(creditCard);

			String mail = userRecordsDAO.readMail(id);
			String message = "Your Credit Card:" + cardNumber + "has been Approved";

			MailImpl.setProperties();
			MailImpl.setMailBody(mail, message);

			System.out.println(" Accepted mail");

			return "redirect:/cardApprovalPage";

		case ("reject"):

			creditCard.setId(id);
			creditCard.setCardNumber(cardNumber);

			approvalRecordsDAO.reject(creditCard);

			String retrivedmail = userRecordsDAO.readMail(id);
			String mailMessage = "Your Credit Card:" + cardNumber + "has been Rejected";

			MailImpl.setProperties();
			MailImpl.setMailBody(retrivedmail, mailMessage);

			System.out.println("Rejected Mail");

			return "redirect:/cardApprovalPage";

		default:

			break;

		}

		return "  ";

	}

	@PostMapping("/signup")
	public String signup(@RequestParam("fName") String fName, @RequestParam("lName") String lName,
			@RequestParam("DOB") String DOB, @RequestParam("aadhaar") String aadhaar,
			@RequestParam("aadhaarProof") MultipartFile aadhaarProof, @RequestParam("pan") String pan,
			@RequestParam("panProof") MultipartFile panProof, @RequestParam("mail") String mail,
			@RequestParam("ph") String phone, @RequestParam("pass") String pass) throws IOException, ParseException {

		if (validation.fName(fName) == true) {
			user.setfName(fName);
		}

		if (validation.lName(lName) == true) {
			user.setlName(lName);
		}
		System.out.println(DOB);

		user.setDob(validation.validDOB(DOB));

		if (validation.aadhaar(aadhaar) == true) {

			user.setAadhaar(aadhaar);

		}

		if (validation.pan(pan) == true) {

			user.setPan(pan);

		}
		if (validation.mail(mail) == true) {
			user.setMail(mail);

		}

		if (validation.phone(phone) == true) {

			user.setPhone(phone);

		}

		if (validation.password(pass) == true) {

			user.setPassword(pass);

		}
		byte[] image = null;
		image = aadhaarProof.getBytes();
		user.setAadhaarProof(image);

		image = panProof.getBytes();
		user.setPanProof(image);

		try {
		userRecordsDAO.insert(user);
		}catch(Exception e) {
			System.out.println(e);
			
			return "signup";
			
		}
		
		
		
		

		user.setCustomerID(userRecordsDAO.readId(user));

		 
		account.setAccountNumber(numberGenerationDAO.accountNumber());
		account.setIfsc(numberGenerationDAO.ifsc());
		try {
		accountRecordsDAO.insert(user, account);
		}catch(Exception e) {
			System.out.println(e);
			account.setAccountNumber(numberGenerationDAO.accountNumber());
			account.setIfsc(numberGenerationDAO.ifsc());

		}
		System.out.println(account.getAccountNumber());

		return "mainPage";

	}

	@PostMapping("/Login")

	public String profile(@RequestParam("mail") String mail, @RequestParam("pass") String pass, HttpSession session,
			Model model) {

		user.setMail(mail);
		user.setPassword(pass);

		if (userRecordsDAO.check(user) == true) {

			if (mail.endsWith("@admin.com")) {

				return "adminPage";

			} else {

				User list = userRecordsDAO.readProfile(user.getMail());

				session.setAttribute("values", list);

				model.addAttribute("list", list);

				return "customerProfile";

			}
		} else {

			return "login";
		}

	}

	@PostMapping("/logout")

	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		return "mainPage";

	}

	@PostMapping("/accountDetails")

	public String account(HttpSession session, Model model) {
		User values = (User) session.getAttribute("values");
		System.out.println("Hello daiii");

		Account list = accountRecordsDAO.read(account, values.getCustomerID());

		model.addAttribute("account", list);

		return "accountDetails";

	}

	@PostMapping("/cibil")

	public String cibil(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {

		HttpSession sess = request.getSession();

		User values = (User) session.getAttribute("values");

		user.setCustomerID(values.getCustomerID());

		Random random = new Random();

		System.out.println("boolean" + accountRecordsDAO.checkCibil(user.getCustomerID()));
		if (accountRecordsDAO.checkCibil(user.getCustomerID()) == false) {
			int cibilScore = random.nextInt(600, 900);
			account.setCibil(cibilScore);
			accountRecordsDAO.insertCibil(user, account);

			model.addAttribute("cibil", cibilScore);
			return "cibilScore";

		}

		else {
			System.out.println("old");

			account.setCibil(accountRecordsDAO.readCibil(user));
			model.addAttribute("cibil", account.getCibil());

			return "cibilScore";

		}
	}

	@PostMapping("/cardApplication")

	public String cardApplication(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		System.out.println("ahahahah In /cardApplication");
		HttpSession sess = request.getSession();
		User values = (User) sess.getAttribute("values");

		values.getCustomerID();

		System.out.println(accountRecordsDAO.checkCibil(values.getCustomerID()));
		System.out.println(employmentRecordsDAO.checkEmploymentDetails(values.getCustomerID()));

		if (employmentRecordsDAO.checkEmploymentDetails(values.getCustomerID()) == false) {

			if (accountRecordsDAO.checkCibil(values.getCustomerID()) == true) {

				return "cardForms";

			} else {
				System.out.println("check cibil score first");

				// sweet alert have to implement

				return "customerProfile";
			}

		} else {

			System.out.println("count exceeded");
			return "redirect:/cardCategory";

		}
	}

	@PostMapping("/occupation")

	public String occupation(@RequestParam("occupation") String occupation,
			@RequestParam("companyName") String companyName, @RequestParam("designation") String designation,
			@RequestParam("annualIncome") Long annualIncome, @RequestParam("incomeProof") MultipartFile incomeProof,
			HttpSession session, HttpServletRequest request, Model model) throws IOException {

		HttpSession sess = request.getSession();
		String fName = null;
		String lName = null;
		String accountNumber = null;
		User values = (User) session.getAttribute("values");

		fName = values.getfName();
		lName = values.getlName();

		user.setMail(values.getMail());
//			user.setPassword(display.getPassword());

		System.out.println("Name in occupation" + user.getMail());
//		System.out.println("Pass in occupation"+user.getPassword());

		creditCard.setHolderName(fName + " " + lName);

		employment.setOccupation(occupation);

		employment.setCompanyname(companyName);
		employment.setDesignation(designation);
		employment.setIncome(annualIncome);

		byte[] image = null;
		image = incomeProof.getBytes();
		employment.setIncomeProof(image);

		int id = userRecordsDAO.readId(user); // to get customer id

		Account list = accountRecordsDAO.read(account, id); // to get account number

		accountNumber = list.getAccountNumber();

		account.setAccountNumber(accountNumber);
		System.out.println("accountNumber in occupation" + accountNumber);
		System.out.println("accountNumber in occupation" + account.getAccountNumber());
		employmentRecordsDAO.insert(employment, user);

		return "redirect:/cardCategory";
	}

	@GetMapping("/cardCategory")

	public String cardCategory(HttpSession session, Model model) {

		User values = (User) session.getAttribute("values");

		user.setCustomerID(values.getCustomerID());

		int cibil = accountRecordsDAO.readCibil(user);// to get cibil score
		Long annualIncome = employmentRecordsDAO.readAnnualIncome(values.getCustomerID());

		if (annualIncome >= 200000 && annualIncome < 400000 && cibil >= 650) {
			// silver

			System.out.println("Silver card in controller");
			creditCard.setCardNumber(numberGenerationDAO.rupayCreditCardNumber());
			creditCard.setCvvNumber(numberGenerationDAO.ccvNumber());

			YearMonth ym = YearMonth.now();
			String date = ym.toString();
			creditCard.setCardAppliedDate(date);

			String valid = ym.plusYears(3).toString();
			creditCard.setValidity(valid);
			creditCard.setCardType("Silver");

			cardRecordsDAO.insert(creditCard, user, account);

			model.addAttribute("preview", numberGenerationDAO.splitCardNumber(creditCard.getCardNumber()));
			model.addAttribute("creditCard", creditCard);
			return "previewSilver";

		} else if (annualIncome >= 400000 && annualIncome < 600000 && cibil >= 700) {
			// gold card

			System.out.println("Gold card in controller");

			creditCard.setCardNumber(numberGenerationDAO.pulseCreditCardNumber());
			creditCard.setCvvNumber(numberGenerationDAO.ccvNumber());

			YearMonth ym = YearMonth.now();
			String date = ym.toString();
			creditCard.setCardAppliedDate(date);

			String valid = ym.plusYears(3).toString();
			creditCard.setValidity(valid);
			creditCard.setCardType("Gold");

			cardRecordsDAO.insert(creditCard, user, account);
			model.addAttribute("preview", numberGenerationDAO.splitCardNumber(creditCard.getCardNumber()));
			model.addAttribute("creditCard", creditCard);
			return "previewGold";
		} else if (annualIncome >= 600000 && annualIncome < 800000 && cibil >= 800) {
			// platinum card

			System.out.println("Platinum card in controller");

			creditCard.setCardNumber(numberGenerationDAO.visaCreditCardNumber());
			creditCard.setCvvNumber(numberGenerationDAO.ccvNumber());

			YearMonth ym = YearMonth.now();
			String date = ym.toString();
			creditCard.setCardAppliedDate(date);

			String valid = ym.plusYears(4).toString();
			creditCard.setValidity(valid);
			creditCard.setCardType("Platinum");

			cardRecordsDAO.insert(creditCard, user, account);
			model.addAttribute("preview", numberGenerationDAO.splitCardNumber(creditCard.getCardNumber()));
			model.addAttribute("creditCard", creditCard);
			return "previewPlatinum";

		} else if (annualIncome > 800000 && cibil >= 850) {
//				//elite

			System.out.println("Elite card in controller");

			creditCard.setCardNumber(numberGenerationDAO.masterCreditCardNumber());
			creditCard.setCvvNumber(numberGenerationDAO.ccvNumber());

			YearMonth ym = YearMonth.now();
			String date = ym.toString();
			creditCard.setCardAppliedDate(date);

			String valid = ym.plusYears(5).toString();
			creditCard.setValidity(valid);
			creditCard.setCardType("Elite");

			cardRecordsDAO.insert(creditCard, user, account);
			model.addAttribute("preview", numberGenerationDAO.splitCardNumber(creditCard.getCardNumber()));
			model.addAttribute("creditCard", creditCard);
			return "previewElite";
		} else {
			// sorry U are not eligible
		}

		return "cardForms";
	}

	@PostMapping("/setPin")

	public String setPin(@RequestParam("cardNumber") String cardNumber, @RequestParam("pin") int pin,
			HttpSession session, Model model) {

		User list = (User) session.getAttribute("values");

		user.setCustomerID(list.getCustomerID());
		System.out.println("in setPin Controller" + user.getCustomerID());

		creditCard.setCardNumber(cardNumber);
		creditCard.setPin(pin);
		System.out.println(cardRecordsDAO.checkCardApproval(cardNumber));
		if (cardRecordsDAO.checkCardApproval(cardNumber) == true) {

			System.out.println(cardRecordsDAO.checkCardPin(cardNumber));
			if (cardRecordsDAO.checkCardPin(cardNumber) == false) {

				cardRecordsDAO.update(creditCard, user);

				model.addAttribute("checkApproval", "Success");

				return "customerProfile";

			}
		} else {
			model.addAttribute("checkApproval", "Failed");

		}

		return "setPin";

	}

	@PostMapping("/shop")

	public String shop(@RequestParam("buy") int amount, HttpSession session, Model model) {

		model.addAttribute("amount", amount);

		return "payment";

	}

	@PostMapping("/payment")

	public String payment(@RequestParam("amount") int amount, @RequestParam("cardNumber") String cardNumber,
			@RequestParam("cvv") int cvv, @RequestParam("validity") String validity,
			@RequestParam("description") String description, HttpSession session, HttpServletRequest request,
			Model model) {
		HttpSession sess = request.getSession();

		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyMM");
		YearMonth yearMonth = YearMonth.parse(validity, inputFormatter);
		String formattedDate = yearMonth.toString();
		System.out.println("Formatted date: " + formattedDate);

		transactions.setCardNumber(cardNumber);
		System.out.println("checkPayment" + cardRecordsDAO.checkPayment(transactions, cvv, formattedDate));
		if (cardRecordsDAO.checkPayment(transactions, cvv, formattedDate) == true) {

			User list = (User) session.getAttribute("values");

			transactions.setId((list.getCustomerID()));
			System.out.println("in payment Controller" + transactions.getId());

			if (amount < 1000) {

				creditCard.setCreditPoints(0);
			}

			else if (amount > 1000 && amount <= 1500) {
				creditCard.setCreditPoints(100);
			} else if (amount > 1500 && amount <= 2500) {
				creditCard.setCreditPoints(200);
			} else {

				creditCard.setCreditPoints(500);
			}

			transactions.setCardNumber(cardNumber);
			cardRecordsDAO.updateCreditPoints(creditCard, transactions);

			transactions.setTranscationId(numberGenerationDAO.transactionID());

			LocalDateTime localDateTime = LocalDateTime.now();
			String dateTime = localDateTime.toString();
			transactions.setDateTime(dateTime);
			transactions.setAmount(amount);
			transactions.setDescription(description);

			transactionRecordsDAO.insert(transactions);
			model.addAttribute("CardDetails", "paymentSuccess");
			return "shop";

		} else {

			model.addAttribute("CardDetails", "incorrectCardDetails");

		}

		return "payment";
	}

	@PostMapping("/statement")

	public String statement(@RequestParam("id") int id, @RequestParam("cardNumber") String cardNumber, Model model) {

		System.out.println("statement in controller" + transactionRecordsDAO.checkCardNumber(cardNumber, id));

		if (transactionRecordsDAO.checkCardNumber(cardNumber, id) == true) {

			transactions.setId(id);
			transactions.setCardNumber(cardNumber);

			List<Transactions> transactionList = transactionRecordsDAO.read(transactions);

			for (Transactions values : transactionList) {

				System.out.println("In statement controller" + values.getCardNumber());
			}
			model.addAttribute("transactions", transactionList);
		}

		return "statement";

	}

	@PostMapping("/creditPoints")

	public String creditPoints(@RequestParam("id") int id, @RequestParam("cardNumber") String cardNumber, Model model) {

		System.out.println("Im in creditPoints controller");

		System.out.println("in creditPoints controller" + cardRecordsDAO.checkPoints(id, cardNumber));

		if (cardRecordsDAO.checkPoints(id, cardNumber) == true) {

			model.addAttribute("creditPoints", cardRecordsDAO.readCreditPoints(id, cardNumber));

		}

		return "creditPoints";

	}

}
