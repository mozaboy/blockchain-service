
package com.service.zuul;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * spring-cloud-starter-zuul本身已经集成了hystrix和ribbon，
 * 所以Zuul天生就拥有线程隔离和断路器的自我保护能力，以及对服务调用的客户端负载均衡功能。
 * 但是，我们需要注意，当使用path与url的映射关系来配置路由规则时，对于路由转发的请求则不会采用HystrixCommand来包装，
 * 所以这类路由请求就没有线程隔离和断路器保护功能，并且也不会有负载均衡的能力。
 * 因此，我们在使用Zuul的时候尽量使用path和serviceId的组合进行配置，这样不仅可以保证API网关的健壮和稳定，
 * 也能用到Ribbon的客户端负载均衡功能。
 */
@EnableZuulProxy
@SpringBootApplication
//@EnableDiscoveryClient
public class ServiceMgrZuulApplication
{
	public static void main(String[] args)
	{
		//SpringApplication.run(ServiceMgrZuulApplication.class, args);
		new SpringApplicationBuilder(ServiceMgrZuulApplication.class).web(true).run(args);
	}
}
