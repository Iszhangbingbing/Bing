package classes.com.cn.controller;

import classes.com.cn.domain.AccountDao;

public class AccountController {
	private AccountDao dao = new AccountDao();
	public void transfer(int fId,int tId,String money){
		dao.transfer(fId, tId, money);
		System.out.println("what fucking");
        System.out.println("you are a fool");
	}
}
