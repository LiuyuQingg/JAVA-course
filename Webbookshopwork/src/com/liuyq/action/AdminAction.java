package com.liuyq.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.liuyq.bean.Admin;
import com.liuyq.bean.Book;
import com.liuyq.bean.Marketing;
import com.liuyq.bean.Page;
import com.liuyq.bean.Shop;
import com.liuyq.bean.Type;
import com.liuyq.bean.User;
import com.liuyq.service.AdminService;
import com.liuyq.service.UserService;

import java.util.Properties; 

@Controller
@RequestMapping("/admin")
public class AdminAction {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public String goIndex(Model model,HttpServletRequest request) {
		
		String ip = request.getLocalAddr();//获得服务器IP
		int port = request.getLocalPort();//获得服务器端口
		 Properties props=System.getProperties(); //获得系统属性集    
		  String osName = props.getProperty("os.name");//操作系统名称 
		  String osArch = props.getProperty("os.arch"); //操作系统构架    
		  String osVersion = props.getProperty("os.version"); //操作系统版本 
		  String java = props.getProperty("java.specification.version");//Java执行时环境规范版本号
		  Long shopCount = adminService.sumShop();
		model.addAttribute("shopCount", shopCount);
		Long userCount = adminService.sumUser();
		model.addAttribute("userCount", userCount);
		Long count = adminService.sumBook();
		model.addAttribute("bookCount", count);
		
		model.addAttribute("osName", osName);
		model.addAttribute("java", java);
		Date date = new Date();
		model.addAttribute("date", date);
		model.addAttribute("ip", ip);
		model.addAttribute("port", port);
		return "index";
		
	}
	
	@RequestMapping("/login")
	public String login(Model model,HttpSession session,HttpServletRequest request) {
		
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin != null) {
			
			String ip = request.getLocalAddr();//获得服务器IP
			int port = request.getLocalPort();//获得服务器端口
			 Properties props=System.getProperties(); //获得系统属性集    
			  String osName = props.getProperty("os.name");//操作系统名称 
			  String osArch = props.getProperty("os.arch"); //操作系统构架    
			  String osVersion = props.getProperty("os.version"); //操作系统版本 
			  String java = props.getProperty("java.specification.version");//Java执行时环境规范版本号
			  Long shopCount = adminService.sumShop();
			model.addAttribute("shopCount", shopCount);
			Long userCount = adminService.sumUser();
			model.addAttribute("userCount", userCount);
			Long count = adminService.sumBook();
			model.addAttribute("bookCount", count);
			
			model.addAttribute("osName", osName);
			model.addAttribute("java", java);
			Date date = new Date();
			model.addAttribute("date", date);
			model.addAttribute("ip", ip);
			model.addAttribute("port", port);
			return "index";
			
		}else {
			
			return "redirect:/user/login.do";
			
		}
		
		
	}
	
	@RequestMapping("/seeAllUser")
	public String seeAllUser(Model model,HttpSession session,@RequestParam(value="pages", required=false) Integer pages){
		
		List<Type> typelist = adminService.seeAllType();
		model.addAttribute("typelist", typelist);
		
		//分页显示图书
		Integer dPage = 1;
		if(pages != null) {
			dPage = pages;
		}
		Page page = new Page();
		page.setTotalcount(adminService.selectUserAllCount());
		page.setTotalPage();
		page.setDpage(dPage);
		List<User> list = adminService.findAllUser(page);
		model.addAttribute("userlist", list);
		model.addAttribute("page",page);
		
		return "allUser";
		
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user) {
		
		return "addUser";
		
	}
	
	@RequestMapping("/findUser")
	
	public String updateUser(Integer id,Model model) {
		
		User user = adminService.findUserById(id);
		model.addAttribute("user",user );
		return "singleUser";
		
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(Integer id,Model model){
		
		List<Shop> shoplist = adminService.findShopByUserId(id);
		int length = shoplist.size();
		if(length == 0) {
			
			adminService.deleteUser(id);
			model.addAttribute("message", "删除成功！");
			return "forward:seeAllUser.to";
			
		}else {
			
			model.addAttribute("message", "用户还有未完成的订单，删除不成功！");
			return "forward:seeAllUser.to";
			
		}
		
	}
	
	@RequestMapping("/findUserByPoint")
	public String findUserByPoint(String point,Model model) {
		
		List<User> list = adminService.findUserByPoint(point);
		model.addAttribute("userlist",list);
		
		return "allUser";
		
	}
	
	@RequestMapping("/insertUser")
	public String insertUser(User user,@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request,Model model) {
		
		if(upfile .getOriginalFilename() == "") {
			
			Integer i  = userService.insertUser(user);
			//model.addAttribute("user", user1);
			model.addAttribute("msg", "添加成功！");
			
			return "addUser";
			
		}else if(upfile .getOriginalFilename() != "") {
			String rootPath = request.getServletContext().getRealPath("/");
		
			try {
			
				FileCopyUtils.copy(upfile.getBytes(),new File(rootPath+"/images/",upfile.getOriginalFilename()));
		
				user.setImgUrl("images/"+upfile.getOriginalFilename());
			
				///User user1 = userService.insertUser(user);
				///model.addAttribute("user", user1);
				
				Integer i = userService.insertUser(user);
				System.out.println(i);
				model.addAttribute("msg", "添加成功！");
		
			}catch(IOException e){
			
				e.printStackTrace();
			
			}
		
			return "addUser";
		}else {
			model.addAttribute("msg", "修改未成功！");
			return "addUser";
			
		}
		
	}
	
	@RequestMapping("/seeAllBook")
	public String seeAllBook(Model model,HttpSession session,@RequestParam(value="pages", required=false) Integer pages) {
		
		List<Type> typelist = adminService.seeAllType();
		model.addAttribute("typelist", typelist);
		
		//分页显示图书
		Integer dPage = 1;
		if(pages != null) {
			dPage = pages;
		}
		Page page = new Page();
		page.setTotalcount(adminService.selectAllCount());
		page.setTotalPage();
		page.setDpage(dPage);
		List<Book> list = adminService.seeAllBook(page);
		model.addAttribute("booklist", list);


		model.addAttribute("page",page);

		return "allBook";
		
		
		
		
	}
	
	@RequestMapping("/findBookByKey")
	public String findBookByKey(Integer booktype,String point,Model model) {
		
		System.out.println(booktype);
		List<Book> booklist = adminService.findBokkByKey(booktype, point);
		List<Type> typelist = adminService.seeAllType();
		model.addAttribute("booklist", booklist);
		model.addAttribute("typelist", typelist);
		
		return "allBook";
		
	}
	
	@RequestMapping("/findBookById")
	public String findBookById(Integer id,Model model) {
		
		Book book = adminService.findBookById(id);
		Set<Type> typeSet = book.getTypeSet();
		Set<Marketing> markSet = book.getMarkSet();
		List<Type> typelist = adminService.seeAllType();
		List<Type> typelist1 = adminService.seeAllType();
		List<Marketing> marketlist = adminService.findAllMarket();
		List<Marketing> marketlist1 = adminService.findAllMarket();


		for(int i=0 ; i<typelist1.size();i++) {
			
			for(Type t1:typeSet) {
				
				if(typelist1.get(i).getId()==t1.getId()) {
					
					typelist1.remove(i);
					
				}
				
			}
			
		}
		for(int i=0 ; i<marketlist1.size();i++) {
			
			for(Marketing t1:markSet) {
				
				if(marketlist1.get(i).getId()==t1.getId()) {
					
					marketlist1.remove(i);
					
				}
				
			}
			
		}
		model.addAttribute("book", book);
		model.addAttribute("typelist", typelist);
		model.addAttribute("typelist1", typelist1);
		model.addAttribute("marketlist", marketlist);
		model.addAttribute("marketlist1", marketlist1);
		
		return "bookDetil";
		
	}
	
	@RequestMapping("/updateBook")
	public String updateBook(Book book,Integer[] type,Integer[] mark,@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request,Model model) {
		
		List<Type> typelist = new ArrayList<Type>();
		List<Marketing> marketlist = new ArrayList<Marketing>();
		for(Integer i:type) {
			
			Type t = adminService.findTypeById(i);
			typelist.add(t);
			
		}
		for(Type t:typelist) {
			
			book.getTypeSet().add(t);
			
		}
		for(Integer i:mark) {
			
			Marketing m = adminService.findMarketById(i);
			marketlist.add(m);
			
		}
		for(Marketing m:marketlist) {
			
			book.getMarkSet().add(m);
			
		}
		
		if(upfile .getOriginalFilename() == "") {
			
			
			
			boolean res = adminService.updateBookExceptImgurl(book);
			//model.addAttribute("user", user1);
			model.addAttribute("msg", "修改成功！");
			model.addAttribute("id", book.getId());
			
			return "forward:findBookById.to";
			
		}else if(upfile .getOriginalFilename() != "") {
			String rootPath = request.getServletContext().getRealPath("/");
		
			try {
			
				FileCopyUtils.copy(upfile.getBytes(),new File(rootPath+"/images/",upfile.getOriginalFilename()));
		
				book.setImgUrl("images/"+upfile.getOriginalFilename());
			
				///User user1 = userService.insertUser(user);
				///model.addAttribute("user", user1);
				
				boolean result = adminService.updateBook(book);
				model.addAttribute("msg", "修改成功！");
				model.addAttribute("id", book.getId());
		
			}catch(IOException e){
			
				e.printStackTrace();
			
			}
		
			return "forward:findBookById.to";
		}else {
			model.addAttribute("msg", "修改未成功！");
			model.addAttribute("id", book.getId());
			return "forward:findBookById.to";
			
		}
		
	}
	
	@RequestMapping("/seeAllShop")
	public String seeAllShop(Model model,HttpSession session,@RequestParam(value="pages", required=false) Integer pages) {
		
		
		//分页显示图书
		Integer dPage = 1;
		if(pages != null) {
			dPage = pages;
		}
		Page page = new Page();
		page.setTotalcount(adminService.selectShopAllCount());
		page.setTotalPage();
		page.setDpage(dPage);
		List<Shop> shoplist = adminService.seeAllShop(page);
		model.addAttribute("shoplist", shoplist);


		model.addAttribute("page",page);

		return "allShop";
		
	}
	
	@RequestMapping("/findShop")
	public String findShop(Integer id,Model model) {
		
		Shop shop = adminService.findShop(id);
		model.addAttribute("shop", shop);
		
		return "shopDetil";
		
	}
	
	@RequestMapping("/updateShop")
	public String updateShop(Integer id,Model model){
		
		boolean res = adminService.updateShop(id);
		if(res) {
			model.addAttribute("msg", "发货成功！");
			return "forward:findShop.to";

		}else {
			
			model.addAttribute("msg", "发货失败！");
			return "forward:findShop.to";

		}
		
	}
	
	@RequestMapping("/findShopByState")
	public String findShopBySate(String state,Model model,HttpSession session,@RequestParam(value="pages", required=false) Integer pages){
		
		//分页显示图书
		Integer dPage = 1;
		if(pages != null) {
			dPage = pages;
		}
		Page page = new Page();
		page.setTotalcount(adminService.selectShopAllCountByState(state));
		page.setTotalPage();
		page.setDpage(dPage);
		
		
		List<Shop> shoplist = adminService.findShopByState(state,page);
		model.addAttribute("shoplist", shoplist);
		model.addAttribute("page",page);

		return "allShop";
		


		
		
	}
	
	@RequestMapping("/addBook")
	public String addBook(Model model) {
		
		List<Type> typelist = adminService.seeAllType();
		model.addAttribute("typelist", typelist);
		
		List<Marketing> marketlist = adminService.findAllMarket();
		model.addAttribute("marketlist", marketlist);
		
		return "addBook";
		
	}
	
	@RequestMapping("/insertBook")
	public String insertBook(Book book,String[] type,String[] market,@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request,Model model) {
		
		List<Type> typelist = new ArrayList<Type>();
		for(String i:type) {
			
			Type t = adminService.findType(i);
			typelist.add(t);
			
		}
		for(Type t:typelist) {
			
				book.getTypeSet().add(t);
			
		}
		
		List<Marketing> marketlist = new ArrayList<Marketing>();
		for(String i:market) {
			
			Marketing m = adminService.findMarketingByType(i);
			marketlist.add(m);
			
		}
		for(Marketing m:marketlist) {
			
				book.getMarkSet().add(m);
			
		}
		if(upfile .getOriginalFilename() == "") {
			
		
			boolean res = adminService.insertBook(book);
			//model.addAttribute("user", user1);
			model.addAttribute("message", "添加成功！");
			
			return "forward:seeAllBook.to";
			
		}else if(upfile .getOriginalFilename() != "") {
			String rootPath = request.getServletContext().getRealPath("/");
		
			try {
			
				FileCopyUtils.copy(upfile.getBytes(),new File(rootPath+"/images/",upfile.getOriginalFilename()));
		
				book.setImgUrl("images/"+upfile.getOriginalFilename());
			
				///User user1 = userService.insertUser(user);
				///model.addAttribute("user", user1);
				
				boolean result = adminService.insertBook(book);
				model.addAttribute("message", "添加成功！");
				
			}catch(IOException e){
			
				e.printStackTrace();
			
			}
			return "forward:seeAllBook.to";
			
		}else {
			model.addAttribute("msg", "添加未成功！");
			model.addAttribute("id", book.getId());
			return "forword:seeAllBook.to";
			
		}
		
		
	}
	
	@RequestMapping("/deleteBookById")
	public String deleteBookById(Integer id,Model model){
		
		Book book = adminService.findBookById(id);
		if(book.getOrderSet().isEmpty()) {
			
			boolean res = adminService.deleteBookById(id);
			model.addAttribute("message", "删除成功！");
			return "forward:seeAllBook.to";

		}else {
			
			model.addAttribute("message", "还有未处理订单，不能删除！");
			return "forward:seeAllBook.to";
			
		}
	}
	
	@RequestMapping("/addType")
	public String addType() {
		
		return "addType";
		
	}
	
	@RequestMapping("/insertType")
	public String insertType(String type,Model model) {
		
		Type t = new Type();
		t.setType(type);
		boolean res = adminService.insertType(t);
		if(res) {
			model.addAttribute("msg", "添加成功！");
			return "forward:findAllType.to";
			
		}else {
			model.addAttribute("msg", "添加不成功！");
			return "forward:addType.to";
		}
		
	}
	
	@RequestMapping("/findAllType")
	public String findAllType(Model model) {
		
		List<Type> list = adminService.findAllType();
		model.addAttribute("typelist", list);
		
		return "allType";
		
	}
	
	@RequestMapping("/findTypeById")
	public String findType(Integer id,Model model) {
		
		Type type = adminService.findTypeById(id);
		model.addAttribute("type", type);
		
		return "typeDetil";
		
	}
	
	@RequestMapping("/updateType")
	public String updateType(Type type,Model model) {
		
		boolean res = adminService.updateType(type);
		if(res) {
			
			model.addAttribute("id", type.getId());
			model.addAttribute("msg", "修改成功！");
			return "forward:findTypeById.to";
			
		}else {
			
			model.addAttribute("id", type.getId());
			model.addAttribute("msg", "修改失败！");
			return "forward:findTypeById.to";
		}
		
	}
	
	@RequestMapping("/deleteType")
	public String deleteType(Integer id,Model model) {
		
		Type type = adminService.findTypeById(id);
		if(type.getBookSet().isEmpty()) {
			
			boolean res = adminService.deleteType(id);
			if(res) {
				
				model.addAttribute("msg", "删除成功！");
				return "forward:findAllType.to";
				
			}else {
				model.addAttribute("msg", "删除失败！");
				return "forward:findAllType.to";
				
			}
		}else {
			
			model.addAttribute("msg", "还有书是这个类别，不能删除此类！");
			return "forward:findAllType.to";
		
		}
	}
	
	@RequestMapping("/findShopByPoint")
	public String findShopByPoint(String point,Model model) {
		
		List<Shop> shoplist = adminService.findShopByKey(point);
		model.addAttribute("shoplist", shoplist);
		return "allShop";
		
	}
	
	@RequestMapping("/findTypeByPoint")
	public String findTypeByPoint(String point,Model model) {
		
		List<Type> typelist = adminService.findTypeByPoint(point);
		model.addAttribute("typelist", typelist);
		return "allType";
		
	}
	
	@RequestMapping("/deleteShop")
	public String deleteShop(Integer id,Model model) {
		
		Shop shop = adminService.findShop(id);
		String state = shop.getState();
		if(state.equals("已发货")) {
			
			boolean res = adminService.deleteShop(id);
			if(res) {
				
				model.addAttribute("message", "删除成功！");
				return "forward:seeAllShop.to";
				
			}else {
				model.addAttribute("message", "删除失败！");
				return "forward:seeAllShop.to";
				
			}
			
		}else {
			
			model.addAttribute("message", "订单还未处理，不能删除！");
			return "forward:seeAllShop.to";
			
		}
		
		
	}
	
	@RequestMapping("/deleteApart")
	public String deleteApart(Integer[] ifdelete,Model model) {
		
		for(Integer id:ifdelete) {
			
			User user = adminService.findUserById(id);
			List<Shop> shoplist = adminService.findShopByUserId(id);
			int length = shoplist.size();
			if(length == 0) {
				
			}else {
				
				model.addAttribute("message", user.getUsername()+"还有未完成的订单，批量删除不成功！");
				return "forward:seeAllUser.to";
				
			}
		
		}
		for(Integer id:ifdelete) {
			
			List<Shop> shoplist = adminService.findShopByUserId(id);
			int length = shoplist.size();
			if(length == 0) {
				
				adminService.deleteUser(id);
				
				
			}else {
				model.addAttribute("message", "批量删除不成功！");
				return "forward:seeAllUser.to";
				
			}
			

		}
		
		model.addAttribute("message", "批量删除成功！");
		return "forward:seeAllUser.to";
		
	}
	
	@RequestMapping("/deletePartBook")
	public String deletePartBook(Integer[] ifdelete,Model model) {
		
		for(Integer id:ifdelete) {
			
			Book book = adminService.findBookById(id);
			if(book.getOrderSet().isEmpty()) {
				

			}else {
				
				model.addAttribute("message", book.getBookName()+"还有未处理订单，不能删除！");
				return "forward:seeAllBook.to";
				
			}

		}
		
		for(Integer id:ifdelete) {
			
			Book book = adminService.findBookById(id);
			if(book.getOrderSet().isEmpty()) {
				
				boolean res = adminService.deleteBookById(id);

			}else {
				
				model.addAttribute("message", "还有未处理订单，不能删除！");
				return "forward:seeAllBook.to";
				
			}

		}
		model.addAttribute("message", "批量删除成功！");
		return "forward:seeAllBook.to";
	}
	
	@RequestMapping("/deletepartType")
	public String deltepartType(Integer[] ifdelete,Model model) {
		
		for(Integer id:ifdelete) {
			
			Type type = adminService.findTypeById(id);
			if(type.getBookSet().isEmpty()) {
			
			}else {
				
				model.addAttribute("msg", "还有书是这个类别，不能删除此类！");
				return "forward:findAllType.to";
			
			}
			
		}
		
		for(Integer id:ifdelete) {
			
			Type type = adminService.findTypeById(id);
			if(type.getBookSet().isEmpty()) {
				
				boolean res = adminService.deleteType(id);
				
			}else {
				
				model.addAttribute("msg", "还有书是这个类别，批量删除不成功！");
			
			}
			
		}
		model.addAttribute("msg", "批量删除成功！");
		return "forward:findAllType.to";

	}
	
	@RequestMapping("/deletepartShop")
	public String deletepartShop(Integer[] ifdelete,Model model) {
		
		for(Integer id:ifdelete) {
			
			Shop shop = adminService.findShop(id);
			String state = shop.getState();
			if(state.equals("已发货")) {
				
				
				
			}else {
				
				model.addAttribute("message", "订单还未处理，批量删除不成功！");
				return "forward:seeAllShop.to";
				
			}
			
		}
		
		for(Integer id:ifdelete) {
			
			Shop shop = adminService.findShop(id);
			String state = shop.getState();
			if(state.equals("已发货")) {
				
				adminService.deleteShop(id);
				
			}else {
				
				model.addAttribute("message", "有的订单还未处理，批量删除不成功！");
				return "forward:seeAllShop.to";
				
			}
			
		}
		model.addAttribute("message", "批量删除成功！");
		return "forward:seeAllShop.to";
		
	}
	
	@RequestMapping("/seeAllAdmin")
	public String seeAllAdmin(Model model,HttpSession session,@RequestParam(value="pages", required=false) Integer pages) {
		
		//分页显示图书
				Integer dPage = 1;
				if(pages != null) {
					dPage = pages;
				}
				Page page = new Page();
				page.setTotalcount(adminService.selectAdminAllCount());
				page.setTotalPage();
				page.setDpage(dPage);
				List<Admin> adminlist = adminService.findAllAdmin(page);
				model.addAttribute("userlist", adminlist);
				model.addAttribute("page",page);
		
		
		
		return "allAdmin";
		
	}
	
	@RequestMapping("/seeAdminDetil")
	public String seeSingleAdmin(HttpSession session,Model model) {
		
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin != null) {
				
			Integer id = admin.getId();
			Admin admin1 = adminService.seeSinglAdmin(id);
			model.addAttribute("admin", admin1);
			return "adminDetil";
				
			}else {
				
				return "redirect:/user/login.do";
				
			}
		
	}
	
	@RequestMapping("/updateAdmin")
	public String updateAdmin(Admin admin,@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request,Model model) {
		
		if(upfile .getOriginalFilename() == "") {
			
			Admin admin1 = adminService.updateAdmin(admin);
			model.addAttribute("admin", admin1);
			
			return "adminDetil";
			
		}else if(upfile .getOriginalFilename() != "") {
			String rootPath = request.getServletContext().getRealPath("/");
		
			try {
			
				FileCopyUtils.copy(upfile.getBytes(),new File(rootPath+"/images/",upfile.getOriginalFilename()));
		
				admin.setImgUrl("images/"+upfile.getOriginalFilename());
			
				Admin admin1 = adminService.updateAdmin(admin);
				model.addAttribute("admin", admin1);
		
			}catch(IOException e){
			
				e.printStackTrace();
			
			}
		
	
			return "adminDetil";
		}
		return "adminDetil";
		
		
	}
	
	@RequestMapping("/loginout")
	public String loginout(HttpSession session) {
		
		session.removeAttribute("admin");
		
		Admin admin = (Admin) session.getAttribute("admin");
		return "redirect:/book/recommend.do";
		
	}
	
	@RequestMapping("/findAdmin")
	public String findAdmin(Integer id,Model model) {
		
		Admin admin = adminService.seeSinglAdmin(id);
		model.addAttribute("admin",admin );
		return "singleAdmin";
		
	}
	
	@RequestMapping("/deleteAdmin")
	public String deleteAdmin(Integer id,Model model) {
		
		boolean res = adminService.deleteAdmin(id);
		if(res) {
			
			model.addAttribute("message", "删除成功！");
			return "forward:seeAllAdmin.to";
			
		}else {
			model.addAttribute("message", "删除不成功！");
			return "forward:seeAllAdmin.to";
			
		}
		
	}
	
	@RequestMapping("/addAdmin")
	public String addAdmin() {
		
		return "addAdmin";
		
	}
	
	@RequestMapping("/insertAdmin")
	public String insertAdmin(Admin admin1,@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request,Model model) {
		
		if(upfile .getOriginalFilename() == "") {
			
			boolean res  = adminService.insertAdmin(admin1);
			model.addAttribute("msg", "添加成功！");
			Admin admin = adminService.findMaxIdAdmin();
			model.addAttribute("admin1", admin);
			return "addAdmin";
			
		}else if(upfile .getOriginalFilename() != "") {
			String rootPath = request.getServletContext().getRealPath("/");
		
			try {
			
				FileCopyUtils.copy(upfile.getBytes(),new File(rootPath+"/images/",upfile.getOriginalFilename()));
		
				admin1.setImgUrl("images/"+upfile.getOriginalFilename());
				
				///User user1 = userService.insertUser(user);
				///model.addAttribute("user", user1);
				
				boolean res = adminService.insertAdmin(admin1);
				//System.out.println(i);
				Admin admin = adminService.findMaxIdAdmin();
				model.addAttribute("admin1", admin);
				model.addAttribute("msg", "添加成功！");
		
			}catch(IOException e){
			
				e.printStackTrace();
			
			}
		
			return "addAdmin";
		}else {
			model.addAttribute("msg", "修改未成功！");
			return "addAdmin";
			
		}
				
	}
	
	@RequestMapping("/deleteApartAdmin")
public String deleteApartAdmin(Integer[] ifdelete,Model model,HttpSession session) {
		
		for(Integer id:ifdelete) {
			
			Admin admin1 = (Admin) session.getAttribute("admin");
			if(admin1 != null) {
			
					Integer id0 = admin1.getId();
					if(id.equals(id0)) {
					
						model.addAttribute("message", admin1.getUsername()+"正登录，批量删除不成功！");
						return "forward:seeAllAdmin.to";
					}
			
				}else {
					
					return "redirect:/user/login.do";
				}
		}
		for(Integer id:ifdelete) {
			
			Admin admin1 = (Admin) session.getAttribute("admin");
			if(admin1 != null) {
			
					Integer id0 = admin1.getId();
					if(id.equals(id0)) {
					
					}else {
						
						boolean res = adminService.deleteAdmin(id);
						if(res) {
							model.addAttribute("message", "批量删除成功！");
							
						}else {
							model.addAttribute("message", "批量删除不成功！");
							return "forward:seeAllAdmin.to";
							
						}
						
					}
			
				}else {
					
					return "redirect:/user/login.do";
				}
		}
		return "forward:seeAllAdmin.to";
	}
	
	@RequestMapping("/findAdminByPoint")
	public String findAdminByPoint(String point,Model model) {
		
		List<Admin> list = adminService.findAdminByPoint(point);
		model.addAttribute("userlist",list);
		
		return "allAdmin";
	}
	
	@RequestMapping("/getAllType")
	public String getAllType(Model model,HttpSession session,@RequestParam(value="pages", required=false) Integer pages) {
		
			//分页显示图书
			Integer dPage = 1;
			if(pages != null) {
				dPage = pages;
			}
			Page page = new Page();
			page.setTotalcount(adminService.selectTypeAllCount());
			page.setTotalPage();
			page.setDpage(dPage);
			List<Type> list = adminService.getAllType(page);
			model.addAttribute("typelist", list);


			model.addAttribute("page",page);

			return "allType";
			
	}
	
}
