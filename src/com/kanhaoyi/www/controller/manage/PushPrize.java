package com.kanhaoyi.www.controller.manage;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kanhaoyi.www.model.PrizeExchangeRecord;
import com.kanhaoyi.www.model.PrizeRecord;
import com.kanhaoyi.www.model.Push;
import com.kanhaoyi.www.model.User;
import com.kanhaoyi.www.service.IPrizeExchangeRecordService;
import com.kanhaoyi.www.service.IPrizeRecordService;
import com.kanhaoyi.www.service.IPushService;
import com.kanhaoyi.www.service.IUserService;
import com.kanhaoyi.www.util.IpUtil;
import com.kanhaoyi.www.util.JSONUtil;
import com.kanhaoyi.www.util.MoneyUtil;

/**
 * 
 * @description 推广 抽奖
 * @author zhuziming
 * @time 2018年12月25日 下午5:22:14
 */
@Controller
@RequestMapping("/pushPrize")
public class PushPrize {

	@Resource
	private IPrizeExchangeRecordService prizeExchangeRecordService;
	
	@Resource
	private IPrizeRecordService prizeRecordService;
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IPushService pushService;
	
	/**
	 * @description 抽奖
	 * @author zhuziming
	 * @time 2018年12月25日 下午5:26:02
	 * @return
	 */
	@RequestMapping("/lottery.action")
	@ResponseBody
	public String drawLottery(HttpSession session){
		// 检查用户是否登录
		User user = userService.getSessionUser(session);
		if(user==null){
			return JSONUtil.returnJson("2", "登录后才能抽奖");
		}
		int loggeryCount = 0; // 初始化抽奖次数为0次。
		// 检查用户的剩余抽奖次数，每天只有1次
		// 查看抽奖记录数，如果没有中奖记录，则可以抽奖
		int todayNum = prizeRecordService.getLoggeryRecord(user.getId());
		if(todayNum==0){
			loggeryCount++;
		}
		// 得到推广的额外抽奖次数
		int pushLotteryCount = pushService.getLotteryCountByUserID(user.getId()); 
		if(pushLotteryCount >0 ){
			loggeryCount = pushLotteryCount;
		}
		
		
		if(loggeryCount<1){
			return JSONUtil.returnJson("2", "抽奖次数已用完");
		}
		
		// 开始抽奖
		/* 奖品共有8个，数字分别为0~7，伟哥（金戈）、雅诗兰黛口红、1G流量不能被抽中，如果抽中转为拿心
		 * 0 1金币
		 * 1 2金币
		 * 2   伟哥（金戈）
		 * 3 50元优惠券
		 * 4   拿心
		 * 5 1G流量
		 * 6  雅诗兰黛口红
		 * 7  拿心
		 * */
		Random rand = new Random();
		int lotteryResult = rand.nextInt(8);// 初始化中奖结果
		if(lotteryResult==2 || lotteryResult==5 || lotteryResult==6){
			lotteryResult=7;
		}
		// 如果存在推广奖劢，优先使用
		if(pushLotteryCount>0){
			Push push = pushService.getFirstOneByUserID(user.getId()); // 取第一个推广奖劢
			push.setLottery(1); // 改为已使用
			pushService.update(push);
		}
		
		System.out.println("中奖号为：" + lotteryResult);
		// 插入抽奖记录
		if(lotteryResult==0 || lotteryResult==1){
			PrizeRecord prizeRecord = new PrizeRecord();
			prizeRecord.setPrizeName(getPrizeName(lotteryResult));
			prizeRecord.setUserID(user.getId());
			prizeRecord.setCreateTime(new Timestamp(new Date().getTime()));
			prizeRecord.setStatus(1); // 因为是虚拟产品，后边会插入兑换记录，这里设状态为已兑换
			prizeRecord.setType(lotteryResult);
			prizeRecordService.insert(prizeRecord);
			
			// 插入兑奖记录
			PrizeExchangeRecord prizeExchangeRecord = new PrizeExchangeRecord();
			prizeExchangeRecord.setCreateTime(new Timestamp(new Date().getTime()));
			prizeExchangeRecord.setPrizeRecordID(prizeRecord.getId());
			prizeExchangeRecord.setPrizeRecordName(getPrizeName(lotteryResult));
			prizeExchangeRecord.setUserID(user.getId());
			prizeExchangeRecordService.insert(prizeExchangeRecord);
			
			User user2 = userService.selectByID(user.getId());
			Double d = MoneyUtil.add(user2.getMoney(), this.getPrizePrice(lotteryResult));
			user2.setMoney(d);
			userService.updateMoney(user2); // 更新数据库中金额
			session.setAttribute("user", user2); // 更新session
		}else if(lotteryResult==3){
			// 优惠券
			PrizeRecord prizeRecord = new PrizeRecord();
			prizeRecord.setPrizeName(getPrizeName(lotteryResult));
			prizeRecord.setUserID(user.getId());
			prizeRecord.setCreateTime(new Timestamp(new Date().getTime()));
			prizeRecord.setStatus(0); // 因为是优惠券产品，这里设状态为未使用
			prizeRecord.setType(lotteryResult);
			prizeRecordService.insert(prizeRecord);
		}else{
			PrizeRecord prizeRecord = new PrizeRecord();
			prizeRecord.setPrizeName(getPrizeName(lotteryResult));
			prizeRecord.setUserID(user.getId());
			prizeRecord.setCreateTime(new Timestamp(new Date().getTime()));
			prizeRecord.setStatus(0); // 因为是拿心，这里设状态为未使用
			prizeRecord.setType(4); // 拿心
			prizeRecordService.insert(prizeRecord);
		}

		JSONObject jo = new JSONObject();
		jo.put("lotteryCount", --loggeryCount); // 剩余抽奖次数
		jo.put("lotteryResult", lotteryResult); // 抽奖结果
		
		return JSONUtil.returnJson("1", "抽奖成功",jo);
	}
	// 跟据编号 获得奖品的名字
	public String getPrizeName(int rand){
		switch(rand){
			case 0:
				return "1金币";
			case 1:
				return "2金币";
			case 3:
				return "50元优惠券";
			case 4:
				return "拿心";
			case 7:
				return "拿心";
			default:
				return "拿心";
		}
	}
	// 跟据编号 获得奖品的价格
	public int getPrizePrice(int rand){
		switch(rand){
			case 0:
				return 1; //"1金币";
			case 1:
				return 2; //"2金币";
			case 3:
				return 50; //"50元优惠券";
			case 4:
				return 0; //"拿心";
			case 7:
				return 0;// "拿心";
			default:
				return 0;// "拿心";
		}
	}
	
	/**
	 * 
	 * @description 查看抽奖次数
	 * @author zhuziming
	 * @time 2018年12月27日 下午1:39:38
	 * @return
	 */
	@RequestMapping("/getLotteryCount.action")
	@ResponseBody
	public String getLotteryCount(HttpSession session){
		User user = userService.getSessionUser(session);
		if(user==null){
			return JSONUtil.returnJson("2", "登录后才能抽奖");
		}
		int loggeryCount = 0; // 初始化抽奖次数为0次。
		// 检查用户的剩余抽奖次数，每天只有1次
		// 查看抽奖记录数，如果没有中奖记录，则可以抽奖
		int todayNum = prizeRecordService.getLoggeryRecord(user.getId());
		if(todayNum==0){
			loggeryCount++;
		}
		// 得到推广的额外抽奖次数
		int pushLotteryCount = pushService.getLotteryCountByUserID(user.getId()); 
		if(pushLotteryCount >0 ){
			loggeryCount = pushLotteryCount;
		}
		return JSONUtil.returnJson("1", loggeryCount+"");
	}
	/**
	 * @description 得到用户id
	 * @author zhuziming
	 * @time 2018年12月27日 下午3:03:15
	 * @param session
	 * @return
	 */
	@RequestMapping("/getUserID.action")
	@ResponseBody
	public String getUserID(HttpSession session){
		User user = userService.getSessionUser(session);
		if(user==null){
			return JSONUtil.returnJson("2", "请登录后在访问此页面");
		}else{
			return JSONUtil.returnJson("1", user.getId()+"");
		}
	}
	/**
	 * @description 推广方法，如果ip不存在则添加一次抽奖机会
	 * @author zhuziming
	 * @time 2018年12月27日 下午3:03:32
	 * @return
	 */
	@RequestMapping("/addLottery.action")
	public String addLottery(HttpServletRequest request){
		try{
			String userID = request.getParameter("userID");
			User user = userService.selectByID(Integer.valueOf(userID));
			String ip = IpUtil.getIpAddr(request);
			int ipNum = pushService.getCountByIP(ip); // 这个ip是否存在
			if(ipNum == 0){
				Push push = new Push();
				push.setIp(ip);
				push.setTime(new Timestamp(new Date().getTime()));
				push.setUserID(user.getId());
				push.setLottery(0);
				pushService.insert(push);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/index.html";
	}
	
	/**
	 * @description 得到抽奖记录
	 * @author zhuziming
	 * @time 2018年12月27日 下午4:52:34
	 * @return
	 */
	@RequestMapping("/getPrizeRecord.action")
	@ResponseBody
	public String getPrizeRecord(HttpSession session){
		User user = userService.getSessionUser(session);
		if(user==null){
			return JSONUtil.returnJson("2", "您还未登录");
		}else{
			List<PrizeRecord> list = prizeRecordService.getListByUserID(user.getId(), "id", "desc", 9, 0);
			StringBuffer sb = new StringBuffer();
			sb.append("<table>");
			for (PrizeRecord prizeRecord : list) {
				sb.append("<tr>");
				sb.append("<td>"+prizeRecord.getCreateTime()+"</td>");
				sb.append("<td>&nbsp;&nbsp;抽奖获得&nbsp;&nbsp;</td>");
				sb.append("<td>"+prizeRecord.getPrizeName()+"</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
			return JSONUtil.returnJson("1", sb.toString());
		}
	}
}
