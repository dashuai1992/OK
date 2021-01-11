package com.very.ok.sys.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.very.ok.result.Result;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@RefreshScope
public class Index {

	private static Logger log = LoggerFactory.getLogger(Index.class);

	@Value("${version}")
	private String version;

	@RequestMapping()
	public String index() {
		log.info("running..");
		return "running..";
	}

	@GetMapping(value = "/info")
	public Result info() {
		return Result.ok(version);
	}
	
	@GetMapping(value = "/json")
	public Result json() {
		return Result.ok(version);
	}
	
	@GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public Result xml() {
		return Result.ok(version);
	}

}
