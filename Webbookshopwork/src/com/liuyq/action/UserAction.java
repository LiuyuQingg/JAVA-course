package com.liuyq.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.liuyq.bean.Admin;
import com.liuyq.bean.Type;
import com.liuyq.bean.User;
import com.liuyq.service.AdminService;
import com.liuyq.service.BookService;
import com.liuyq.service.UserService;

@Controller
@RequestMapping("/user")
public class UserAction {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/registUser")
	public String regist(User user,@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request,Model model) {
		
		
		if(upfile.getOriginalFilename() == "" && upfile.getOriginalFilename() == null) {
			
			boolean result = userService.regist(user);
			if(result) {
				
				return "redirect:/book/recommend.do";
			}else {
				model.addAttribute("msg", "注册不成功！");
				return "forward:/book/regist.do";
			}
			
		}else if (upfile.getOriginalFilename() != "" && upfile.getOriginalFilename() != null) {
			
			String rootPath = request.getServletContext().getRealPath("/");
			
			try {
			
				FileCopyUtils.copy(upfile.getBytes(),new File(rootPath+"/images/",upfile.getOriginalFilename()));
		
				user.setImgUrl("images/"+upfile.getOriginalFilename());
			
				boolean res = userService.insertImgUrl(user);
		
			}catch(IOException e){
			
				e.printStackTrace();
			
			}
			model.addAttribute("msg", "注册成功，请登录！");
			return "forward:/book/regist.do";
		}
		model.addAttribute("msg", "注册不成功！");
		return "forward:/book/regist.do";
//			
	
		
		
	}
	
//	@RequestMapping("/beforeRegistUser")
//	public String beforeRegist(User user,@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request,Model model) {
//		
//		String email = user.getEmail();
//		//设置邮件服务器的参数
//		Properties props = new Properties();
//		props.put("mail.transport.protocol", "smtp");
//		props.put("mail.smtp.host", "smtp.163.com");
//		props.put("mail.smtp.auth", "true");
//		//创建与邮箱服务器之间的连接，使用用户名和授权码
//		Session sessions = Session.getInstance(props,new Authenticator() {
//			public PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("15732103061@163.com","liuyuqing0");
//			}
//		});
//		Message message = new MimeMessage(sessions);
//		try {
//			message.setFrom(new InternetAddress("15732103061@163.com"));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
//			message.setSubject("找回密码！");
//			//message.setText("您的密码已经初始化是:123456");
//			MimeBodyPart mimeBodyPart = new MimeBodyPart();
//			mimeBodyPart.setContent("请<a href = 'http://localhost:8080/Webbookshopwork/reset.jsp'>重置</a>密码","text/html;charset=utf-8");
//			MimeMultipart mimeMultipart = new MimeMultipart();
//			mimeMultipart.addBodyPart(mimeBodyPart);
//			message.setContent(mimeMultipart);
//			
////			//附件，文件部分
////			MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
////			//把文件，添加到附件2中
////			DataSource ds2 = new FileDataSource(new File("E:\\class\\2017-2018\\Andriod\\实验手册\\实验  静态包含.doc"));
////			//数据处理器
////			DataHandler dh2 = new DataHandler(ds2 );
////			//设置附件内容，文件
////			mimeBodyPart2.setDataHandler(dh2);
////			//设置附件的文件名
////			mimeBodyPart2.setFileName(MimeUtility.encodeText("实验手册.docx"));
////			
//			
////			MimeMultipart mimeMultipart2 = new MimeMultipart();
////			mimeMultipart2.addBodyPart( mimeBodyPart);
////			mimeMultipart2.addBodyPart(mimeBodyPart2);
////			message.setContent(mimeMultipart2);
////			
////			mimeBodyPart2.setDataHandler(dh2);
////			mimeBodyPart2.setFileName(MimeUtility.encodeText( "文件2.docx"));       
////			
//			
////			message.setContent(mimeMultipart2);
//			Transport.send(message);
//			
//
//
//		
//		} catch (AddressException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 catch (MessagingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////		response.setContentType("text/html");
////		response.getWriter().write("<html>邮件发送成功！");	
//
//		
//	}
	
	@RequestMapping("/login")
	public String login(String password,String username,HttpSession session,Model model) {
		
		User user = userService.login(password, username);
		Admin admin = adminService.login(password,username);
		if(user != null) {
			
			session.setAttribute("user1",user);
			System.out.println((User)session.getAttribute("user1"));
			
			return "redirect:/book/recommend.do";
			
		}else if(admin != null){
			
			session.setAttribute("admin",admin);
			System.out.println((Admin)session.getAttribute("admin"));
			return "redirect:/admin/login.to";
			
		}else {
			
			model.addAttribute("error","您还没有登录！");
			return "login";
		}
		
	}
	
	@RequestMapping("/aboutMe")
	public String aboutMe(Integer id,Model model) {
		
		if(id!=null) {
			
			List<Type> list =  bookService.findAllType();
			User user = userService.aboutMe(id);
			model.addAttribute("user", user);
			model.addAttribute("typelist", list);
			return "account";
			
		}else {
			
			model.addAttribute("error","您还没有登录！" );
			return "login";
			
		}
		
	}
	
	@RequestMapping("/updateUser")
	public String update( User user,@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request,Model model) {
		
	
			if(upfile .getOriginalFilename() == "") {
				
				User user1 = userService.update(user);
				model.addAttribute("user", user1);
				
				return "account";
				
			}else if(upfile .getOriginalFilename() != "") {
				String rootPath = request.getServletContext().getRealPath("/");
			
				try {
				
					FileCopyUtils.copy(upfile.getBytes(),new File(rootPath+"/images/",upfile.getOriginalFilename()));
			
					user.setImgUrl("images/"+upfile.getOriginalFilename());
				
					User user1 = userService.update(user);
					model.addAttribute("user", user1);
			
				}catch(IOException e){
				
					e.printStackTrace();
				
				}
			
	
		
		
				return "account";
			}
			return "account";
			
		
	}
	
	@RequestMapping("/forgetPassword")
	public String forgetPassword() {
		
		return "findpasword";
		
	}
	
	@RequestMapping("/sendMail")
	public String sendMail(String mail,HttpSession session) {
		
		User user = userService.findUserByMail(mail);
		session.getServletContext().setAttribute("mailUser", user);
		//设置邮件服务器的参数
				Properties props = new Properties();
				props.put("mail.transport.protocol", "smtp");
				props.put("mail.smtp.host", "smtp.163.com");
				props.put("mail.smtp.auth", "true");
				//创建与邮箱服务器之间的连接，使用用户名和授权码
				Session sessions = Session.getInstance(props,new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("15732103061@163.com","liuyuqing0");
					}
				});
				Message message = new MimeMessage(sessions);
				try {
					message.setFrom(new InternetAddress("15732103061@163.com"));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
					message.setSubject("找回密码！");
					//message.setText("您的密码已经初始化是:123456");
					MimeBodyPart mimeBodyPart = new MimeBodyPart();
					mimeBodyPart.setContent("请<a href = 'http://localhost:8080/Webbookshopwork/reset.jsp'>重置</a>密码","text/html;charset=utf-8");
					MimeMultipart mimeMultipart = new MimeMultipart();
					mimeMultipart.addBodyPart(mimeBodyPart);
					message.setContent(mimeMultipart);
					
//					//附件，文件部分
//					MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
//					//把文件，添加到附件2中
//					DataSource ds2 = new FileDataSource(new File("E:\\class\\2017-2018\\Andriod\\实验手册\\实验  静态包含.doc"));
//					//数据处理器
//					DataHandler dh2 = new DataHandler(ds2 );
//					//设置附件内容，文件
//					mimeBodyPart2.setDataHandler(dh2);
//					//设置附件的文件名
//					mimeBodyPart2.setFileName(MimeUtility.encodeText("实验手册.docx"));
//					
					
//					MimeMultipart mimeMultipart2 = new MimeMultipart();
//					mimeMultipart2.addBodyPart( mimeBodyPart);
//					mimeMultipart2.addBodyPart(mimeBodyPart2);
//					message.setContent(mimeMultipart2);
//					
//					mimeBodyPart2.setDataHandler(dh2);
//					mimeBodyPart2.setFileName(MimeUtility.encodeText( "文件2.docx"));       
//					
					
//					message.setContent(mimeMultipart2);
					Transport.send(message);
					


				
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//				response.setContentType("text/html");
//				response.getWriter().write("<html>邮件发送成功！");	
		
		return "success";
		
	}
	
	@RequestMapping("/resetPassword")
	public String resetPassword(HttpSession session,String password) {
		
		User user = (User) session.getServletContext().getAttribute("mailUser");
		boolean result = userService.resetPassword(user, password);
		if(result) {
			
			return "successes";
			
		}
		
		return "fail";
		
	}

}
