package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import junit.framework.Assert;

public class LoginLogoutTest {

	@Test
	public void loginIniRealm() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager--->使用的initRealm作为数据源
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject-->与用户直接交互的对象
		Subject subject = SecurityUtils.getSubject();
		// 4、创建用户名/密码身份验证Token（即用户身份/凭证）
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
		}

		Assert.assertTrue("用户验证成功!", subject.isAuthenticated());

		// 6、退出
		subject.logout();
	}

	@Test
	public void loginCustomRealm() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager--->使用的initRealm作为数据源
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-realm.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject-->与用户直接交互的对象
		Subject subject = SecurityUtils.getSubject();
		// 4、创建用户名/密码身份验证Token（即用户身份/凭证）
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
		}

		Assert.assertTrue("用户验证成功!", subject.isAuthenticated());

		// 6、退出
		subject.logout();
	}

	@Test
	public void checkRole() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager--->使用的initRealm作为数据源
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-role.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject-->与用户直接交互的对象
		Subject subject = SecurityUtils.getSubject();

		subject.hasRole("role1");// 判断是否具有角色
		subject.checkRole("role1");// 断言是否具有角色->没有则会抛出异常
	}

	@Test
	public void checkPermission() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager--->使用的initRealm作为数据源
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-role-permission.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject-->与用户直接交互的对象
		Subject subject = SecurityUtils.getSubject();
		
		subject.isPermitted("user:create");
		subject.checkPermission("user:create");
		
	}

}
