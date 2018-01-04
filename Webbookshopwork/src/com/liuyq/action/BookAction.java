package com.liuyq.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuyq.bean.Book;
import com.liuyq.bean.Marketing;
import com.liuyq.bean.Orders;
import com.liuyq.bean.Page;
import com.liuyq.bean.Pages;
import com.liuyq.bean.Shop;
import com.liuyq.bean.Type;
import com.liuyq.bean.User;
import com.liuyq.service.BookService;



@Controller
@RequestMapping("/book")
public class BookAction {

	@Autowired
	private BookService bookService;
	
	//查看用户账号是否已经被注册
			@ResponseBody 
			@RequestMapping(value="/checkUser.do")
			public String checkUser(Model model,String username) throws JsonGenerationException, JsonMappingException, IOException {
				
				boolean isUsered = bookService.usernameIsUsered(username);
				
				ObjectMapper x = new ObjectMapper();
				String str = x.writeValueAsString(isUsered);  
				return str;
			}

	
	@RequestMapping("/booklist")
	public String userlist(Model model,HttpSession session,@RequestParam(value="pages", required=false) Integer pages) {
		
		//分页显示图书
		Integer dPage = 1;
		if(pages != null) {
			dPage = pages;
		}
		Pages page = new Pages();
		page.setTotalcount(bookService.selectBookAllCount());
		page.setTotalPage();
		page.setDpage(dPage);
		
		List<Book> list = bookService.findAll(page);
		List<Type> typelist = bookService.findAllType();
		model.addAttribute("typelist",typelist);
		model.addAttribute("booklist",list);
		model.addAttribute("page", page);
		return "products";
		
	}
	
	@RequestMapping("/recommend")
	public String recommend(Model model) {
		
		List<Book> list = bookService.recommendBook();
		List<Book> list2 = bookService.mainPush();
		List<Type> list3 = bookService.findAllType();
		
		model.addAttribute("booklist", list);
		model.addAttribute("list", list2);
		model.addAttribute("typelist", list3);
		
		return "index";
		
	}
	
	@RequestMapping("/newbook")
	public String newBook(Model model) {
		
		List<Book> list = bookService.newBook();
		List<Type> typelist = bookService.findAllType();
		model.addAttribute("typelist",typelist);
		model.addAttribute("booklist", list);
		
		return "products";
		
	}
	
	@RequestMapping("/seeSingleBook")
	public String seeSingleBook(Integer id,Model model) {
		
		System.out.println(id);
		Book book = bookService.seeSingleBook(id);
		List<String> typelist = new ArrayList<String>();
		 Iterator<Type> iter=book.getTypeSet().iterator();
		 List<Type> list1 = bookService.findAllType();
		 while(iter.hasNext()){
		     //获得Item对象，即Set里面的每一个值
		     Type type = iter.next();
		     typelist.add(type.getType());
		     List<Book> list = bookService.findBookByType(type.getType());
		     model.addAttribute("booklist", list);
		   }
		 
		model.addAttribute("book",book);
		List<Type> typelists = bookService.findAllType();
		model.addAttribute("typelists",typelists);
		model.addAttribute("typelist", list1);
		return "single";
		
	}
	
	@RequestMapping("/foundBook")
	public String foundBook(String tag,Model model) {
		
		List<Book> list = bookService.foundBook(tag);
		List<Type> typelist = bookService.findAllType();
		model.addAttribute("typelist",typelist);
		model.addAttribute("booklist", list);
		
		return "products";
	}
	
	@RequestMapping("/regist")
	public String regist() {
		
		return"login";
		
	}
	
	@RequestMapping("/seeTypeBook")
	public String seeTypeBook(String type,Model model,HttpSession session,@RequestParam(value="pages", required=false) Integer pages) {
		
		//分页显示图书
				Integer dPage = 1;
				if(pages != null) {
					dPage = pages;
				}
				Pages page = new Pages();
				page.setTotalcount(bookService.selectTypeBookAllCount(type));
				page.setTotalPage();
				page.setDpage(dPage);
		
		List<Book> list = bookService.seeTypeBook(type,page);
		model.addAttribute("booklist", list);
		List<Type> typelist = bookService.findAllType();
		model.addAttribute("typelist",typelist);
		model.addAttribute("page", page);
		return "products";
		
	}
	
	@RequestMapping("/seeMarkBook")
	public String seeMarkBook(String type,Model model,HttpSession session,@RequestParam(value="pages", required=false) Integer pages) {
		
		//分页显示图书
		Integer dPage = 1;
		if(pages != null) {
			dPage = pages;
		}
		Pages page = new Pages();
		page.setTotalcount(bookService.selectMarkBookAllCount(type));
		page.setTotalPage();
		page.setDpage(dPage);
		
		List<Book> list = bookService.seeMarkBook(type,page);
		model.addAttribute("booklist", list);
		List<Type> typelist = bookService.findAllType();
		model.addAttribute("typelist",typelist);
		model.addAttribute("page", page);

		return "products";
		
	} 
	
	@RequestMapping("/newSeeTypeBook")
	public String newSeeTypeBook(String type,Model model) {
		
		List<Book> list = bookService.newSeeTypeBook(type);
		List<Type> typelist = bookService.findAllType();
		model.addAttribute("typelist",typelist);
		model.addAttribute("booklist", list);
		return "products";
		
	}
	
	@RequestMapping("/addChat")
	public String addChat(Integer id,Integer quentity,Model model,HttpSession session) {
		
		User user = (User) session.getAttribute("user1");
		if(user != null) {
			
			Integer userId = user.getId();
			User user1 = bookService.findUserById(userId);
			Orders order = new Orders();
			Book book = bookService.findBookById(id);
			order.setBook(book);
			order.setUser(user1);
			order.setDate(new Date());
			order.setQuentity(quentity);
			bookService.addChat(order);
			
			return "redirect:booklist.do";
			
		}else {
			
			model.addAttribute("error", "您还没有登录！");
			return "login";
			
		}
		
		
	}
	
	@RequestMapping("/singleAddChat")
	public String singleAddChat(Integer id,Integer quentity,Model model,HttpSession session) {
		
		User user = (User) session.getAttribute("user1");
		Integer userId = user.getId();
		User user1 = bookService.findUserById(userId);
		Orders order = new Orders();
		Book book = bookService.findBookById(id);
		order.setBook(book);
		order.setUser(user1);
		order.setDate(new Date());
		order.setQuentity(quentity);
		bookService.addChat(order);
		
		return "redirect:booklist.do";
		
	}
	
	@RequestMapping("/seeBookChat")
	private String seeChat(Integer id,Model model,HttpSession session) {
		
		User user = (User) session.getAttribute("user1");
		List<Type> list1 = bookService.findAllType();
		if(user != null) {
			
			Integer userId = user.getId();
			List<Orders> list = bookService.seeChat(userId);
			model.addAttribute("orders", list);
			
			model.addAttribute("typelist", list1);
			return "checkout";
			
		}else {
			
			model.addAttribute("error", "您还没有登录!");
			return "login";
			
		}
		
		
	}
	
	@RequestMapping("/shopping")
	public String shopping(Integer id,String address,Integer quentity,Model model,HttpSession session) {
		
		User user = (User) session.getAttribute("user1");
		Orders order = bookService.upOrderById(id,address,quentity);
		Shop shop = new Shop();
		shop.setUser(user);
		shop.setOrder(order);
		shop.setDate(new Date());
		shop.setState("未发货");
		boolean result = bookService.shopping(shop);
		if(result) {
			
			boolean r = bookService.upOrder(order);
			if(r) {
				
				return "redirect:seeShopping.do";
			}

				
				
			
			
		}
		return "";
		
	}
	
	@RequestMapping("/seeShop")
	public String seeShop(Integer id,Integer quentity,Model model) {
		
		
		Orders order1 = bookService.seeShop(id);
		Orders order = bookService.saveOrder(order1);
		List<Type> list = bookService.findAllType();
		
		
		model.addAttribute("order", order);
		model.addAttribute("typelist", list);
		
		return "contact";
		
	}
	
	@RequestMapping("/seeShopping")
	public String seeShopping(Model model,HttpSession session){
		
		User user1 = (User) session.getAttribute("user1");
		List<Type> list1 = bookService.findAllType();
		if(user1 != null) {
			Integer userId = user1.getId();
			List<Shop> list = bookService.seeShopping(userId);
			model.addAttribute("shops", list);
			model.addAttribute("typelist", list1);
			return "shop";
			
		}else {
			
			model.addAttribute("error", "您还没有登录！");
			return "login";
			
		}
		
		
	}
	
	@RequestMapping("/deleteOrder")
	public String deleteOrder(Integer id) {
		
		boolean result = bookService.deleteOrder(id);
		if(result) {
			
			return "redirect:seeBookChat.do";
			
		}
		
		return "";
		
	}
	
	@RequestMapping("/deleteAllChat")
	public String deleteAllChat(HttpSession session,Model model) {
		
		User user = (User) session.getAttribute("user1");
		if(user != null) {
			
			Integer userId = user.getId();
			
			boolean result = bookService.deleteAllChat(userId);
			
			if(result) {
				
				return "redirect:seeBookChat.do";
				
			}else {
				
				return "";
				
			}
			
		}else {
			
			model.addAttribute("error", "您还没有登录！");
			return "login";
			
		}
		
	}
	
	@RequestMapping("/deleteShop")
	public String deleteShop(Integer id,String state,Model model) {
				
		if(state.equals("已发货")) {
			
			boolean reult = bookService.deleteShop(id);
			if(reult) {
				
				model.addAttribute("msg", "删除成功！");
				return "forward:seeShopping.do";
				
			}else {
				
				model.addAttribute("msg", "删除失败！");
				return "forward:seeShopping.do";
				
			}
			
		}else {
			
			model.addAttribute("msg", "订单还未被处理，删除失败！");
			return "forward:seeShopping.do";
			
		}
		
		
	}
	
	@RequestMapping("/buyBook")
	public String byeBook(Integer id,Integer quentity,Model model,HttpSession session) {
		
		User user = (User) session.getAttribute("user1");
		List<Type> list1 = bookService.findAllType();
		if(user != null) {
			
			Orders order = bookService.createShop(id, quentity, user);
			model.addAttribute("order", order);
			model.addAttribute("typelist", list1);
			return "contact";
			
		}else {
			
			model.addAttribute("error", "您还没有登录！");
			return "login";
			
		}
		
	}
	
	@RequestMapping("/clearShop")
	public String clearShop(Integer id,String state,Model model) {
		
		if(state.equals("未发货")) {
			
			boolean res = bookService.clearShop(id);
			if(res) {
				
				model.addAttribute("msg", "取消成功！");
				return "forward:seeShopping.do";
				
			}else {
				
				model.addAttribute("msg", "取消失败！");
				return "forward:seeShopping.do";
				
			}
			
		}else {
			model.addAttribute("msg", "商品已经发货，不能取消！");
			return "forward:seeShopping.do";
			
		}
		
	}
	
	@RequestMapping("/deleteApart")
	public String deleteApart(String delete) {
		
		
			
			System.out.println(delete);
			
	
		return "";
		
	}
	
	@RequestMapping("/seePartShop")
	public String seePartShop(Integer[] buy,Model model,HttpSession session) {
	
//		List<Orders> orderlist = new ArrayList<Orders>();
//		for(String id:buy) {
//			String point[] = id.split(",");
//			Integer oId = Integer.parseInt(point[0]);
//			Orders order1 = bookService.seeShop(oId);
//			order1.setQuentity(Integer.parseInt(point[1]));
//			Orders order = bookService.saveOrder(order1);
//			orderlist.add(order);
//		}
//		
//		List<Type> list = bookService.findAllType();
//		
//		
//		model.addAttribute("orderlist", orderlist);
//		model.addAttribute("typelist", list);
		
		List<Orders> orderlist = new ArrayList<Orders>();
		for(Integer id:buy) {
			
			Orders order1 = bookService.seeShop(id);
			//order1.setQuentity(quentity);
			Orders order = bookService.saveOrder(order1);
			orderlist.add(order);
		}
		
		List<Type> list = bookService.findAllType();
		 User user = (User) session.getAttribute("user1");
		
		 model.addAttribute("user", user);
		model.addAttribute("orderlist", orderlist);
		model.addAttribute("typelist", list);
		
		return "contactlist";
		
		
		
	}
	
	@RequestMapping("/partShopping")
	public String partshopping(Integer[] id,Integer[] quentity,String address,Model model,HttpSession session) {
		User user = (User) session.getAttribute("user1");
		Integer uId = user.getId();
		User u =bookService.updateUser(uId,address);
		session.removeAttribute("user1");
		System.out.println(session.getAttribute("user1"));
		session.setAttribute("user1", u);
		Double total = 0.0;
		List<Orders> orderlist = new ArrayList<Orders>();
		for(int i=0;i<id.length;i++) {
			
			Orders order = bookService.selectOrderById(id[i]);
			order.setUser(user);
			orderlist.add(order);

		}
		
		for(int i=0;i<quentity.length;i++) {
			Orders order = orderlist.get(i);
			order.setQuentity(quentity[i]);
			Integer oId = order.getId();
			bookService.updateOrderQ(quentity[i],oId);
			
		}
		
		for(Orders o:orderlist) {
			
			Double m = o.getBook().getPrice() * o.getQuentity();
			total = m+total;
			
		}
		
		User user1 = (User) session.getAttribute("user1");
		model.addAttribute("orderlist", orderlist);
		model.addAttribute("total", total);
		model.addAttribute("user", user1);
		return "makeSureShop";
		
	}
	
	@RequestMapping("/makeSureShop")
	public String makeSureShop(HttpSession session,Integer[] id,Integer[] quentity,String address,Model model) {
		List<Shop> shoplist = new ArrayList<Shop>();
		List<Shop> shoplist1 = new ArrayList<Shop>();
		List<Double> moneylist = new ArrayList<Double>();
		Double total = 0.0;
		for(Integer i:id) {
			
			Shop shop = new Shop();
			Orders order = bookService.selectOrderById(i);
			shop.setOrder(order);
			shop.setUser(order.getUser());
			shoplist.add(shop);
			
		}

		for(int i=0;i<quentity.length;i++) {
			Orders order = shoplist.get(i).getOrder();
			order.setQuentity(quentity[i]);
			Integer oId = order.getId();
			bookService.updateOrderQ(quentity[i],oId);
			
		}
		
		for(Shop s:shoplist) {
			
			s.setDate(new Date());
			s.setState("未发货");
			Shop shop = bookService.shopping1(s);
			shoplist1.add(shop);
			Double money = shop.getOrder().getBook().getPrice() * shop.getOrder().getQuentity();
			moneylist.add(money);
		}
		
		for(Double d : moneylist) {
			
			total = d+total;
			
		}
		User user = (User) session.getAttribute("user1");
//		model.addAttribute("user", user);
//		model.addAttribute("total", total);
//		model.addAttribute("moneylist", moneylist);
//		model.addAttribute("shoplist", shoplist1);
		return "redirect:seeShopping.do";
		
	}
	
}
