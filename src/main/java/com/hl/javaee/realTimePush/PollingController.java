package com.hl.javaee.realTimePush;

import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

/**
 * 长轮询请求
 * @author huanglin 2023/11/14 下午5:28:46
 */

@Controller
@RequestMapping("/polling")
public class PollingController {

	// 存放监听的长轮询集合
	public static Multimap<String, DeferredResult<String>> watchRequest = Multimaps.synchronizedMultimap(HashMultimap.create());
	
	@GetMapping("/watch/{id}")
	@ResponseBody
	public DeferredResult<String> watch(@PathVariable String id){
		// 延迟对象设置超时时间
		DeferredResult<String> deferredResult = new DeferredResult<>(1000L);
		
		deferredResult.onCompletion(new Runnable() {
			// 异步请求完成时移除key，防止内存溢出
			@Override
			public void run() {
				watchRequest.remove(id, deferredResult);
			}
		});
		
		// 注册长轮询请求
		watchRequest.put(id, deferredResult);
		
		return deferredResult;
	}
	
	@GetMapping("/pulish/{id}")
	@ResponseBody
	public String publish(@PathVariable String id) {
		// 数据变更 取出监听ID的所有长轮询请求, 并一一响应处理
		if(watchRequest.containsKey(id)) {
			Collection<DeferredResult<String>> deferredResults = watchRequest.get(id);
			for(DeferredResult<String> deferredResult : deferredResults) {
				deferredResult.setResult("有数据更新了" + new Date());
			}
		}
		
		return "ok";
	}
}
