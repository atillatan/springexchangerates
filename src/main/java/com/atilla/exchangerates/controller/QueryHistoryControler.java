package com.atilla.exchangerates.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.atilla.exchangerates.common.AppConstants;
import com.atilla.exchangerates.domain.QueryHistory;
import com.atilla.exchangerates.service.QueryHistoryService;

/**
 * Requirements and Basic Features:
 * 
 * All successful queries should be persisted in the DB. The customer can get
 * historical information using two API’s, one for the daily information and
 * other for the monthly information. ◦ daily:
 * /api/exchange-rate/history/daily/{yyyy}/{MM}/{dd} ◦ monthly:
 * /api/exchange-rate/history/monthly/{yyyy}/{MM}
 * 
 * @author atilla
 *
 */
@RestController
@RequestMapping(AppConstants.API_BASE_PATH + QueryHistoryControler.CONTROLLER_PATH)
public class QueryHistoryControler {

	private static final Logger logger = LoggerFactory.getLogger(QueryHistoryControler.class);

	public static final String CONTROLLER_PATH = "/exchange-rate/history";

	private final QueryHistoryService queryHistoryService;

	@Autowired
	public QueryHistoryControler(QueryHistoryService queryHistoryService) {
		this.queryHistoryService = queryHistoryService;
	}

	@GetMapping("/daily/{year}/{month}/{day}")
	public List<QueryHistory> daily(@PathVariable String year, @PathVariable String month, @PathVariable String day) {
		logger.info(String.format("Incoming request -> year: %s, month:%s, day:%s", year, month, day));
		return this.queryHistoryService.getDaily(year, month, day);
	}

	@GetMapping("/monthly/{year}/{month}")
	public List<QueryHistory> monthly(@PathVariable String year, @PathVariable String month) {
		logger.info(String.format("Incoming request -> year: %s, month:%s", year, month));
		return this.queryHistoryService.getMonthly(year, month);
	}

}
