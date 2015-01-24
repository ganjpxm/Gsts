package org.ganjp.gone.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.ganjp.gone.common.util.MaskFormat;
import org.ganjp.gone.demo.model.FirstJavaBean;
import org.ganjp.gone.demo.model.SocialSecurityNumber;
import org.ganjp.gone.demo.model.User;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.rss.Channel;

@Controller
@RequestMapping("/spring")
public class SpringController {
	
	@RequestMapping("")
	public String goToSpring() {
		return "demo/spring";
	}
	
	//-------------------------------- Simple ---------------------------------------
	@RequestMapping("/simple/hello")
	public @ResponseBody String sayHello() {
		return "Hello world!";
	}
	@RequestMapping(value="/simple/hello2", method=RequestMethod.GET, headers="Accept=text/plain")
	public @ResponseBody String sayHello2() {
		return "Hello world!";
	}
	
	//-------------------------------- Request Mapping ---------------------------------------
	@RequestMapping(value="/mapping/path/*", method=RequestMethod.GET)
	public @ResponseBody String byPathPattern(HttpServletRequest request) {
		return "Mapped by path pattern ('" + request.getRequestURI() + "')"; //('/gone/spring/mapping/path/ganjp')
	}
	@RequestMapping(value="/mapping/parameter", method=RequestMethod.GET, params="!foo") // or params="!foo", /gone/spring/mapping/parameter?foo=bar
	public @ResponseBody String byParameter() {
		return "Mapped by path + method + absence of query parameter!";
	}
	@RequestMapping(value="/mapping/header", method=RequestMethod.GET, headers="FooHeader")
	public @ResponseBody String byHeaderNegation() {
		return "Mapped by path + method + presence of header!";
	}
	@RequestMapping(value="/mapping/consumes", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String byConsumes(@RequestBody User user) {
		return "Mapped by path + method + consumable media type (user '" + user + "')";
	}
	@RequestMapping(value="/mapping/produces", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User byUserJson() {
		return new User();
	}
	@RequestMapping(value="/mapping/produces", method=RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody User byUserXml() {
		return new User();
	}
	
	//-------------------------------- Request Data ---------------------------------------
	@RequestMapping(value="/data/param", method=RequestMethod.GET)
	public @ResponseBody String withParam(@RequestParam String userName) {
		return "Obtained 'userName' query parameter value '" + userName + "'";
	}
	@RequestMapping(value="/data/group", method=RequestMethod.GET)
	public @ResponseBody String withParamGroup(User user) {
		return "Obtained parameter group " + user;
	}
	@RequestMapping(value="/data/path/{var}", method=RequestMethod.GET)
	public @ResponseBody String withPathVariable(@PathVariable String var) {
		return "Obtained 'var' path variable value '" + var + "'";
	}
	@RequestMapping(value="/data/{path}/simple", method=RequestMethod.GET) // data/user;userName=ganjp;userName=xiaogan/simple
	public @ResponseBody String withMatrixVariable(@PathVariable String path, @MatrixVariable String userName) {
		return "Obtained matrix variable 'userName=" + userName + "' from path segment '" + path + "'";
	}
	@RequestMapping(value="{path1}/{path2}", method=RequestMethod.GET)
	public @ResponseBody String withMatrixVariablesMultiple (
			@PathVariable String path1, @MatrixVariable(value="foo", pathVar="path1") String foo1,
			@PathVariable String path2, @MatrixVariable(value="foo", pathVar="path2") String foo2) {
		return "Obtained matrix variable foo=" + foo1 + " from path segment '" + path1
				+ "' and variable 'foo=" + foo2 + " from path segment '" + path2 + "'";
	}
	@RequestMapping(value="/data/header", method=RequestMethod.GET)
	public @ResponseBody String withHeader(@RequestHeader String Accept) {
		return "Obtained 'Accept' header '" + Accept + "'";
	}
	@RequestMapping(value="/data/cookie", method=RequestMethod.GET)
	public @ResponseBody String withCookie(@CookieValue String openid_provider) {
		return "Obtained 'openid_provider' cookie '" + openid_provider + "'";
	}
	@RequestMapping(value="/data/body", method=RequestMethod.POST)
	public @ResponseBody String withBody(@RequestBody String body) {
		return "Posted request body '" + body + "'";
	}
	@RequestMapping(value="/data/entity", method=RequestMethod.POST)
	public @ResponseBody String withEntity(HttpEntity<String> entity) {
		return "Posted request body '" + entity.getBody() + "'; headers = " + entity.getHeaders();
	}
	//-- Standard Resolvable Web Arguments ----
	@RequestMapping(value="/data/standard/request", method=RequestMethod.GET)
	public @ResponseBody String standardRequestArgs(HttpServletRequest request, Principal user, Locale locale) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("request = ").append(request).append(", ");
		buffer.append("userPrincipal = ").append(user).append(", ");
		buffer.append("requestLocale = ").append(locale);
		return buffer.toString();
	}
	@RequestMapping(value="/data/standard/request/reader", method=RequestMethod.POST)
	public @ResponseBody String requestReader(Reader requestBodyReader) throws IOException {
		return "Read char request body = " + FileCopyUtils.copyToString(requestBodyReader);
	}
	@RequestMapping(value="/data/standard/request/is", method=RequestMethod.POST)
	public @ResponseBody String requestReader(InputStream requestBodyIs) throws IOException {
		return "Read binary request body = " + new String(FileCopyUtils.copyToByteArray(requestBodyIs));
	}
	@RequestMapping("/data/standard/response")
	public @ResponseBody String response(HttpServletResponse response) {
		return "response = " + response;
	}
	@RequestMapping("/data/standard/response/writer")
	public void availableStandardResponseArguments(Writer responseWriter) throws IOException {
		responseWriter.write("Wrote char response using Writer");
	}
	@RequestMapping("/data/standard/response/os")
	public void availableStandardResponseArguments(OutputStream os) throws IOException {
		os.write("Wrote binary response using OutputStream".getBytes());
	}
	@RequestMapping("/data/standard/session")
	public @ResponseBody String session(HttpSession session) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("session=").append(session);
		return buffer.toString();
	}
	
	//-------------------------------- Response Writing ---------------------------------------
	@RequestMapping("/response/charset/accept")
	public @ResponseBody String responseAcceptHeaderCharset() {
		return "你好世界！ (\"Hello world!\" in Chinese)";
	}
	@RequestMapping(value="/response/charset/produce", produces="text/plain;charset=UTF-8")
	public @ResponseBody String responseProducesConditionCharset() {
		return "你好世界！ (\"Hello world!\" in Chinese)";
	}
	
	//-------------------------------- Message Converters ---------------------------------------
	@RequestMapping(value="/messageconverters/form", method=RequestMethod.GET)
	public @ResponseBody MultiValueMap<String, String> writeForm() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("firstName", "Jianping");
		map.add("lastName", "Gan");
		return map;
	}
	// Jaxb2RootElementHttpMessageConverter (requires JAXB2 on the classpath - useful for serving clients that expect to work with XML)
	@RequestMapping(value="/messageconverters/xml", method=RequestMethod.POST)
	public @ResponseBody String readXml(@RequestBody User user) {
		return "Read from XML: " + user;
	}
	@RequestMapping(value="/messageconverters/xml", method=RequestMethod.GET)
	public @ResponseBody User writeXml() {
		return new User("Xiao", "Gan");
	}
	// MappingJacksonHttpMessageConverter (requires Jackson on the classpath - particularly useful for serving JavaScript clients that expect to work with JSON)
	@RequestMapping(value="/messageconverters/json", method=RequestMethod.POST)
	public @ResponseBody String readJson(@Valid @RequestBody User user) {
		return "Read from JSON: " + user;
	}
	@RequestMapping(value="/messageconverters/json", method=RequestMethod.GET)
	public @ResponseBody User writeJson() {
		return new User("Xiao", "Gan");
	}
	// AtomFeedHttpMessageConverter (requires Rome on the classpath - useful for serving Atom feeds)
	@RequestMapping(value="/messageconverters/atom", method=RequestMethod.POST)
	public @ResponseBody String readFeed(@RequestBody Feed feed) {
		return "Read " + feed.getTitle();
	}
	@RequestMapping(value="/messageconverters/atom", method=RequestMethod.GET)
	public @ResponseBody Feed writeFeed() {
		Feed feed = new Feed();
		feed.setFeedType("atom_1.0");
		feed.setTitle("My Atom feed");
		return feed;
	}
	// RssChannelHttpMessageConverter (requires Rome on the classpath - useful for serving RSS feeds)
	@RequestMapping(value="/messageconverters/rss", method=RequestMethod.POST)
	public @ResponseBody String readChannel(@RequestBody Channel channel) {
		return "Read " + channel.getTitle();
	}
	@RequestMapping(value="/messageconverters/rss", method=RequestMethod.GET)
	public @ResponseBody Channel writeChannel() {
		Channel channel = new Channel();
		channel.setFeedType("rss_2.0");
		channel.setTitle("My RSS feed");
		channel.setDescription("Description");
		channel.setLink("http://localhost:8080/gone/spring/messageconverters/rss");
		return channel;
	}
	//-------------------------------- Type Converters ---------------------------------------
	@RequestMapping("/convert/bean")
	public @ResponseBody String bean(FirstJavaBean bean) {
		return "Converted " + bean;
	}
	@RequestMapping("/convert/primitive") //convert/primitive?value=3
	public @ResponseBody String primitive(@RequestParam Integer value) {
		return "Converted primitive " + value;
	}
	@RequestMapping("/convert/date/{value}") //convert/date/2010-07-04
	public @ResponseBody String date(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date value) {
		return "Converted date " + value;
	}
	@RequestMapping("/convert/collection") //convert/collection?values=1&values=2&values=3&values=4&values=5  or convert/collection?values=1,2,3,4,5"
	public @ResponseBody String collection(@RequestParam Collection<Integer> values) {
		return "Converted collection " + values;
	}
	@RequestMapping("/convert/formattedCollection") //convert/formattedCollection?values=2010-07-04,2011-07-04
	public @ResponseBody String formattedCollection(@RequestParam @DateTimeFormat(iso=ISO.DATE) Collection<Date> values) {
		return "Converted formatted collection " + values;
	}
	@RequestMapping("/convert/value") //convert/value?value=123456789
	public @ResponseBody String valueObject(@RequestParam SocialSecurityNumber value) {
		return "Converted value object " + value;
	}
	@RequestMapping("/convert/custom") //convert/custom?value=123-45-6789
	public @ResponseBody String customConverter(@RequestParam @MaskFormat("###-##-####") String value) {
		return "Converted '" + value + "' with a custom converter";
	}
	
	//------------------------------- Validation -----------------------
	// enforcement of constraints on the JavaBean arg require a JSR-303 provider on the classpath
	@RequestMapping("/validate")
	public @ResponseBody String validate(@Valid User bean, BindingResult result) {
		if (result.hasErrors()) {
			return "Object has validation errors";
		} else {
			return "No errors";
		}
	}
	
	//------------------------------- File Upload -----------------------
	@RequestMapping(value="/fileupload", method=RequestMethod.POST)
	public @ResponseBody String processUpload(@RequestParam MultipartFile file, Model model) throws IOException {
		model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
		return "File '" + file.getOriginalFilename() + "' uploaded successfully";
	}
	
	//------------------------------- Exception Handling -----------------------
	@RequestMapping("/exception")
	public @ResponseBody String exception() {
		throw new IllegalStateException("Sorry!");
	}
	@ExceptionHandler
	public @ResponseBody String handle(IllegalStateException e) {
		return "IllegalStateException handled!";
	}
	
	//------------------------------- Redirecting -----------------------
	@RequestMapping(value="/redirect/uriTemplate", method=RequestMethod.GET)
	public String uriTemplate(RedirectAttributes redirectAttrs) {
		redirectAttrs.addAttribute("account", "ganjp");  // Used as URI template variable
		redirectAttrs.addAttribute("date", new LocalDate(2014, 12, 8));  // Appended as a query parameter
		return "redirect:/spring/redirect/{account}";
	}
	@RequestMapping(value="/redirect/{account}", method=RequestMethod.GET)
	public String show(@PathVariable String account, @RequestParam(required=false) LocalDate date) {
		return "jsp/demo/redirectResults";
	}
	
	//------------------------------- Async Requests -----------------------
	@RequestMapping("/async/callable/response-body")
	public @ResponseBody Callable<String> callable() {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "Callable result";
			}
		};
	}
	@RequestMapping("/async/callable/view")
	public Callable<String> callableWithView(final Model model) {

		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				model.addAttribute("foo", "bar");
				model.addAttribute("fruit", "apple");
				return "jsp/demo/html";
			}
		};
	}
	@RequestMapping("/async/callable/exception")
	public @ResponseBody Callable<String> callableWithException(
			final @RequestParam(required=false, defaultValue="true") boolean handled) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				if (handled) {
					// see handleException method further below
					throw new IllegalStateException("Callable error");
				}
				else {
					throw new IllegalArgumentException("Callable error");
				}
			}
		};
	}
	@RequestMapping("/async/callable/custom-timeout-handling")
	public @ResponseBody WebAsyncTask<String> callableWithCustomTimeoutHandling() {
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "Callable result";
			}
		};
		return new WebAsyncTask<String>(1000, callable);
	}

}
